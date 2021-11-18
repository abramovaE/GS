<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new item</title>
    <style>
        <%@include file="../../../resources/style.css" %>
    </style>
</head>

<body class="bodyClass">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="addItem">
    <form:form method="POST" modelAttribute="itemForm">
        <h2>Добавить товар</h2>
        <div>
            <form:input type="text" path="article" placeholder="article" autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="name" placeholder="name"></form:input>
        </div>
        <div>
            <form:input type="text" path="type" placeholder="type"></form:input>
        </div>
        <div>
            <form:input type="text" path="ean" placeholder="ean"></form:input>
        </div>
        <button type="submit">Добавить</button>
    </form:form>
</div>

</body>
</html>