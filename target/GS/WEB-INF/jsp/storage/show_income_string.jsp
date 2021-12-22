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
    <div class="topPanelLast">
        <div><a href="/GS">На главную</a></div>
    </div>
</div>
<div class="outerDivLogin">
    <form:form method="POST" modelAttribute="incomeStringForm">
        <h2 class="h2Light">Изменить приход</h2>
        <form:hidden path="id"/>
        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
        <form:hidden path="item.id"/>
        <form:hidden path="incomeMain.id"/>
        <div class="innerDivLogin">
            ${incomeStringForm.item.name}
        </div>
        <div class="innerDivLogin">
            <form:input type="number" path="count" placeholder="Количество"
                        required="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="purchasePrice"
                        placeholder="Цена покупки, руб." required="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="purchasePriceAct" placeholder="Цена покупки окончательная, руб."
                        required="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="storeArticle" placeholder="Артикул в магазине"
                        autofocus="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="store" placeholder="Магазин покупки"
                        autofocus="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="batchNumber" placeholder="Номер партии"
                        autofocus="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            Сумма покупки, руб.:
        </div>
        <div class="innerDivLogin">
            Сумма покупки окончательная, руб.:
        </div>
        <div class="innerDivLogin">
            <button type="submit" class="inputClassLight">Добавить</button>
        </div>
    </form:form>
</div>
</body>
</html>