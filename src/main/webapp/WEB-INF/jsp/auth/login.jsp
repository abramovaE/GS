<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
  <style>
    <%@include file="/resources/style.css" %>
  </style>
</head>

<body class="bodyClassGreen">
<sec:authorize access="isAuthenticated()">
  <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="outerDivLogin">
  <form method="POST" action="/login">
    <h2 class="h2Light">Вход в систему</h2>
    <div class="innerDivLogin">
      <input name="username" type="text" placeholder="Username"
             autofocus="true" class="inputClassLight"/>
    </div>
    <div class="innerDivLogin">
      <input name="password" type="password" placeholder="Password" class="inputClassLight"/>
    </div>
    <div class="innerDivLogin">
      <button type="submit" class="inputClassLight">Войти</button>
    </div>
    <div class="innerDivLogin">
    <a href="/registration" class="inputClassLight">Зарегистрироваться</a>
    </div>
  </form>
</div>

</body>
</html>