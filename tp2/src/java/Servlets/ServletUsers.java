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
        String message = "pas compris";  
  
        if (action != null) {  
            if (action.equals("listerLesUtilisateurs")) {  
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();  
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "pagejsp.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";  
            } else if (action.equals("creerUtilisateursDeTest")) {  
                  gestionnaireUtilisateurs.creerUtilisateursDeTest();  
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();  
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "pagejsp.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";  
            } else if(action.equals("ajouterUtilisateur")){
                gestionnaireUtilisateurs.ajouterUtilisateur(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("login"));
                forwardTo = "pagejsp.jsp?";
                message="utilisateur ajouter";
            }
            else if(action.equals("rechercherUtilisateur")){
               Collection<Utilisateur> utilisateurs= gestionnaireUtilisateurs.rechercherUtilisateurs(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("login"));
               request.setAttribute("listeDesUsers", utilisateurs); 
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
            else {  
                forwardTo = "pagejsp.jsp?action=todo";  
                message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";  
            }  
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
}
