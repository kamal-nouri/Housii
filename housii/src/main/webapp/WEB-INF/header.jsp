<%--
  Created by IntelliJ IDEA.
  User: Abood
  Date: 1/22/2021
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link href="${pageContext.request.contextPath}/css/style3.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg py-3 navbar-dark bg-dark shadow-sm" style="background-color: #151414 !important;">
    <div class="container">
        <a href="/" class="navbar-brand">
            <!-- Logo Image -->
            <img src="${pageContext.request.contextPath}/img/HousiiLogo.png" width="200" alt="logo" class="d-inline-block align-middle mr-2">
            <!-- Logo Text -->
            <span class="text-uppercase font-weight-bold">Housii</span>
        </a>

        <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent"aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"class="navbar-toggler"><span class="navbar-toggler-icon"></span></button>

        <div id="navbarSupportedContent" class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="#" class="nav-link">Houses <span class="sr-only">(current)</span></a></li>
                <li class="nav-item active"><a href="#" class="nav-link">Studios</a></li>
                <li class="nav-item active"><a href="#" class="nav-link">Villa</a></li>
                <li class="nav-item active"><a href="#" class="nav-link">Login</a></li>
                <li class="nav-item active"><a href="#" class="nav-link">Sign Up</a></li>
                <!-- <li class="nav-item active"><a href="/meals/cart" class="nav-link">Cart</a></li>
                <li class="nav-item active"><a href="/logout" class="nav-link">Logout</a></li> -->
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
