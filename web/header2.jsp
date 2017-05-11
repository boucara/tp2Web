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
    
    <div class="panel panel-primary">
            <div class="panel-heading">Menu de gestion des utilisateurs</div>
            <div class="panel-body">
                 <div class="list-group">
                    <a href="ServletUsers?action=listerLesUtilisateurs&pagination=0" class="list-group-item">Afficher les utilisateurs</a>
                    <a href="ServletUsers?action=creerUtilisateursDeTest&pagination=0" class="list-group-item">Créer 34 utilisateurs de test</a>
                    <a href="ServletUsers?action=ajouterUtilisateur" class="list-group-item">Créer un utilisateur</a>
                    <a href="ServletUsers?action=rechercherUtilisateur" class="list-group-item">rechercher des utilistaeurs</a>
                    <a href="ServletUsers?action=detailUtilisateur" class="list-group-item">Afficher les détails d'un utilisateur</a>
                    <a href="ServletUsers?action=updateUtilisateur" class="list-group-item">Modifier les détails d'un utilisateur</a>
                    <a href="ServletUsers?action=deleteUtilisateur" class="list-group-item">Supprimer un utilisateur</a>
                </div>
            </div>
        </div>
            
        
    
</div> 
<div class="right">