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
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/master.css">
</head>
<body>
	<div class="outer">
		<div class="header-outer" id="header-outer">
			<!-- Header -->
			<header id="header" class="header">
				<nav class="navbar navbar-expand-lg navbar-light">

					<div class="collapse navbar-collapse"
						style="margin-left: 250px; display: inline-block;">

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
									href="${pageContext.request.contextPath}/LogsServlet">SQL Logs</a></li>
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
		<!-- Section -->
		<section>
			<div class="content">
				<div class="User quest">
					<h1>Enregistrer un véhicule</h1>
					<form action="${pageContext.request.contextPath}/AddVoitureServlet"
						method="Post" id="customer-info-form" class="no-gutters">
						<div class="account-container row">
							<fieldset class=" common-form-controls col-md-3">

								<div class="sel inputwrapper">
									<label for="brand">Modèles<em>*</em></label> <select
										class="required" name="brand" id="brand">
										<option value="rôle" selected disabled>Marque</option>
										<c:forEach items="${all_brand}" var="item" varStatus="loop">
											<option value="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>

								<div class="sel inputwrapper">
									<label for="owner">Propriétaire<em>*</em></label> <select
										class="required" name="owner" id="owner">
										<option value="rôle" selected disabled>Choisir un
											utilisateur</option>
										<c:forEach items="${users}" var="item" varStatus="loop">
											<option value="${item.getIdUtilisateur()}">${item.getPrenom()}</option>
										</c:forEach>
									</select>
								</div>

								<div class="input-wrapper">
									<label for="lastname">Immatriculation<em>*</em></label> <input
										autocomplete="off" name="immatriculation" id="immatriculation"
										type="text">
								</div>
							</fieldset>

						</div>

						<div class="row">
							<div class="col-md-5">
								<p class="obligatoire">les champs avec la signe "*" sont
									obligatoire</p>
							</div>
							<div class="actions col-md-4">
								<button type="submit">Ajouter</button>
							</div>
						</div>
					</form>
				</div>
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
	<script src="assets/js/bower.js" type="text/javascript"></script>
	<script src="assets/js/application.js" type="text/javascript"></script>
</body>
</html>
