<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.cours.ebenus.service.GoogleCalendar" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PARK ROOM</title>
    <!-- CSS files -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/master.css">


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
                <div class="User export" style="float: right">
                    <h2 style="margin-top: 30px; display: inline-block;">
                        Bonjour <strong> Mr ${current_user.getEmail()} </strong>
                        (${current_user.getRole().getIdentifiant()})
                    </h2>

                    <h1 class="logout">
                        <a href="<c:url value="LogoutServlet"/>" role="button">Se
                            Deconnecter</a>
                    </h1>
                </div>
            </div>
       </header>
    </div>
    <!-- Section -->
    <section>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">#</th>
                <c:forEach items="${getDates.entrySet()}" var="dates">
                    <th><c:out value="${dates.getValue().getAsString()}"/></th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${getParkings}" var="park">
                <tr>
                    <th>Place parking n°<c:out value="${park.getNum()}"/></th>
                    <c:forEach items="${getDates.entrySet()}" var="dates">
                        <c:choose>
                            <c:when test="${!GoogleCalendar.isBooked(park.getNum(),dates.getKey())}">
                                <form action="${pageContext.request.contextPath}/CrudParkingServlet" method="Post">
                                <td><button type="submit" class="badge-success">Reserver</button></td>
                                    <input type="hidden" value="${dates.getKey()}" name="date">
                                    <input type="hidden" value="${park.getNum()}" name="num">
                                    <input type="hidden" value="${current_user.getEmail()}" name="userEmail">
                                </form>
                            </c:when>
                            <c:when test="${GoogleCalendar.isBookByUser(current_user.getEmail(),park.getNum(),dates.getKey()) }">
                                <form action="${pageContext.request.contextPath}/DeleteParkingServlet" method="post">
                                <td><button type="submit" class="badge-danger">Cancel</button></td>
                                    <input type="hidden" value="${dates.getKey()}" name="date">
                                    <input type="hidden" value="${park.getNum()}" name="num">
                                    <input type="hidden" value="${current_user.getEmail()}" name="userEmail">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <td>${GoogleCalendar.UserBook(park.getNum(),dates.getKey())}</td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
