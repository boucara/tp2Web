/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.Gestionnaires;

import java.util.ArrayList;
import java.util.Collection;  
import javax.ejb.Stateless;  
import javax.persistence.EntityManager;  
import javax.persistence.PersistenceContext;  
import javax.persistence.Query;  
import utilisateurs.modeles.Adresse;
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
        ArrayList<Adresse> adresses = new ArrayList<Adresse>();
        adresses.add(new Adresse("Rue des templiers", "06510", "Sophia", "France"));
        creeUtilisateur("John", "Lennon", "jlennon", "test", adresses);
        creeUtilisateur("Paul", "Mac Cartney", "pmc", "test", adresses);
        creeUtilisateur("Ringo", "Starr", "rstarr", "test", adresses);
        creeUtilisateur("Georges", "Harisson", "georgesH", "test", adresses);
        creeUtilisateur("Rivera", "Garth", "RiveraGarth", "test", adresses);
        creeUtilisateur("Joyce", "Amery", "JoyceAmery", "test", adresses);
        creeUtilisateur("Saunders", "Micah", "SaundersMicah", "test", adresses);
        creeUtilisateur("Mccarty", "Fritz", "MccartyFritz", "test", adresses);
        creeUtilisateur("Blake", "Perry", "BlakePerry", "test", adresses);
        creeUtilisateur("Jenkins", "Maxwell", "JenkinsMaxwell", "test", adresses);
        creeUtilisateur("Burch", "Geoffrey", "BurchGeoffrey", "test", adresses);
        creeUtilisateur("Roberson", "Lane", "RobersonLane", "test", adresses);
        creeUtilisateur("Carey", "Baxter", "CareyBaxter", "test", adresses);
        creeUtilisateur("Bishop", "Samson", "BishopSamson", "test", adresses);
        creeUtilisateur("Carver", "Odysseus", "CarverOdysseus", "test", adresses);
        creeUtilisateur("Daniel", "Blaze", "DanielBlaze", "test", adresses);
        creeUtilisateur("Barlow", "Isaiah", "BarlowIsaiah", "test", adresses);
        creeUtilisateur("Landry", "Raymond", "LandryRaymond", "test", adresses);
        creeUtilisateur("Bruce", "Gray", "BruceGray", "test", adresses);
        creeUtilisateur("Bryant", "Merrill", "BryantMerrill", "test", adresses);
        creeUtilisateur("Little", "Grant", "LittleGrant", "test", adresses);
        creeUtilisateur("Glass", "Judah", "GlassJudah", "test", adresses);
        creeUtilisateur("Rodgers", "Bevis", "RodgersBevis", "test", adresses);
        creeUtilisateur("Love", "Channing", "LoveChanning", "test", adresses);
        creeUtilisateur("Walters", "Flynn", "WaltersFlynn", "test", adresses);
        creeUtilisateur("Mitchell", "Gareth", "MitchellGareth", "test", adresses);
        creeUtilisateur("Hale", "Ronan", "HaleRonan", "test", adresses);
        creeUtilisateur("Griffith", "Cameron", "GriffithCameron", "test", adresses);
        creeUtilisateur("Little", "Thomas", "LittleThomas", "test", adresses);
        creeUtilisateur("Valenzuela", "Leroy", "ValenzuelaLeroy", "test", adresses);
        creeUtilisateur("Glass", "Hakeem", "GlassHakeem", "test", adresses);
        creeUtilisateur("Morales", "Keith", "MoralesKeith", "test", adresses);
        creeUtilisateur("Bradford", "Mohammad", "BradfordMohammad", "test", adresses);
        creeUtilisateur("Ellison", "Vaughan", "EllisonVaughan", "test", adresses);  
    }  
  
    public Utilisateur creeUtilisateur(String nom, String prenom, String login, String mdp, ArrayList<Adresse> adresses ) {  
        
        Utilisateur u = new Utilisateur(nom, prenom, login, mdp, adresses);  
        em.persist(u);  
        return u;  
    } 
    public void ajouterUtilisateur(String nom, String prenom, String login, String mdp, String numeroEtRue, String codePostal , String ville,String pays){
       ArrayList<Adresse> adresses = new ArrayList<Adresse>();
       Adresse  adresse = new Adresse(numeroEtRue, codePostal, ville,  pays) ;
       adresses.add(adresse);
       Utilisateur u = creeUtilisateur(nom,  prenom,  login, mdp, adresses);
        
        //Collection<Utilisateur> listeUtilisateurs = getAllUsers();
        //listeUtilisateurs.add(u);
    }
    public Collection<Utilisateur> rechercherUtilisateurs(String nom, String prenom, String login) {
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
    public void delete (String nom, String prenom, String login, String mdp){
        Query q= em.createQuery("delete from Utilisateur ut where ut.login=:login  "); 
            q.setParameter("login", login);
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
    
    public Collection<Utilisateur> getUser(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login='"+login+"'");
        if(!q.getResultList().isEmpty()) {
            return q.getResultList();
        }
        else {
            return null;
        }
    }
    // Add business logic below. (Right-click in editor and choose  
    // "Insert Code > Add Business Method")  

    public Collection<Adresse> getAdresses(String loginUser, int pagination) {
        Query q = em.createQuery("select u.adresses from Utilisateur u where u.login='"+loginUser+"'");
        q.setFirstResult(pagination);
        q.setMaxResults(10);
        if(!q.getResultList().isEmpty()) {
            return q.getResultList();
        }
        else {
            return null;
        }
    }
    
    public int getNbAdresses(String loginUser) {
        Query q = em.createQuery("select u.adresses from Utilisateur u where u.login='"+loginUser+"'");
        return q.getResultList().size();
    }
}  