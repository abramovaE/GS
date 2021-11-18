<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h2>Все товары</h2>
    <h4><a href="/add_item">Добавить товар</a></h4>

    <table>
        <th>id</th>
        <th>Артикул</th>
        <th>Наименование</th>
        <th>Тип</th>
        <th>EAN-номер</th>
        <c:forEach items="${allItems}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.article}</td>
                <td>${item.name}</td>
                <td>${item.type}</td>
                <td>${item.ean}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
