<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new item</title>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="outerDivLogin">
    <form:form method="POST" modelAttribute="itemForm">
        <h2 class="h2Light">Добавить товар</h2>
        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
        <div class="innerDivLogin">
            <form:input type="text" path="article" placeholder="Артикул" autofocus="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="name" placeholder="Наименование" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="type" placeholder="Тип" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="ean" placeholder="Штрих-код" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <button type="submit" class="inputClassLight">Добавить</button>
        </div>
    </form:form>
</div>

</body>
</html>