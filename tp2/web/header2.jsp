<%@ page language="java" contentType="text/html; charset=UTF-8"  
         pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div>  
    <a href="${pageContext.request.contextPath}">  
        <img src="${pageContext.request.contextPath}/resources/logo.jpg"/>  
    </a>  
    <c:if test="${requestScope['user'] != null}">
        Bonjour ${requestScope['user'].firstname} <a href="ServletUsers?action=deconnexion">Deconnexion</a>
    </c:if>
    <c:if test="${requestScope['user'] == null}">
        <form action="ServletUsers" method="post">   
            Login : <input type="text" name="login"/><br>
            MDP : <input type="password" name="mdp"/><br>
            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
            <input type="hidden" name="action" value="seConnecter"/> 
            <input type="submit" value="Se Connecter" name="submit"/>  
        </form>
    </c:if>
</div> 
