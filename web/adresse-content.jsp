<%-- 
    Document   : asdresse-content
    Created on : 11 mai 2017, 21:05:37
    Author     : Tom
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>   
  
<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
            
                        
                
            
            <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->  
            <c:if test="${!empty param['message']}">  
                <h2>Reçu message : ${param.message}</h2>  
            </c:if> 
                
        <c:if test="${requestScope['user'] != null}">  
  
        <!-- Zone qui affiche les adresses si le paramètre action vaut listerLesAdresses -->  
        <c:if test="${param['action'] == 'listerLesAdresses'}" >  
            <h2>Liste des utilisateurs</h2>  
  
            <table border="10" class="table">  
                <!-- La ligne de titre du tableau des adresses -->  
                <thead>
                <tr>  
                    <td><b>Rue</b></td>  
                    <td><b>CP</b></td>  
                    <td><b>Ville</b></td>
                    <td><b>Pays</b></td>
                    <td><b></b></td>
                    <td><b></b></td>
                    <td><b></b></td>
                </tr>  
                </thead>
  
                <!-- Ici on affiche les lignes, une par adresse -->  
                <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                <c:set var="total" value="0"/>  
                <tbody>
                <c:forEach var="a" items="${requestScope['listeAdresses']}">  
                     <c:if test="${a != null}"> 
                    <tr>
                     <form>
                         <td><input type="text" name="numeroEtRue" value="${a.numeroEtRue}"/></td>  
                        <td><input type="text" name="codePostal" value="${a.codePostal}"/></td>  
                        <td><input type="text" name="ville"  value="${a.ville}"/></td>
                        <td><input type="text" name="pays" value="${a.pays}"/></td>
                        <input type="hidden" name="action" value="updateAdresse"/>
                        <input type="hidden" name="loginUser" value="${requestScope['loginUser']}"/>
                        <td><input type="submit" value="Mettre à jour" name="submit"/></td>
                     </form>
                        <td><a href="ServletAdresses?action=listerLesAdresses&pagination=${i*10}&loginUser=${requestScope['loginUser']}&idAdresse=${a.id}">Supprimer</a></td>
                        <td></td>
                         <c:set var="total" value="${total+1}"/>
                        <!-- On compte le nombre d'adresses -->  
                         
                    </tr>  
                    </c:if> 
                </c:forEach> 
                    <tr><form><td><input type="text" name="numeroEtRue"/></td>  
                        <td><input type="text" name="codePostal" /></td>  
                        <td><input type="text" name="ville"  /></td>
                        <td><input type="text" name="pays" /></td>
                        <td></td>
                        <td></td>
                        <input type="hidden" name="action" value="ajouterAdresse"/>
                        <input type="hidden" name="loginUser" value="${requestScope['loginUser']}"/>
                        <td><input type="submit" value="ajouter" name="submit"/></td></form></tr>
                
  
                <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                <tr><td><b>TOTAL</b></td><td></td><td></td><td></td><td></td><td></td><td><b>${total}/${requestScope['nombreAdresses']}</b></td></tr> 
                </tbody>
            </table>  
            <ul class="pagination">
            <c:forEach var="i" begin="0" end="${requestScope['nombreAdresses']/10}">
                <li><a href="ServletAdresses?action=listerLesAdresses&pagination=${i*10}&loginUser=${requestScope['loginUser']}">${i}</a></li>
            </c:forEach>
            </ul>
            </c:if>  
        </c:if>
        </div>
            
        
  
        

