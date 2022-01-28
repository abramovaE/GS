<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Show all items</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
        <%@include file="/resources/index_style.css" %>
        <%@include file="/resources/balance.css" %>
    </style>
</head>
<body  class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="topPanel">
    <div class="topPanelFirst">
        <div class="username">${pageContext.request.userPrincipal.name}</div>
    </div>
    <div class="topPanelLast">
        <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
    </div>
</div>
<div class="outerDivTr">
    <div class="innerDivTr">
        <h2 class="h2Light">Внесено средств: ${allMoneySum/100} р.</h2>
        <h2 class="h2Light">Доступно средств: ${availableMoney/100} р.</h2>

        <table class="todayIncomeStrings">
            <th>Дата внесения</th>
            <th>Пользователь</th>
            <th>Сумма</th>
            <c:forEach items="${allMoney}" var="money">
                <tr>
                    <td>${money.date}</td>
                    <td>${money.userName}</td>
                    <td>${money.sum/100}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>