<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
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
                        <a href="${pageContext.request.contextPath}" title="Ebenus"
						class="logo"> <img src="./assets/images/logo/Parclik.png"
						alt="Ebenus">
					</a>
                    </div>
                </header>
            </div>
            <!-- Section -->
            <section>
                <div class="content">
                    <div class="account-login">
                        <form action="${pageContext.request.contextPath}/LoginServlet" method="Post" id="customer-info-form"> 
                            <div class="row">
                                <div class="col-md-5 registered-users m-auto">
                                    <div>
                                        <h1>CONNECTEZ-VOUS</h1>
                                        <p>On se connait déjà ?</p>
                                        <p>Connectez-vous avec votre <strong>adresse e-mail</strong> et votre <strong>mot de passe</strong> pour accéder à votre espace client.</p>
                                        <ul class="form-list">
                                            <li>
                                                <label for="email" class="required">Email<em>*</em></label>
                                                <div class="input-box">
                                                    <input type="text" name="email" value="" id="email" class="input-text" title="Email">
                                                </div>
                                            </li>
                                            <li>
                                                <label for="pass" class="required">Mot de passe<em>*</em></label>
                                                <div class="input-box">
                                                    <input type="password" name="password" class="input-text" id="pass" title="Password">
                                                </div>
                                            </li>
                                        </ul>
                                        <p class="required-msg">* champs obligatoires</p>
                                    </div>
                                    <br>
                                    <button class="btn btn-default" type="submit">Connexion</button>
                                </div>
                            </div>
                        </form>
                    </div> 
                </div>
                <div class="content">
                     <form action="${pageContext.request.contextPath}/AddUserServlet" method="get" id="customer-info-form2"> 
                         <div class="row">
                             <div class="col-md-5 registered-users m-auto">
                             	<h1>...ou inscrivez vous</h1>
                             	<div>
                                    <ul class="form-list">
                                        <li>
                                        	<input type="hidden" name="signin" value="yes" />
                                        </li>
                                    </ul>
                               </div>
                               <br>
                               <button class="btn btn-default" type="submit">
	                      			S'inscrire 
	                 			</button>
                          </div>
                       </div>
					</form>      
				</div>
            </section>
            <!-- Footer -->
            <footer>
                <div class="footer-container ">
                    <div class="footer">
                        <div class="footer-middle">
                            <div class="footer-container_">
                            </div>
                        </div>
                        <div class="footer-bottom">
                            <div class="footer-container_">
                                <address>© Parclick. 2019. Tous droit réservé</address>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
        <!-- JS files -->
        <script src="./assets/js/bower.js" type="text/javascript"></script>
        <script src="./assets/js/application.js" type="text/javascript"></script>
    </body>
</html>
