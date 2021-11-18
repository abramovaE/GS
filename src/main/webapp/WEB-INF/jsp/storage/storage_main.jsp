<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Storage</title>
</head>

<body>
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
<%--    <form method="POST" action="/addItem">--%>
        <h2>Склад</h2>
        <h4><a href="/add_item">Добавить товар</a></h4>
        <h4><a href="/all_items">Посмотреть все товары</a></h4>
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