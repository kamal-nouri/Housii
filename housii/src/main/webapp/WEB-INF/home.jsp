<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: msys
  Date: 1/21/2021
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Housii</title>
    <!--Important link source from https://bootsnipp.com/snippets/ooa9M-->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- load Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <!--Important link source from https://bootsnipp.com/snippets/ooa9M-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <!------ Include the above in your HEAD tag for search bar ---------->
    <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
    <script src="https://code.jquery.com/jquery-1.4.2.js" integrity="sha256-lcAjyA3+DTAwTFgkSHiZUGH4eAGmbapda/TyUSvg5vk=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui-1.8.2.custom.js"></script>
    <script src="https://code.jquery.com/ui/1.8.20/jquery-ui.js" integrity="sha256-nzQScIoAB0RpIRHQq0ftdGO/gQuTtcsXu4QbZ6FhIA8=" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/css/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(document).ready(function() {
            $('#locationName').autocomplete({
                source : '${pageContext.request.contextPath }/search'
            });
        });
    </script>
    <!------ Include the above in your HEAD tag for search bar ---------->
</head>
<body>
<%@ include file = "header.jsp" %>
<div class="slideshow-container">

    <div class="mySlides fade">
        <%--        <div class="numbertext" style="display: flex;align-items: center;justify-content: center;width: 100%;height: 100%"><div style="display: flex;align-items: center;justify-content: center;background-color:white;opacity:.6;border-radius:80px;height: 15%;width: 80%;">search</div></div>--%>
        <img src="img/first.jpg" style="width:100%">
    </div>

    <div class="mySlides fade">
        <%--        <div class="numbertext" style="display: flex;align-items: center;justify-content: center;width: 100%;height: 100%"><div style="display: flex;align-items: center;justify-content: center;background-color:white;opacity:.6;border-radius:80px;height: 15%;width: 80%;">search</div></div>--%>
        <img src="img/secc.jpg" style="width:100%">
    </div>

    <div class="mySlides fade">
        <%--        <div class="numbertext" style="display: flex;align-items: center;justify-content: center;width: 100%;height: 100%"><div style="display: flex;align-items: center;justify-content: center;background-color:white;opacity:.6;border-radius:80px;height: 15%;width: 80%;">search</div></div>--%>
        <img src="img/third.jpg" style="width:100%">
    </div>
<%--    <div class="search-bar">--%>
<%--        <div class="input-group">--%>
<%--            <input type="text" name="searchedLocation" placeholder="search" id="locationName" required/>--%>
<%--        </div>--%>
<%--        <a class="imp" href="/searchLocation">--%>
<%--            <span>Search</span>--%>
<%--            <div class="liquid"></div>--%>
<%--        </a>--%>
<%--    </div>--%>

    <div >
    <form:form method="POST" action="/searchLocation" cssClass="search-bar" modelAttribute="house">
        <div class="input-group">
            <form:input id="locationName" placeholder="Search" path="location"/>
        </div>
        <input type="submit" class="liquid" value="search"/>
    </form:form>
    </div>
</div>
<br>
<div style="text-align:center">
    <span class="dot"></span>
    <span class="dot"></span>
    <span class="dot"></span>
</div>

<script>
    var slideIndex = 0;
    showSlides();

    function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("dot");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {
            slideIndex = 1
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " active";
        setTimeout(showSlides, 2000); // Change image every 2 seconds
    }

</script>
<div class="cards-list">
    <c:forEach items="${categories}" var="category">
        <a id="im" href="/${category.name}">
            <div class="card 1">
                <div class="card_image"><img src="img/${category.name}.jpg"/></div>
                <div class="card_title title-white">
                    <p>${category.name}</p>
                </div>
            </div>
        </a>
    </c:forEach>

</div>
<%@ include file = "footer.jsp" %>
</body>
</html>
