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
    <title>Title</title>
</head>
<body>
<h1>Login</h1>
<p><c:out value="${error}" /></p>
<p><c:out value="${message}" /></p>
<form:form method="POST" action="/loginuser" modelAttribute="user">
    <p>
        <form:label path="email">Email:</form:label>
        <form:input type="email" path="email"/>
    </p>

    <p>
        <form:label path="password">Password:</form:label>
        <form:password path="password"/>
    </p>

    <input type="submit" value="login!"/>
</form:form>
</body>
</html>
