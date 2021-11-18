<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
</head>

<body class="bodyClassGreen">
<div class="outerDivLogin">
    <form:form method="POST" modelAttribute="userForm">
        <h2  class="h2Light">Регистрация</h2>
        <div class="innerDivLogin">
            <form:input type="text" path="username" placeholder="Username"
                        autofocus="true" class="inputClassLight"/>
            <form:errors path="username"/>
                ${usernameError}
        </div>
        <div class="innerDivLogin">
            <form:input type="password" path="password" placeholder="Password" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="password" path="passwordConfirm"
                        placeholder="Confirm your password" class="inputClassLight"/>
            <form:errors path="password"/>
                ${passwordError}
        </div>
        <div class="innerDivLogin">
            <button type="submit" class="inputClassLight">Зарегистрироваться</button>
        </div>
    </form:form>
</div>
</body>
</html>
