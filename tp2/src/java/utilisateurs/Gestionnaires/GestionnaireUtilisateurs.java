/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.Gestionnaires;

import java.util.Collection;  
import javax.ejb.Stateless;  
import javax.persistence.EntityManager;  
import javax.persistence.PersistenceContext;  
import javax.persistence.Query;  
import utilisateurs.modeles.Utilisateur; 

/**
 *
 * @author Aicha
 */
@Stateless  
public class GestionnaireUtilisateurs {  
    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext  
    private EntityManager em;  
  
    public void creerUtilisateursDeTest() {  
        creeUtilisateur("John", "Lennon", "jlennon");  
        creeUtilisateur("Paul", "Mac Cartney", "pmc");  
        creeUtilisateur("Ringo", "Starr", "rstarr");  
        creeUtilisateur("Georges", "Harisson", "georgesH");  
    }  
  
    public Utilisateur creeUtilisateur(String nom, String prenom, String login) {  
        Utilisateur u = new Utilisateur(nom, prenom, login);  
        em.persist(u);  
        return u;  
    } 
    public void ajouterUtilisateur(String nom, String prenom, String login){
        Utilisateur u = creeUtilisateur(nom,  prenom,  login);
        //Collection<Utilisateur> listeUtilisateurs = getAllUsers();
        //listeUtilisateurs.add(u);
    }
     public  Collection<Utilisateur> rechercherUtilisateurs(String nom, String prenom, String login){
         String where = "";
         if (nom != "") {
             where = where + "u.lastname like '%"+nom+"%'";
         }
         
         if (prenom != "") {
             if (where == "") where = where + "u.firstname like '%"+prenom+"%'";
             else where = where +  "OR u.firstname like '%"+prenom+"%'";
         }
         
         if (login != "") {
             if (where == "") where = where + "u.login like '%"+login+"%'";
             else where = where +  "OR u.login like '%"+login+"%'";
         }
         
         if (where != "") where = " where "+where;
         
         Query q = em.createQuery("select u from Utilisateur u "+where); // u.lastname like '%"+nom+"%'");// OR u.firstname like '%"+prenom+"%' OR u.login like '%"+login+"%'");
          
         return q.getResultList(); 
          
     }
    public void modifUtilisateur(String nom, String prenom, String login){
         //for(Utilisateur ut : getAllUsers()){
           //if(ut.getLastname().equalsIgnoreCase(nom) || ut.getFirstname().equalsIgnoreCase(prenom) || ut.getLogin().equalsIgnoreCase(login)){
            Query q= em.createQuery("update  Utilisateur ut"
                    +" set ut.getLastname()=nom,ut.getFirstname()=prenom,ut.getLogin()=login where"
                    +"ut.getLasrname()=nom OR ut.getFirstname=prenom OR ut.getLogin=login"); 
            int numUpdates = q.executeUpdate();
    }
  
    public Collection<Utilisateur> getAllUsers() {  
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u");  
        return q.getResultList();  
    }  
    // Add business logic below. (Right-click in editor and choose  
    // "Insert Code > Add Business Method")  
}  