<%-- 
    Document   : pagejsp
    Created on : 4 avr. 2017, 15:20:04
    Author     : Aicha
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>   
  
<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
                        
            <c:if test="${requestScope['user'] == null}">
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
                <div class="form-group"><label class="control-label col-sm-2">Nom : </label><div class="col-sm-10"><input type="text" name="nom"/></div></div><br>  
                <div class="form-group"><label class="control-label col-sm-2">Prénom :</label><div class="col-sm-10"> <input type="text" name="prenom"/></div></div><br>  
                <div class="form-group"><label class="control-label col-sm-2">Login :</label><div class="col-sm-10"> <input type="text" name="login"/></div></div><br>  
                <div class="form-group"><label class="control-label col-sm-2">MDP :</label><div class="col-sm-10"> <input type="password" name="mdp"/></div></div><br>
                <div class="form-group"><label class="control-label col-sm-2">numeroEtRue :</label><div class="col-sm-10"> <input type="text" name="numeroEtRue"/></div></div><br>
                <div class="form-group"><label class="control-label col-sm-2">codePostal :</label><div class="col-sm-10"> <input type="text" name="codepostal"/></div></div><br>
                <div class="form-group"><label class="control-label col-sm-2">ville :</label><div class="col-sm-10"> <input type="text" name="ville"/></div></div><br>
                <div class="form-group"><label class="control-label col-sm-2">pays :</label><div class="col-sm-10"> <input type="text" name="pays"/></div></div><br>
                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                <input type="hidden" name="action" value="ajouterUtilisateur"/>  
                <input type="submit" value="Créer l'utilisateur" name="submit" onclick="ajouterUtilisateur()" class="btn btn-default"/>  
                </form> 
            </c:if>  
            <c:if test="${requestScope['user'] != null}">  
            
            <c:if test="${param['action'] == 'rechercherUtilisateur'}">
             <h3>rechercher des utilistaeurs</h3> 
             <form action="ServletUsers" method="get" class="form-horizontal"> 
                 <input type="hidden" name="action" value="rechercherUtilisateur"/>  
                 <div class="form-group"><label class="control-label col-sm-2">Nom : </label><div class="col-sm-10"><input type="text" name="nom"/></div></div><br>  
                <div class="form-group"><label class="control-label col-sm-2">Prénom :</label><div class="col-sm-10"> <input type="text" name="prenom"/></div></div><br>  
                <div class="form-group"><label class="control-label col-sm-2">Login :</label><div class="col-sm-10"> <input type="text" name="login"/></div></div><br>  
                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                <input type="hidden" name="post" value="sendRequest"/>
                <input type="submit" value="rechercher l'utilisateur" name="submit" class="btn btn-default" />  

              </form> 
            </c:if> 
             
            <c:if test="${param['action'] == 'detailUtilisateur'}"> 
            <h3>Afficher les détails d'un utilisateur</h3>  
            <form action="ServletUsers" method="get" class="form-horizontal">  
                <div class="form-group"><label class="control-label col-sm-2">login :</label><div class="col-sm-10"> <input type="text" name="login"/></div></div><br>  
                <input type="hidden" name="action" value="detailUtilisateur"/> 
                <input type="hidden" name="post" value="sendRequest"/>
                <input type="submit" value="Chercher" name="submit" class="btn btn-default"/>  
            </form>
            </c:if> 
  
            <c:if test="${param['action'] == 'updateUtilisateur'}">
            <h3>Modifier les détails d'un utilisateur :</h3>  
            <form action="ServletUsers" method="get" class="form-horizontal">  
                <div class="form-group"><label class="control-label col-sm-2">Login :</label><div class="col-sm-10"> <input type="text" name="login"/></div></div><br>  
                <div class="form-group"><label class="control-label col-sm-2">MDP : </label><div class="col-sm-10"><input type="password" name="mdp"/></div></div><br>
                <div class="form-group"><label class="control-label col-sm-2">Nom : </label><div class="col-sm-10"><input type="text" name="nom"/></div></div><br>  
                <div class="form-group"><label class="control-label col-sm-2">Prénom : </label><div class="col-sm-10"><input type="text" name="prenom"/></div></div><br>
                
                <input type="hidden" name="action" value="updateUtilisateur"/>  
                <input type="submit" value="Mettre à jour" name="submit" class="btn btn-default"/>  
            </form>
            </c:if> 
        
            <c:if test="${param['action'] == 'deleteUtilisateur'}">
            <h3>Supprimer un utilisateur :</h3>  
            <form action="ServletUsers" method="get" class="form-horizontal">  
                <div class="form-group"><label class="control-label col-sm-2">Login :</label><div class="col-sm-10"> <input type="text" name="login"/></div></div><br>    
                <input type="hidden" name="action" value="deleteUtilisateur"/>  
                <input type="submit" value="supprimer" name="submit" class="btn btn-default"/>  
            </form>
            </c:if> 
  
        <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes -->  
        <c:if test="${param['action'] == 'listerLesUtilisateurs'}" >  
            <h2>Liste des utilisateurs</h2>  
  
            <table border="10" class="table">  
                <!-- La ligne de titre du tableau des comptes -->  
                <thead>
                <tr>  
                    <td><b>Login</b></td>  
                    <td><b>Préom</b></td>  
                    <td><b>Nom</b></td>
                    <td><b>Adresses</b></td>
                </tr>  
                </thead>
  
                <!-- Ici on affiche les lignes, une par utilisateur -->  
                <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                <c:set var="total" value="0"/>  
                <tbody>
                <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                    <tr>  
                        <td>${u.login}</td>  
                        <td>${u.firstname}</td>  
                        <td>${u.lastname}</td>
                        <c:if test="${u.adresses.size()<=1}">
                            <td><a href="ServletAdresses?action=listerLesAdresses&pagination=0&loginUser=${u.login}">${u.adresses.size()} adresse</a></td>
                        </c:if>
                        <c:if test="${u.adresses.size()>1}">
                            <td><a href="ServletAdresses?action=listerLesAdresses&pagination=0&loginUser=${u.login}">${u.adresses.size()} adresses</a></td>
                        </c:if>
                        <c:set var="total" value="${total+1}"/> 
                        <!-- On compte le nombre de users -->  
                         
                    </tr>  
                    
                </c:forEach> 
  
                <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                <tr><td><b>TOTAL</b></td><td></td><td></td><td><b>${total}/${requestScope['nombreUsers']}</b></td></tr>  
                </tbody>

            </table>  
            <ul class="pagination">
            <c:forEach var="i" begin="0" end="${requestScope['nombreUsers']/10}">
                <li><a href="ServletUsers?action=listerLesUtilisateurs&pagination=${i*10}">${i}</a></li>
            </c:forEach>
            </ul>
            </c:if>  
        </c:if>
            
        
  
        
