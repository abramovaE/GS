<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Show all items</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/all_items.css" %>
    </style>
</head>

<body  class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="outerDivTr">
    <h2  class="h2Light">Все товары</h2>
    <h4><a href="/add_item">Добавить товар</a></h4>
    <table class="addIncome">
        <th><a href="<c:url value="/allItems/sortBy/article"/>">Артикул</a></th>
        <th><a href="<c:url value="/allItems/sortBy/name"/>">Наименование</a></th>
        <th><a href="<c:url value="/allItems/sortBy/type"/>">Тип</a></th>
        <th><a href="<c:url value="/allItems/sortBy/ean"/>">EAN-номер</a></th>
        <th><a href="<c:url value="/allItems/sortBy/username"/>">Кто добавил</a></th>
        <th><a href="<c:url value="/allItems/sortBy/date"/>">Дата добавления</a></th>
        <th><a href="<c:url value="/allItems/sortBy/count"/>">Актуальное количество</a></th>
        <c:forEach items="${items}" var="item">
            <tr>
                <td>${item.article}</td>
                <td>${item.name}</td>
                <td>${item.type}</td>
                <td>${item.ean}</td>
                <td>${item.userName}</td>
                <td>${item.date}</td>
                <td>${item.count}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
