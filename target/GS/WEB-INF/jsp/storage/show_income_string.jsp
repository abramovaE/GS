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
        <%@include file="/resources/show_income_string_style.css" %>


    </style>
</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<script>
    function handlePrice(){
        var count = document.getElementById('incomeStringForm').count.value
        var ppDouble = document.getElementById('incomeStringForm').purchasePriceDouble.value
        var ppActDouble = document.getElementById('incomeStringForm').purchasePriceActDouble.value
        document.getElementById('ppSum').innerHTML = Math.round(count * ppDouble * 100)/100
        document.getElementById('ppActSum').innerHTML = Math.round(count * ppActDouble * 100)/100
    }
</script>
<div class="topPanel">
    <div class="topPanelFirst">
        <div class="username">${pageContext.request.userPrincipal.name}</div>
    </div>
    <div class="topPanelLast">
        <div><a href="/GS">На главную</a></div>
    </div>
</div>

<h2 class="h2Light">Изменить приход</h2>
<div class="outerDivLogin">
    <form:form method="POST" modelAttribute="incomeStringForm">

        <form:hidden path="id"/>
        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
        <form:hidden path="item.id"/>
        <form:hidden path="incomeMain.id"/>
        <div class="innerDivLogin">
            <div>Товар</div>
            <div> ${incomeStringForm.item.name}</div>
        </div>
        <div class="innerDivLogin">
            <form:label path="count">Количество</form:label>
            <form:input type="number" path="count" placeholder="Количество"
                        onchange="handlePrice()" min="0"
                        required="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="purchasePriceDouble">Цена покупки, руб.</form:label>
            <form:input type="number" min = "0" step="0.01" path="purchasePriceDouble"
                        onchange="handlePrice()"
                        placeholder="Цена покупки, руб." required="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="purchasePriceActDouble">Цена покупки окончательная, руб.</form:label>
            <form:input type="number" min="0" step="0.01" path="purchasePriceActDouble"
                        placeholder="Цена покупки окончательная, руб."
                        required="true" class="inputClassLight" onchange="handlePrice()"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="storeArticle">Артикул в магазине</form:label>
            <form:input type="text" path="storeArticle" placeholder="Артикул в магазине"
                        autofocus="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="store">Магазин покупки</form:label>
            <form:input type="text" path="store" placeholder="Магазин покупки"
                        autofocus="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="batchNumber">Номер партии</form:label>
            <form:input type="text" path="batchNumber" placeholder="Номер партии"
                        autofocus="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <div>Сумма покупки, руб.</div>
            <div id="ppSum">${incomeStringForm.count*incomeStringForm.purchasePrice}/100</div>
        </div>
        <div class="innerDivLogin">
            <div>Сумма покупки окончательная, руб.</div>
            <div id="ppActSum">${incomeStringForm.count*incomeStringForm.purchasePriceAct}/100</div>
        </div>
        <div class="innerDivLogin">
            <button type="submit" class="inputClassLight">Сохранить</button>
        </div>
    </form:form>
</div>
</body>
</html>