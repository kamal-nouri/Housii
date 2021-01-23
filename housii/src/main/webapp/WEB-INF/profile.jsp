<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: msys
  Date: 1/23/2021
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
<div class="container" style="color: beige">
    <h2>Welcome,${user.name}</h2>
    <h3>Email : ${user.email}</h3>
    <h3>Phone no. : ${user.phoneNumber}</h3>
    <c:forEach items="${user.houses}" var="house">
    <div class="main">
        <img src="${pageContext.request.contextPath}/img/${house.name}.jpg"
             alt="image for ${house.name}"/>
        <div class="right">
            <h3> ${house.name}</h3>
            <p>Location: ${house.location}</p>
            <p>Description :${house.description} </p>
            <p>Price :${house.price} </p>
        </div>
        </c:forEach>
    </div>
</body>
</html>
