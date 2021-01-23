<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Abood
  Date: 1/21/2021
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- load Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerstyle.css">
    <title>Register Page</title>
</head>
<body>
<div class="login-container text-c animated flipInX">
    <div>
        <h1 class="logo-badge text-whitesmoke"><span class="fa fa-user-circle"></span></h1>
    </div>
    <h3 class="text-whitesmoke">Sign Up</h3>
    <div class="container-content">
    <form:form id="signup" method="POST" action="/process_register" modelAttribute="user" cssClass="margin-t">
        <div class="form-group">
            <form:input cssClass="form-control" id="name" placeholder=" Name" path="name"/>
        </div>
        <div class="form-group">
            <form:input type="email" cssClass="form-control" id="email" placeholder="Email" path="email"/>
        </div>

        <div class="form-group">
            <form:password cssClass="form-control" id="password" placeholder="Password" path="password"/>
        </div>
        <div class="form-group">
            <form:password cssClass="form-control" id="passwordConfirmation" placeholder="Confirm Password" path="passwordConfirmation"/>
        </div>
        <input class="form-button button-l margin-b" type="submit" value="Sign Up"/>
    </form:form>
        <div>
            <p><form:errors cssStyle="font-family: cursive;" path="user.*"/></p>
            <p><c:out value="${message}" /></p>
        </div>
        <a href="/login">already have an account!</a>
        <br><br>
        <p style="font-family: cursive;">Find a Home you Love! &copy; 2021 </p>
    </div>
</div>
</body>
</html>
