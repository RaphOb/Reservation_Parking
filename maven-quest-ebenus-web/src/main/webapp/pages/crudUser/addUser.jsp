<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Ebenus</title>
	<!-- CSS files -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="./assets/css/master.css"> 
</head>
<body>
	<div class="outer">
	    <div class="header-outer" id="header-outer">
	        <!-- Header -->
	        <header id="header"  class="header">
	            <div class="header padd-top">
	                <a href="${pageContext.request.contextPath}" title="Ebenus" class="logo"> 
	                    <img src="./assets/images/logo/logo.png" alt="Ebenus"> 
	                </a>
	            </div>
	        </header>
	    </div>
    </div>
	<!-- Section -->
    <section>
        <div class="content">
            <div class="account-login">
                <div class="page-title">
                    <h1>Ajouter un utilisateur</h1>
                </div>
                <form action="${pageContext.request.contextPath}/AddUserServlet" method="Post" id="customer-info-form"> 
                    <div class="row">
                        <div class="col-md-5 registered-users">
                            <div>
                                <h2>Entrez ses informations</h2>
                                <ul class="form-list">
                                	<li>
                                		<label class="required">Rôles<em>*</em></label>
                                		<div class="input-box">
                                			<c:forEach items="${roles}" var="item" varStatus="loop">
										       <input id="role${loop.index}" type="radio" name="role" value="${item.getIdRole()}"/>
										       <label for="role${loop.index}">${item.getIdentifiant()}</label>
									    	</c:forEach>
	                                    </div>
                                        
                                    </li>
                                    <li>
	                                    <label for="civilite" class="required">Civilité<em>*</em></label>
	                                    <div class="input-box">
	                                        <input type="text" name="civilite" value="" id="civilite" class="input-text" title="Civilité">
	                                    </div>
                                    </li>
                                    <li>
                                        <label for="prenom" class="required">Prénom<em>*</em></label>
                                        <div class="input-box">
                                            <input type="text" name="prenom" class="input-text" id="prenom" title="Prénom">
                                        </div>
                                    </li>
                                    <li>
                                        <label for="nom" class="required">Nom<em>*</em></label>
                                        <div class="input-box">
                                            <input type="text" name="nom" class="input-text" id="nom" title="Nom">
                                        </div>
                                    </li>
                                     <li>
                                        <label for="email" class="required">Email (identifiant de connexion)<em>*</em></label>
                                        <div class="input-box">
                                            <input type="email" name="email" class="input-text" id="email" title="Email">
                                        </div>
                                    </li>
                                     <li>
                                        <label for="pass" class="required">Mot de passe<em>*</em></label>
                                        <div class="input-box">
                                            <input type="password" name="password" class="input-text" id="pass" title="Password">
                                        </div>
                                    </li>
                                    <li>
                                        <label for="dateNaissance" class="required">Date de naissance<em>*</em></label>
                                        <div class="input-box">
                                            <input type="date" name="dateNaissance" class="input-text" id="dateNaissance" title="Date de Naissance">
                                        </div>
                                    </li>
                                </ul>
                                <p class="required-msg">* champs obligatoires</p>
                            </div>
                            <br>
                            <button class="btn btn-default" type="submit">Ajouter</button>
                        </div>
                    </div>
                </form>
            </div> 
        </div>
    </section>

</body>
</html>