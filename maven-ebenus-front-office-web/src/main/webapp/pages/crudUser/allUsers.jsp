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
                        <a href="${pageContext.request.contextPath}" title="Ebenus" class="logo"> 
                            <img src="./assets/images/logo/Parclik.png" alt="Ebenus"> 
                        </a>
                    </div>
                </header>
            </div>
            <!-- Section -->
            <section>
                <div class="content">
                    <div class="User export">
                        <h2 style="margin-top: 20px;display: inline-block;"> Bonjour <strong> Mr ${current_user.getNom()} </strong> avec le rôle ${current_user.getRole().getIdentifiant()}.</h2>

                        <h1 class="logout"> <a href="<c:url value="LogoutServlet"/>"  role="button">Se Deconnecter</a></h1> 
                       
                        <h1 id="user_list_title" style="cursor: pointer;"class="clearfix"> <i id="icon-plus-1" class="fa fa-plus"></i> liste des utilisateurs</h1>

                        <div id="user_list" class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Civilite</th>
                                        <th>Prénom</th>
                                        <th>Nom</th>
                                        <th>Identifiant</th>
                                        <th>Date création</th>
                                        <th>Date modification</th>
                                        <th>Identifiant rôle</th>
                                        <th>Description rôle</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
	                                <c:forEach items = "${users}" var = "user">
	                                	<tr> 
							        		<td> <c:out value = "${user.getCivilite()}"/> </td>
								        	<td> <c:out value = "${user.getPrenom()}"/> </td>
								        	<td> <c:out value = "${user.getNom()}"/> </td>
								        	<td> <c:out value = "${user.getEmail()}"/> </td>
								        	<td> <c:out value = "${user.getDateCreation()}"/> </td>
								        	<td> <c:out value = "${user.getDateModification()}"/> </td>
								        	<td> <c:out value = "${user.getRole().getIdentifiant()}"/> </td>
								        	<td> <c:out value = "${user.getRole().getDescription()}"/> </td>
								        	<td> 
								        		<a href="<c:url value="/UpdateUserServlet?user=${user.getIdUtilisateur()}"/>"><i class="fa fa-edit"></i></a>
								        		<a href="<c:url value="/DeleteUserServlet?user=${user.getIdUtilisateur()}"/>" class="no-style-btn">
								        			<i class="fa fa-trash-o"></i>
												</a>
							        		</td>
						          		</tr>
							        </c:forEach>
                                </tbody>
                            </table>
                            <ul class="User">
                                <li>
                                    <!-- <a href="#" role="button">Import d'utilisateurs à partir d'un fichier CSV</a> -->
                                    <div class="col-lg-12 no-padding">
                                        <a href="AddUserServlet" role="button" type="submit">Ajouter Utilisateur</a>
                                    </div>
                                </li>
                            </ul>
                        </div>

                    </div> 
                </div>
            </section>
            
            <section>
            	<div class="content">
            		<h1 id="parking_list_title" style="cursor: pointer;"class="clearfix"> <i id="icon-plus-2"class="fa fa-plus"></i> liste des places disponibles</h1>
            		
            		<div id="parking_list" class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Voiture</th>
                                        <th>Numéro de place</th>
                                        <th>Disponible</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
	                                <c:forEach items = "${parkings}" var = "parking">
	                                	<tr> 
							        		<td> 
							        			<c:choose>
								        			<c:when test="${empty parking.getIdVoiture()}">
								        				N/A
							        				</c:when>
							        				<c:otherwise>
										        			${parking.getIdVoiture()}
									        		</c:otherwise>
								        		</c:choose>
							        		 </td>
								        	<td> <c:out value = "${parking.getNum()}"/> </td>
								        	<td>
									        	<c:choose>
									        		<c:when test="${parking.getAvailable()}">
									        			Oui 
								        			</c:when>
									        		<c:otherwise>
									        			Non 
									        		</c:otherwise>
								        		</c:choose>
								        	</td>
							        		<td>
							        			<!-- TODO 2 servlets a faire ici: update et delete des places   -->
								        		<a href="<c:url value="/UpdateUserServlet?user=${user.getIdUtilisateur()}"/>"><i class="fa fa-edit"></i></a>
								        		<a href="<c:url value="/DeleteUserServlet?user=${user.getIdUtilisateur()}"/>" class="no-style-btn">
								        			<i class="fa fa-trash-o"></i>
												</a>
							        		</td>
						          		</tr>
							        </c:forEach>
                                </tbody>
                            </table>
                            <!-- TODO 1 servlet a faire ici: create places   -->
                            <ul class="User">
                                <li>
                                    <div class="col-lg-12 no-padding">
                                        <a href="AddUserServlet" role="button" type="submit">Ajouter une place de parking</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
            	</div>
            </section>
            
            <section>
            	<div class="content">
            		<h1 id="voiture_list_title" style="cursor: pointer;"class="clearfix"> <i id="icon-plus-3" class="fa fa-plus"></i> liste des voitures</h1>
            		
            		<div id="voiture_list" class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Propriétaire</th>
                                        <th>Modèle</th>
                                        <th>Immatriculation</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
	                                <c:forEach items = "${voitures}" var = "voiture">
	                                	<tr> 
	                                		<td>
	                                			<c:out value="${voiture.getUtilisateur()}"/>
							        		</td>
							        		<td>
	                                			<c:out value="${voiture.getMarque()}"/>
							        		</td>
							        		<td>
	                                			<c:out value="${voiture.getImmattriculation()}"/>
							        		</td>
							        		<td>
							        			<!-- TODO 2 servlets a faire ici: update et delete des voitures   -->
								        		<a href="<c:url value="/UpdateUserServlet?user=${user.getIdUtilisateur()}"/>"><i class="fa fa-edit"></i></a>
								        		<a href="<c:url value="/DeleteUserServlet?user=${user.getIdUtilisateur()}"/>" class="no-style-btn">
								        			<i class="fa fa-trash-o"></i>
												</a>
							        		</td>
						          		</tr>
							        </c:forEach>
                                </tbody>
                            </table>
                            <!-- TODO 1 servlet a faire ici: create voiture   -->
                            <ul class="User">
                                <li>
                                    <div class="col-lg-12 no-padding">
                                        <a href="AddUserServlet" role="button" type="submit">Ajouter un véhicule</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
            	</div>
            </section>
            <!-- Footer -->
            <footer>
                <div class="footer-container ">
                    <div class="footer">
                        <div class="footer-middle">
                            <div class="footer-container_">
                                <div class="row no-gutters"> 
                                    <div class="col-sm-6 col-md-3">
                                        <div class="block">
                                            <div class="block-title"><strong><span>Contactez Nous</span></strong></div>
                                            <div class="block-content">
                                                <ul class="contact-info">
                                                    <li><i class="icon-location">&nbsp;</i><p><b>Addresse:</b><br>123 Rue la victoire, 75000 Paris, France</p></li>
                                                    <li><i class="icon-phone">&nbsp;</i><p><b>Tél:</b><br>(+33) 00 11 00 11 00</p></li>
                                                    <li><i class="icon-mail">&nbsp;</i><p><b>Email:</b><br><a href="mailto:mail@example.com">mail@example.com</a></p></li>
                                                    <li><i class="icon-clock">&nbsp;</i><p><b>Horaire : </b><br>Lundi au Samedi</p></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-3"><div class="block">
                                            <div class="block-title"><strong><span>Mon compte</span></strong></div>
                                            <div class="block-content">
                                                <ul class="links">
                                                    <li><i class="icon-right-dir theme-color"></i><a href="#" title="A propos de nous">Mon compte</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>                
                                    <div class="col-sm-6 col-md-3"><div class="block">
                                            <div class="block-title"><strong><span>Information</span></strong></div>
                                            <div class="block-content">
                                                <ul class="features">
                                                    <li><i class="icon-ok theme-color"></i><a href="#">Les informations</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-3">
                                        <div class="block">
                                            <div class="block-title"><strong><span>Nos Services</span></strong></div>
                                            <div class="block-content">
                                                <ul class="features">
                                                    <li><i class="icon-ok  theme-color"></i><a href="#">Service Client</a></li>
                                                    <li><i class="icon-ok  theme-color"></i><a href="#">Politique d'Utilisation</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>              
                                </div>
                            </div>
                        </div>
                        <div class="footer-bottom">
                            <div class="footer-container_">                
                                <address>© Ebenus. 2019. Tous droit réservé</address>
                            </div>
                        </div>
                    </div>
                </div>   
            </footer>
            <!-- JS files -->
            <script src="./assets/js/bower.js" type="text/javascript"></script>
            <script src="./assets/js/application.js" type="text/javascript"></script>
            <script src="./assets/js/events.js" type="text/javascript"></script>
        </div>   
    </body>
</html>
