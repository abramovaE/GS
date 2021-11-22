<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Storage</title>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
<%--    <form method="POST" action="/addItem">--%>
        <h2 class="h2Light">Склад</h2>
    <div class="storageMainPanel">
        <div class="squareImgDiv"><a href="/add_income">
            <div>+</div>
            <div>Создать приход</div>
            </a></div>
        <div class="squareImgDiv"><a href="/add_expand">
            <div>-</div>
            <div>Создать расход</div>
            </a></div>
        <div class="squareImgDiv"><a href="/show_storage">
            <div>$</div>
            <div>Посмотреть остатки</div>
            </a></div>
    </div>




<%--        <div>--%>
<%--            &lt;%&ndash;            <input name="username" type="text" placeholder="Username"&ndash;%&gt;--%>
<%--            &lt;%&ndash;                   autofocus="true"/>&ndash;%&gt;--%>
<%--            &lt;%&ndash;            <input name="password" type="password" placeholder="Password"/>&ndash;%&gt;--%>
<%--            &lt;%&ndash;            <button type="submit">Log In</button>&ndash;%&gt;--%>
<%--            &lt;%&ndash;            <h4><a href="/registration">Зарегистрироваться</a></h4>&ndash;%&gt;--%>
<%--        </div>--%>
<%--    </form>--%>
</div>

</body>
</html>