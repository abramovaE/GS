<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new incomeString</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
        <%@include file="/resources/index_style.css" %>
    </style>
</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="topPanel">
    <div class="topPanelFirst">
        <div class="username">${pageContext.request.userPrincipal.name}</div>
    </div>
    <div class="topPanelCentral">
        <div><a href="add_expand_string">Создать расход</a></div>
    </div>
    <div class="topPanelLast">
        <div><a href="/GS">На главную</a></div>
    </div>
</div>
<div class="outerDivTr">
    <div class="innerDivTr">
        <h2 class="h2Light">Расходы</h2>
        <table class="todayIncomeStrings">
            <tr>
                <th>Добавил</th>
                <th>Дата</th>
                <th>Магазин</th>
                <th>Сумма, руб.</th>
            </tr>
                <c:forEach items="${incomesMain}" var="incomeMain">
                    <tr>
                        <td>${incomeMain.userName}</td>
                        <td>${incomeMain.date}</td>
                        <td>${incomeMain.store}</td>
                        <td>${incomeMain.summ}</td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>