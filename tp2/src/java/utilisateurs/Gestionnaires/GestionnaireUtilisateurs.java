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
        creeUtilisateur("John", "Lennon", "jlennon", "test");
        creeUtilisateur("Paul", "Mac Cartney", "pmc", "test");
        creeUtilisateur("Ringo", "Starr", "rstarr", "test");
        creeUtilisateur("Georges", "Harisson", "georgesH", "test");
        creeUtilisateur("Rivera", "Garth", "RiveraGarth", "test");
        creeUtilisateur("Joyce", "Amery", "JoyceAmery", "test");
        creeUtilisateur("Saunders", "Micah", "SaundersMicah", "test");
        creeUtilisateur("Mccarty", "Fritz", "MccartyFritz", "test");
        creeUtilisateur("Blake", "Perry", "BlakePerry", "test");
        creeUtilisateur("Jenkins", "Maxwell", "JenkinsMaxwell", "test");
        creeUtilisateur("Burch", "Geoffrey", "BurchGeoffrey", "test");
        creeUtilisateur("Roberson", "Lane", "RobersonLane", "test");
        creeUtilisateur("Carey", "Baxter", "CareyBaxter", "test");
        creeUtilisateur("Bishop", "Samson", "BishopSamson", "test");
        creeUtilisateur("Carver", "Odysseus", "CarverOdysseus", "test");
        creeUtilisateur("Daniel", "Blaze", "DanielBlaze", "test");
        creeUtilisateur("Barlow", "Isaiah", "BarlowIsaiah", "test");
        creeUtilisateur("Landry", "Raymond", "LandryRaymond", "test");
        creeUtilisateur("Bruce", "Gray", "BruceGray", "test");
        creeUtilisateur("Bryant", "Merrill", "BryantMerrill", "test");
        creeUtilisateur("Little", "Grant", "LittleGrant", "test");
        creeUtilisateur("Glass", "Judah", "GlassJudah", "test");
        creeUtilisateur("Rodgers", "Bevis", "RodgersBevis", "test");
        creeUtilisateur("Love", "Channing", "LoveChanning", "test");
        creeUtilisateur("Walters", "Flynn", "WaltersFlynn", "test");
        creeUtilisateur("Mitchell", "Gareth", "MitchellGareth", "test");
        creeUtilisateur("Hale", "Ronan", "HaleRonan", "test");
        creeUtilisateur("Griffith", "Cameron", "GriffithCameron", "test");
        creeUtilisateur("Little", "Thomas", "LittleThomas", "test");
        creeUtilisateur("Valenzuela", "Leroy", "ValenzuelaLeroy", "test");
        creeUtilisateur("Glass", "Hakeem", "GlassHakeem", "test");
        creeUtilisateur("Morales", "Keith", "MoralesKeith", "test");
        creeUtilisateur("Bradford", "Mohammad", "BradfordMohammad", "test");
        creeUtilisateur("Ellison", "Vaughan", "EllisonVaughan", "test");  
    }  
  
    public Utilisateur creeUtilisateur(String nom, String prenom, String login, String mdp) {  
        Utilisateur u = new Utilisateur(nom, prenom, login, mdp);  
        em.persist(u);  
        return u;  
    } 
    public void ajouterUtilisateur(String nom, String prenom, String login, String mdp){
        Utilisateur u = creeUtilisateur(nom,  prenom,  login, mdp);
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
    public void modifUtilisateur(String nom, String prenom, String login, String mdp){
         //for(Utilisateur ut : getAllUsers()){
         // if(ut.getLastname().equalsIgnoreCase(nom) || ut.getFirstname().equalsIgnoreCase(prenom) || ut.getLogin().equalsIgnoreCase(login)){
            Query q= em.createQuery("update Utilisateur ut set ut.lastname= :nom  , ut.firstname=:prenom ,ut.login=:login  , ut.mdp=:mdp  where  ut.login=:login "); 
            q.setParameter("nom", nom);
            q.setParameter("prenom", prenom);
            q.setParameter("login", login);
            q.setParameter("mdp", mdp);
            int numUpdates = q.executeUpdate();
          
    }
    
  
    public Collection<Utilisateur> getAllUsers() {  
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u");  
        return q.getResultList();  
    }  
    
    public Collection<Utilisateur> getPaginatedUsers(int pagination) {
        return getPaginatedUsers(pagination, 10);
    }
    
    public Collection<Utilisateur> getPaginatedUsers(int pagination, int max) {
        Query q = em.createQuery("select u from Utilisateur u ");
        q.setFirstResult(pagination);
        q.setMaxResults(max);
        return q.getResultList();
    }
    
    public int getNumberUsers() {
        Query q = em.createQuery("select u from Utilisateur u ");
        return q.getResultList().size();
    }
    
    public Utilisateur getUser(String login, String mdp) {
        Query q = em.createQuery("select u from Utilisateur u where u.mdp='"+mdp+"' and u.login='"+login+"'");
        if(!q.getResultList().isEmpty()) {
            return (Utilisateur)q.getResultList().get(0);
        }
        else {
            return null;
        }
    }
    // Add business logic below. (Right-click in editor and choose  
    // "Insert Code > Add Business Method")  
}  