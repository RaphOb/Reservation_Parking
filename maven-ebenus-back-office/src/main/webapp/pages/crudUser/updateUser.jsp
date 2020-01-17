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
				<nav class="navbar navbar-expand-lg navbar-light bg-light"
					style="background-color: #dee0ef !important;">

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
									href="${pageContext.request.contextPath}/LogsServlet">Logs</a></li>
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
					<h1>Mise à jour des données utilisateur</h1>
					<form
						action="${pageContext.request.contextPath}/UpdateUserServlet?userU=${userU.getIdUtilisateur()}"
						method="Post" id="customer-info-form" class="no-gutters">
						<div class="account-container row">
							<fieldset class=" common-form-controls col-md-3">
								<div>
									<p>Informations Personnelles</p>
									<div class="input-wrapper">
										<label for="firstname">Prénom<em>*</em></label> <input
											autocomplete="off" name="firstname" id="firstname"
											type="text" value="${userU.getPrenom()}">
									</div>
									<div class="input-wrapper">
										<label for="lastname">Nom<em>*</em></label> <input
											autocomplete="off" name="lastname" id="lastname" type="text"
											value="${userU.getNom()}">
									</div>
									<div class="input-wrapper">
										<label for="email">Email<em>*</em></label> <input
											autocomplete="off" name="email" id="email" type="email"
											value="${userU.getEmail()}">
									</div>
							</fieldset>
							<div class="offset-md-2"></div>
							<fieldset class="col-md-3">

								<div class="input-wrapper">
									<label for=""> Selectionner le rôle<em>*</em>
									</label>
								</div>
								<div class="sel-container">
									<div class="sel">
										<select class="required" name="select_role" id="select_role">
											<option value="rôle" selected disabled>Rôle</option>
											<c:forEach items="${roles}" var="item" varStatus="loop">
												<option value="${item.getIdRole()}">${item.getIdentifiant()}
												</option>
											</c:forEach>
											<option value="${userU.getRole().getIdRole()}" selected>${userU.getRole().getIdentifiant()}
											</option>
										</select>
									</div>
								</div>
								<div class="input-wrapper">
									<label>Civilité</label>
									<div class="gender">
										<input autocomplete="off" type="radio" id="male" name="sex"
											value="Mr" checked="checked" /> <label for="male"> <i
											class="fa fa-male" aria-hidden="true"></i>
										</label> <input autocomplete="off" type="radio" id="female" name="sex"
											value="Mme" /> <label for="female"> <i
											class="fa fa-female" aria-hidden="true"></i>
										</label>
									</div>
								</div>
								<div class="input-wrapper">
									<label style="display: block;"> Date de naissance<em>*</em></label>
									<input autocomplete="off" id="dteNaiss" name="dteNaiss"
										style="width: auto;" data-toggle="datepicker" type="text"
										value="${date}" name="naissance">
								</div>
							</fieldset>
						</div>
						<div class="row">
							<div class="col-md-5">
								<p class="obligatoire">les champs avec la signe "*" sont
									obligatoire</p>
							</div>
							<div class="actions col-md-4">
								<button type="submit">Mettre à jour</button>
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
							<div class="row no-gutters">
								<div class="col-sm-6 col-md-3">
									<div class="block">
										<div class="block-title">
											<strong><span>Contactez Nous</span></strong>
										</div>
										<div class="block-content">
											<ul class="contact-info">
												<li><i class="icon-location">&nbsp;</i>
													<p>
														<b>Addresse:</b><br>123 Rue la victoire, 75000 Paris,
														France
													</p></li>
												<li><i class="icon-phone">&nbsp;</i>
													<p>
														<b>Tél:</b><br>(+33) 00 11 00 11 00
													</p></li>
												<li><i class="icon-mail">&nbsp;</i>
													<p>
														<b>Email:</b><br> <a href="mailto:mail@example.com">mail@example.com</a>
													</p></li>
												<li><i class="icon-clock">&nbsp;</i>
													<p>
														<b>Horaire : </b><br>Lundi au Samedi
													</p></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-3">
									<div class="block">
										<div class="block-title">
											<strong><span>Mon compte</span></strong>
										</div>
										<div class="block-content">
											<ul class="links">
												<li><i class="icon-right-dir theme-color"></i><a
													href="#" title="A propos de nous">Mon compte</a></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-3">
									<div class="block">
										<div class="block-title">
											<strong><span>Information</span></strong>
										</div>
										<div class="block-content">
											<ul class="features">
												<li><i class="icon-ok theme-color"></i><a href="#">Les
														informations</a></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-md-3">
									<div class="block">
										<div class="block-title">
											<strong><span>Nos Services</span></strong>
										</div>
										<div class="block-content">
											<ul class="features">
												<li><i class="icon-ok  theme-color"></i><a href="#">Service
														Client</a></li>
												<li><i class="icon-ok  theme-color"></i><a href="#">Politique
														d'Utilisation</a></li>
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
	</div>
	<!-- JS files -->
	<script src="assets/js/bower.js" type="text/javascript"></script>
	<script src="assets/js/application.js" type="text/javascript"></script>
</body>
</html>
