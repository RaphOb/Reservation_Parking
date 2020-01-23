<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ebenus</title>
<!-- CSS files -->
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
				<div class="User quest">
					<h1>Créez votre compte</h1>
					<form action="${pageContext.request.contextPath}/AddUserServlet"
						method="Post" id="customer-info-form" class="no-gutters">
						<div class="account-container row">
							<fieldset class=" common-form-controls col-md-3">
								<div>
									<p>Informations Personnelles</p>

									<div class="input-wrapper">
										<label for="firstname">Prénom<em>*</em></label> <input
											autocomplete="off" name="firstname" id="firstname"
											type="text">
									</div>
									<div class="input-wrapper">
										<label for="lastname">Nom<em>*</em></label> <input
											autocomplete="off" name="lastname" id="lastname" type="text">
									</div>
									<div class="input-wrapper">
										<label for="email">Email<em>*</em></label> <input
											autocomplete="off" name="email" id="email" type="email">
									</div>
									<div class="input-wrapper">
										<label for="pass" class="required">Mot de passe<em>*</em></label>
										<div class="input-box">
											<input type="password" name="password" class="input-text"
												id="password" title="Password">
										</div>
									</div>
									<div class="input-wrapper">
										<label for="pass" class="required">Confirmer le mot de
											passe<em>*</em>
										</label>
										<div class="input-box">
											<input type="password" name="password_confirm"
												class="input-text" id="pass" title="Password">
										</div>
									</div>
							</fieldset>
							<div class="offset-md-2"></div>
							<fieldset class="col-md-3">


								<div class="sel-container">
									<c:if test="${not empty admin }">
										<div>
											<label for=""> Selectionner le rôle<em>*</em>
											</label>
											<div class="sel">
												<select class="required" name="select_role" id="select_role">
													<option value="rôle" selected disabled>Rôle</option>
													<c:forEach items="${roles}" var="item" varStatus="loop">
														<option value="${item.getIdRole()}">${item.getIdentifiant()}
														</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</c:if>
									<div class="input-wrapper">
										<label>Civilité</label>
										<div class="gender">
											<input autocomplete="off" type="radio" id="male" name="sex"
												value="Mr" checked="checked" /> <label for="male"> <i
												class="fa fa-male" aria-hidden="true"></i>
											</label> <input autocomplete="off" type="radio" id="female"
												name="sex" value="Mme" /> <label for="female"> <i
												class="fa fa-female" aria-hidden="true"></i>
											</label>
										</div>
									</div>
								</div>
								<%--                                            <div class="input-wrapper">--%>
								<%--                                                <label style="display: block;"> Date de naissance<em>*</em></label>--%>
								<%--                                                <input  autocomplete="off" id="dteNaiss" name="dteNaiss" style="width:auto;" data-toggle="datepicker" type="text" value="" name="naissance">--%>
								<%--                                            </div>--%>
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
