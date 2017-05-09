<%-- 
    Document   : pagejsp
    Created on : 4 avr. 2017, 15:20:04
    Author     : Aicha
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>   
  
<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
   
        <div class="panel panel-primary">
            <div class="panel-heading">Menu de gestion des utilisateurs</div>
            <div class="panel-body">
                 <div class="list-group">
                    <a href="ServletUsers?action=listerLesUtilisateurs&pagination=0" class="list-group-item">Afficher les utilisateurs</a>
                    <a href="ServletUsers?action=creerUtilisateursDeTest&pagination=0" class="list-group-item">Créer 34 utilisateurs de test</a>
                    <a href="ServletUsers?action=ajouterUtilisateur" class="list-group-item">Créer un utilisateur</a>
                    <a href="ServletUsers?action=rechercheUtilisateurs" class="list-group-item">rechercher des utilistaeurs</a>
                    <a href="ServletUsers?action=detailUtilisateur" class="list-group-item">Afficher les détails d'un utilisateur</a>
                    <a href="ServletUsers?action=modifierUtilisateur" class="list-group-item">Modifier les détails d'un utilisateur</a>
                    <a href="ServletUsers?action=supprimerUtilisateur" class="list-group-item">Supprimer un utilisateur</a>
                </div>
            </div>
        </div>
            
        <div class="right">
                        
            <c:if test="${requestScope['user'] == null || param['action'] != 'ajouterUtilisateur'}">
            <h2>Liste des fonctionnalités à implémenter dans la Servlet (note : après chaque action cette page sera  
                rappelée par la servlet avec la liste des utilisateurs raffraichie et un message de confirmation</h2>
            
                Connectez vous pour accéder à toutes les fonctionnalités
            </c:if>
                
            
            <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->  
            <c:if test="${!empty param['message']}">  
                <h2>Reçu message : ${param.message}</h2>  
            </c:if> 
                
            <c:if test="${param['action'] == 'ajouterUtilisateur'}">
                <h3>Créer un utilisateur</h3>  
                <form action="ServletUsers" method="get">  
                Nom : <input type="text" name="nom"/><br>  
                Prénom : <input type="text" name="prenom"/><br>  
                Login : <input type="text" name="login"/><br>  
                MDP : <input type="password" name="mdp"/><br>
                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                <input type="hidden" name="action" value="ajouterUtilisateur"/>  
                <input type="submit" value="Créer l'utilisateur" name="submit" onclick="ajouterUtilisateur()"/>  
                </form> 
            </c:if>  
            <c:if test="${requestScope['user'] != null}">  
            
            <c:if test="${param['action'] == 'rechercheUtilisateurs'}">
             <h3>rechercher des utilistaeurs</h3> 
             <form action="ServletUsers" method="get"> 
                 <input type="hidden" name="action" value="rechercherUtilisateur"/>  
                 Nom : <input type="text" name="nom"/><br>  
                Prénom : <input type="text" name="prenom"/><br>  
                Login : <input type="text" name="login"/><br>  
                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                <input type="submit" value="rechercher l'utilisateur" name="submit" />  

              </form> 
            </c:if> 
             
            <c:if test="${param['action'] == 'detailUtilisateur'}"> 
            <h3>Afficher les détails d'un utilisateur</h3>  
            <form action="ServletUsers" method="get">  
                login : <input type="text" name="login"/><br>  
                <input type="hidden" name="action" value="chercherParLogin"/>  
                <input type="submit" value="Chercher" name="submit"/>  
            </form>
            </c:if> 
  
            <c:if test="${param['action'] == 'modificationUtilisateur'}">
            <h3>Modifier les détails d'un utilisateur :</h3>  
            <form action="ServletUsers" method="get">  
                Login : <input type="text" name="login"/><br>  
                MDP : <input type="password" name="mdp"/><br>
                Nom : <input type="text" name="nom"/><br>  
                Prénom : <input type="text" name="prenom"/><br>  
                <input type="hidden" name="action" value="updateUtilisateur"/>  
                <input type="submit" value="Mettre à jour" name="submit"/>  
            </form>
            </c:if> 
        
            <c:if test="${param['action'] == 'supprimerUtilisateur'}">
            <h3>Supprimer un utilisateur :</h3>  
            <form action="ServletUsers" method="get">  
                Login : <input type="text" name="login"/><br>  
                MDP : <input type="password" name="mdp"/><br>
                Nom : <input type="text" name="nom"/><br>  
                Prénom : <input type="text" name="prenom"/><br>  
                <input type="hidden" name="action" value="deleteUtilisateur"/>  
                <input type="submit" value="supprimer" name="submit"/>  
            </form>
            </c:if> 
  
        <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes -->  
        <c:if test="${param['action'] == 'listerLesUtilisateurs'}" >  
            <h2>Liste des utilisateurs</h2>  
  
            <table border="10">  
                <!-- La ligne de titre du tableau des comptes -->  
                <tr>  
                    <td><b>Login</b></td>  
                    <td><b>Préom</b></td>  
                    <td><b>Nom</b></td>  
                </tr>  
  
                <!-- Ici on affiche les lignes, une par utilisateur -->  
                <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                <c:set var="total" value="0"/>  
  
                <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                    <tr>  
                        <td>${u.login}</td>  
                        <td>${u.firstname}</td>  
                        <td>${u.lastname}</td>  
                        <!-- On compte le nombre de users -->  
                        <c:set var="total" value="${total+1}"/>  
                    </tr>  
                </c:forEach>  
  
                <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                <tr><td><b>TOTAL</b></td><td></td><td><b>${total}/${requestScope['nombreUsers']}</b></td><td></td></tr>  
            </table>  
            <c:forEach var="i" begin="0" end="${requestScope['nombreUsers']/10}">
                <a href="ServletUsers?action=listerLesUtilisateurs&pagination=${i*10}">${i}</a>
            </c:forEach>
            </c:if>  
        </c:if>
        </div>
            
        
  
        
