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
  
        <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes -->  
        <c:if test="${param['action'] == 'listerLesAdresses'}" >  
            <h2>Liste des utilisateurs</h2>  
  
            <table border="10" class="table">  
                <!-- La ligne de titre du tableau des comptes -->  
                <thead>
                <tr>  
                    <td><b>Rue</b></td>  
                    <td><b>CP</b></td>  
                    <td><b>Ville</b></td>
                    <td><b>Pays</b></td>
                </tr>  
                </thead>
  
                <!-- Ici on affiche les lignes, une par utilisateur -->  
                <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                <c:set var="total" value="0"/>  
                <tbody>
                <c:forEach var="a" items="${requestScope['listeAdresses']}">  
                    <tr>  
                        <td>${a.numeroEtRue}</td>  
                        <td>${a.codePostal}</td>  
                        <td>${a.ville}</td>
                        <td>${a.pays}</td>
                        
                        <!-- On compte le nombre de users -->  
                         
                    </tr>  
                    <c:set var="total" value="${total+1}"/> 
                </c:forEach> 
                </tbody>
  
                <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                <tr><td><b>TOTAL</b></td><td></td><td><b>${total}/${requestScope['nombreAdresses']}</b></td></tr>  
            </table>  
            <ul class="pagination">
            <c:forEach var="i" begin="0" end="${requestScope['nombreAdresses']/10}">
                <li><a href="ServletAdresses?action=listerLesAdresses&pagination=${i*10}&loginUser=${requestScope['loginUser']}">${i}</a></li>
            </c:forEach>
            </ul>
            </c:if>  
        </c:if>
        </div>
            
        
  
        

