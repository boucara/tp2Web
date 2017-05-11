/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilisateurs.Gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Adresse;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Tom
 */
@WebServlet(name = "ServletAdresses", urlPatterns = {"/ServletAdresses"})
public class ServletAdresses extends HttpServlet {
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
        String action = request.getParameter("action");
        String forwardTo = "adresse.jsp";
        String message = "en attente d'une action";
        int pagination = 0;
        String loginUser = request.getParameter("loginUser");
        request.setAttribute("loginUser", loginUser);  
        Utilisateur user = (Utilisateur)request.getSession().getAttribute("user");
        if(request.getParameter("pagination") != null)
        {
            pagination = Integer.parseInt(request.getParameter("pagination"));
        }
        if (action != null) {  
            if (action.equals("listerLesAdresses") && user != null) {  
                Collection<Adresse> liste = gestionnaireUtilisateurs.getAdresses(loginUser, pagination);
                int numberAdresses = gestionnaireUtilisateurs.getNbAdresses(loginUser);
                System.out.println(numberAdresses);
                
                request.setAttribute("listeAdresses", liste);  
                request.setAttribute("nombreAdresses", numberAdresses);
                forwardTo = "adresse.jsp?action=listerLesAdresses";  
                message = "Liste des adresses de l'utilisateur " + loginUser;  
            }  else if(action.equals("ajouterAdresses") && user != null){
                forwardTo = "adresse.jsp?action=listerLesUtilisateurs";
                message="utilisateur ajouter";
            }
            else if(action.equals("updateAdresses") && user != null){
                forwardTo = "adresse.jsp?action=listerLesUtilisateurs";
                message="utilisateur modifier";
            }
            else if(action.equals("deleteAdresses") && user != null){
                forwardTo = "adresse.jsp?action=listerLesUtilisateurs";
                message="utilisateur supprimer";
            }    
            else if (request.getSession().getAttribute("user") != null) {
                forwardTo = "adresse.jsp?action="+action;
            }
            else {  
                forwardTo = "pagejsp.jsp?action="+action;  
                message = "Il faut se connecter pour accéder à certaines fonctionnalités !";  
            }  
        }  
        if(user != null)
        {
            request.setAttribute("user", user);
        }
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);  
        dp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
