<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yasmeen Muhtaseb
  Date: 1/23/2021
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Housii</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {font-family: cursive;}
        * {box-sizing: border-box;}

        input[type=text], select, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }

        input[type=submit] {
            background-color: #a273b1;
        ;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color:  rgb(168,45,168);
        }
        h3{
            margin-left: 31%;

        }

        .container {
            width: 50%;
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 2%;
            margin-left: 30%;
        }
    </style>
</head>
<body>
<h3>Add House</h3>
<div class="container">
    <p><form:errors cssStyle="font-family: cursive;" path="house.*"/></p>
<form:form method="POST" action="/add" modelAttribute="house">
    <form:label path="name">Name:</form:label>
    <form:input type="text"  path="name" placeholder="House Name ..."/>

    <form:label path="location">Location:</form:label>
    <form:input type="text"  path="location" placeholder="House Location ..."/>

    <form:label path="price">Price:</form:label>
    <form:input  type="text" path="price" placeholder="House Price ..."/>


    <form:label path="category">Category:</form:label>
    <form:select path="category">
        <c:forEach items="${categories}" var="cat">
            <form:option value="${cat.id}">${cat.name}</form:option>
        </c:forEach>
    </form:select>

    <form:label path="description">Description:</form:label>
    <form:textarea path="description" placeholder="Write Description.." style="height:200px"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Add">
    <form action="/">
        <input type="submit" value="Back">
    </form>
</form:form>

    </div>
</body>
</html>
