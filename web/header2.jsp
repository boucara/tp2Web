<%@ page language="java" contentType="text/html; charset=UTF-8"  
         pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div>  
    <a href="http://miageprojet2.unice.fr/">  
        <img src="${pageContext.request.contextPath}/resources/logo_miage.png"/>  
    </a>  
    <a href="http://unice.fr/">  
        <img src="${pageContext.request.contextPath}/resources/logo_uns.png"/>  
    </a>  
    <div class="div_connexion">
        <c:if test="${requestScope['user'] != null}">
            Bonjour ${requestScope['user'].firstname} <a href="ServletUsers?action=deconnexion">Deconnexion</a>
        </c:if>
        <c:if test="${requestScope['user'] == null}">
            <form action="ServletUsers" method="post" class="form-inline">   
                <div class="form-group"><label>Login :</label> <input type="text" name="login" class="form-control"/><br></div>
                <div class="form-group"><label>MDP :</label> <input type="password" name="mdp" class="form-control"/><br></div>
                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                <input type="hidden" name="action" value="seConnecter"/> 
                <input type="submit" value="Se Connecter" name="submit" class="btn btn-default"/>  
            </form>
        </c:if>
    </div>
    
</div> 
