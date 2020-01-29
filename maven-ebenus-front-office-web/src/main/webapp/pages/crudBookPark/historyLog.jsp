<%--
  Created by IntelliJ IDEA.
  User: oraph
  Date: 29/01/2020
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Liste de vos reservations</title>
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
<!-- Section -->
<section>
    <div class="jumbotron" style="margin: 0 auto">
        <h1 class="display-4">L'historique de vos réservations</h1>
        <hr class="my-4">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Place de parking</th>
                <th scope="col">Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${getHistory}" var="history">
                <tr>
                    <td><c:out value="${history.getPlaceParking().getNum()}"/></td>
                    <td><c:out value="${history.getBookTime().toString().substring(0,10)}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<!-- JS files -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
