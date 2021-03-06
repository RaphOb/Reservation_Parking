<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ebenus</title>
<!-- CSS files -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
	
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-1/css/all.min.css">
	
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./assets/css/master.css">

</head>
<body>
	<div class="outer">
		<div class="header-outer" id="header-outer">
			<!-- Header -->
			<header id="header" class="header">
				<nav class="navbar navbar-expand-lg navbar-light">

					<div class="collapse navbar-collapse" style="margin-left: 250px;">

						<a href="${pageContext.request.contextPath}" title="Ebenus"
							class="logo"> <img src="./assets/images/logo/Parclik.png"
							alt="Ebenus">
						</a>

						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav mr-auto">
								<li class="nav-item active"><a class="nav-link"
									href="${pageContext.request.contextPath}/CrudUserServlet">Data
										management </a></li>
								<li id="logs" class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/LogsServlet">SQL
										Logs</a></li>
							</ul>
						</div>

					</div>

					<div class="content">
						<div class="User export">
							<h2 style="margin-top: 30px; display: inline-block;">
								Bonjour <strong> Mr ${current_user.getNom()} </strong>
								(${current_user.getRole().getIdentifiant()})
							</h2>

							<h1 class="logout">
								<a href="<c:url value="LogoutServlet"/>" role="button">Se
									Deconnecter</a>
							</h1>
						</div>
					</div>
				</nav>
			</header>
		</div>



		<div id="lists">
			<section>
				<div class="content">
					<h1 id="user_list_title" style="cursor: pointer;" class="clearfix">
						<i id="icon-plus-1" class="fa fa-plus" style="color: orangered;"></i>
						liste des utilisateurs
					</h1>

					<div id="user_list" class="table-responsive">
						<table id="table-user"
							class="table table-bordered table-hover table-striped">
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
									<th>Actions <a id="addUser" href="AddUserServlet"
										role="button" type="submit"> <i class="fa fa-user-plus fa-lg"
											style="color: green;"> </i>
									</a>
									</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${users}" var="user">
									<tr>
										<td><c:out value="${user.getCivilite()}" /></td>
										<td><c:out value="${user.getPrenom()}" /></td>
										<td><c:out value="${user.getNom()}" /></td>
										<td><c:out value="${user.getEmail()}" /></td>
										<td><c:out value="${user.getDateCreation()}" /></td>
										<td><c:out value="${user.getDateModification()}" /></td>
										<td><c:out value="${user.getRole().getIdentifiant()}" />
										</td>
										<td><c:out value="${user.getRole().getDescription()}" />
										</td>
										<td><a
											href="<c:url value="/UpdateUserServlet?user=${user.getIdUtilisateur()}"/>"><i
												class="fa fa-edit"></i></a> <a
											href="<c:url value="/DeleteUserServlet?user=${user.getIdUtilisateur()}"/>"
											class="no-style-btn"> <i class="fa fa-trash-o" style="color:red;"></i>
											</a> 
										
											<c:if test="${user.getRole().getIdRole() eq 3}">
												<a
													href="<c:url value="/AcceptAccountServlet?user=${user.getIdUtilisateur()}"/>"
													class="no-style-btn"> <i class="fas fa-user-check"></i>
												</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<ul class="User">
							<li>
								<div class="col-lg-12 no-padding"></div>
							</li>
						</ul>
					</div>
				</div>
			</section>

			<section>
				<div class="content">
					<h1 id="parking_list_title" style="cursor: pointer;"
						class="clearfix">
						<i id="icon-plus-2" class="fa fa-plus" style="color: orangered"></i>
						liste des places disponibles
					</h1>

					<div id="parking_list" class="table-responsive">
						<table id="table-place"
							class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>Voiture</th>
									<th>Numéro de place</th>
									<th>Disponible</th>
									<th>Actions <a id="addPlace" href="AddPlaceParkingServlet"
										role="button" type="submit"><i class="fa fa-plus fa-lg"
											style="color: green;"> </i></a>
									</th>
								</tr>

							</thead>

							<tbody>
								<c:forEach items="${parkings}" var="parking">
									<tr>
										<td><c:choose>
												<c:when test="${empty parking.getIdVoiture()}">
									        				N/A
								        				</c:when>
												<c:otherwise>
											        			${parking.getIdVoiture()}
										        		</c:otherwise>
											</c:choose></td>
										<td><c:out value="${parking.getNum()}" /></td>
										<td><c:choose>
												<c:when test="${parking.getAvailable()}">
										        			Oui 
									        			</c:when>
												<c:otherwise>
										        			Non 
										        		</c:otherwise>
											</c:choose></td>
										<td><a
											href="<c:url value="/UpdatePlaceParkingServlet?place=${parking.getNum()}&voiture=${parking.getIdVoiture()}"/>"><i
												class="fa fa-edit"></i></a> <a
											href="<c:url value="/DeletePlaceParkingServlet?place=${parking.getNum()}"/>"
											class="no-style-btn"> <i class="fa fa-trash-o" style="color:red;"></i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</section>

			<section>
				<div class="content">
					<h1 id="voiture_list_title" style="cursor: pointer;"
						class="clearfix">
						<i id="icon-plus-3" class="fa fa-plus" style="color: orangered"></i>
						liste des voitures
					</h1>

					<div id="voiture_list" class="table-responsive">
						<table id="table-voiture"
							class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>Propriétaire</th>
									<th>Modèle</th>
									<th>Immatriculation</th>
									<th>Actions <a id="addVoiture " href="AddVoitureServlet"
										role="button" type="submit"> <i class="fa fa-plus fa-lg"
											style="color: green;"> </i>
									</a>
									</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${voitures}" var="voiture">
									<tr>
										<td><c:out value="${voiture.getUtilisateur()}" /></td>
										<td><c:out value="${voiture.getMarque()}" /></td>
										<td><c:out value="${voiture.getImmattriculation()}" /></td>
										<td><a
											href="<c:url value="/UpdateVoitureServlet?voiture=${voiture.getIdVoiture()}"/>"><i
												class="fa fa-edit"></i></a> <a
											href="<c:url value="/DeleteVoitureServlet?voiture=${voiture.getIdVoiture()}"/>"
											class="no-style-btn"> <i class="fa fa-trash-o" style="color:red;"></i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</section>
		</div>
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
		<!-- JS files -->
		<script src="./assets/js/bower.js" type="text/javascript"></script>
		<script src="./assets/js/application.js" type="text/javascript"></script>
		<script src="./assets/js/events.js" type="text/javascript"></script>

		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>

		<script
			src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>

		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</div>
</body>
</html>
