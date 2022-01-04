<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/index_style.css" %>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body  class="bodyClassGreen">
<div>
    <div class="topPanel">
        <div class="topPanelFirst">
            <sec:authorize access="!isAuthenticated()">
                <div class="logo">Storage of goods</div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="username">${pageContext.request.userPrincipal.name}</div>
            </sec:authorize>
        </div>
        <div class="topPanelLast">
            <sec:authorize access="!isAuthenticated()">
                <div><a href="login">Войти</a></div>
                <div><a href="registration">Зарегистрироваться</a></div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div><a href="logout">Выйти</a></div>
            </sec:authorize>
        </div>
    </div>

    <sec:authorize access="isAuthenticated()">
        <div class="indexPanel">
            <div class="label">Склад</div>
            <div class="storageMainPanel">
                <div class="squareImgDiv"><a href="incomes_main">
                    <div></div>
                    <div>Приходы</div>
                </a></div>
                <div class="squareImgDiv"><a href="expands_main">
                    <div></div>
                    <div>Расходы</div>
                </a></div>
                <div class="squareImgDiv"><a href="items_main">
                    <div></div>
                    <div>Товары</div>
                </a></div>
                <div class="squareImgDiv"><a href="show_storage">
                    <div></div>
                    <div>Остатки</div>
                </a></div>
            </div>
<%--            <div class="storageMainPanel">--%>
<%--                <div class="squareImgDiv">--%>
<%--                    <a href="items_main">--%>
<%--                        <div>Товары</div>--%>
<%--                    </a>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </sec:authorize>


<%--    <h4><a href="/news">Новости (только пользователь)</a></h4>--%>
<%--    <h4><a href="/admin">Пользователи (только админ)</a></h4>--%>

</div>
</body>
</html>