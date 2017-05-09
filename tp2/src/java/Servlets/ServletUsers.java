/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;  
import java.util.Collection;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import utilisateurs.Gestionnaires.GestionnaireUtilisateurs;  
import utilisateurs.modeles.Utilisateur;  
  
/**
 *
 * @author Aicha
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {
    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        // Pratique pour décider de l'action à faire  
        String action = request.getParameter("action");  
        String forwardTo = "pagejsp.jsp";  
        String message = "en attente d'une action";
        int pagination = 0;
        if(request.getParameter("pagination") != null)
        {
            pagination = Integer.parseInt(request.getParameter("pagination"));
        }
        if (action != null) {  
            if (action.equals("listerLesUtilisateurs") && request.getSession().getAttribute("user") != null) {  
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getPaginatedUsers(pagination);
                int numberUsers = gestionnaireUtilisateurs.getNumberUsers();
                request.setAttribute("listeDesUsers", liste);  
                request.setAttribute("nombreUsers", numberUsers);
                forwardTo = "pagejsp.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";  
            } else if (action.equals("creerUtilisateursDeTest") && request.getSession().getAttribute("user") != null) {  
                gestionnaireUtilisateurs.creerUtilisateursDeTest();  
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getPaginatedUsers(pagination);  
                int numberUsers = gestionnaireUtilisateurs.getNumberUsers();
                request.setAttribute("listeDesUsers", liste);  
                request.setAttribute("nombreUsers", numberUsers); 
                forwardTo = "pagejsp.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";  
            } else if(action.equals("ajouterUtilisateur") && request.getParameter("login") != null && !request.getParameter("login").isEmpty()){
                String mdp = request.getParameter("mdp");
                gestionnaireUtilisateurs.ajouterUtilisateur(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("login"), mdp);
                forwardTo = "pagejsp.jsp?action=listerLesUtilisateurs";
                message="utilisateur ajouter";
            }
            else if(action.equals("rechercherUtilisateur") && request.getSession().getAttribute("user") != null && request.getParameter("post") != null){
               Collection<Utilisateur> utilisateurs= gestionnaireUtilisateurs.rechercherUtilisateurs(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("login"));
               request.setAttribute("listeDesUsers", utilisateurs); 
               int numberUsers = utilisateurs.size();
               request.setAttribute("nombreUsers", numberUsers);
                System.out.println("utilsateurs = "+utilisateurs);
                if(utilisateurs!=null){
                    forwardTo = "pagejsp.jsp?action=listerLesUtilisateurs";  
                    message = "Liste des utilisateurs de votre recherche";  
                }
                else
                {
                    forwardTo = "pagejsp.jsp?";
                    message = "aucun utilisateur ne correspond à votre recherche"; 
                }
            }
            else if(action.equals("updateUtilisateur") && request.getSession().getAttribute("user") != null&& request.getParameter("login") != null && !request.getParameter("login").isEmpty()){
                String mdp = request.getParameter("mdp");
                gestionnaireUtilisateurs.modifUtilisateur(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("login"), mdp);
                forwardTo = "pagejsp.jsp?action=listerLesUtilisateurs";
                message="utilisateur modifier";
            }
          else if(action.equals("deleteUtilisateur") && request.getSession().getAttribute("user") != null&& request.getParameter("login") != null && !request.getParameter("login").isEmpty()){
                String mdp = request.getParameter("mdp");
                gestionnaireUtilisateurs.delete(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("login"), mdp);
                forwardTo = "pagejsp.jsp?action=listerLesUtilisateurs";
                message="utilisateur supprimer";
            }
                    
            else if (action.equals("seConnecter")) {
                String login = request.getParameter("login");
                String mdp = request.getParameter("mdp");
                Utilisateur user = gestionnaireUtilisateurs.getUser(login, mdp);
                if(user != null)
                {
                    request.getSession().setAttribute("user", user);
                    message = "Utilisateur connecté";
                }
                else
                {
                    message = "Mot de passe ou login incorrect";
                }
                forwardTo = "pagejsp.jsp?action=connexion";
            } else if (action.equals("deconnexion")) {
                request.getSession().removeAttribute("user");
                message = "Deconnexion de l'utilisateur";
                forwardTo = "pagejsp.jsp?action=deconnexion";
            }else if (request.getSession().getAttribute("user") != null) {
                forwardTo = "pagejsp.jsp?action="+action;
            }
            else {  
                forwardTo = "pagejsp.jsp?action="+action;  
                message = "Il faut se connecter pour accéder à certaines fonctionnalités !";  
            }  
        }  
        if(request.getSession().getAttribute("user") != null)
        {
            request.setAttribute("user", request.getSession().getAttribute("user"));
        }
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);  
        dp.forward(request, response);  
        // Après un forward, plus rien ne peut être exécuté après !  
    }  
     
     
     
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ServletUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServletUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ServletUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServletUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
