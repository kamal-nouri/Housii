<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Abood
  Date: 1/22/2021
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- load Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerstyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css">
    <title>Login Page</title>
</head>
<body class="main-bg">
<div class="login-container text-c animated flipInX">
    <div>
        <h1 class="logo-badge text-whitesmoke"><span class="fa fa-user-circle"></span></h1>
    </div>
    <h3 class="text-whitesmoke">Sign In</h3>
    <div class="container-content">
<form:form method="POST" action="/loginuser" modelAttribute="user">
    <div class="form-group">
        <form:input cssClass="form-control" id="email" placeholder="Email" type="email" path="email"/>
    </div>

    <div class="form-group">
        <form:password cssClass="form-control" id="password" placeholder="Password" path="password"/>
    </div>

    <input type="submit" class="form-button button-l margin-b" value="Sign In"/>
</form:form>
    <p style="font-family: cursive;"><c:out value="${error}" /></p>
    <p style="font-family: cursive;"><c:out value="${message}" /></p>
        <a href="/register">Do not have an account ?</a>
        <br><br>
        <p style="font-family: cursive;">Find a Home you Love! &copy; 2021</p>
    </div>
</div>
</body>
</html>
