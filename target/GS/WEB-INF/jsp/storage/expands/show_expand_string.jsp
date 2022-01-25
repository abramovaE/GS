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
        let count = document.getElementById('expandStringForm').count.value
        let ppDouble = document.getElementById('salePriceDouble').value
        document.getElementById('ppSum').innerHTML = (Math.round(Number(count) * Number(ppDouble) * 100)/100)
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

<h2 class="h2Light">Изменить расход</h2>
<div class="outerDivLogin">
    <form:form method="POST" modelAttribute="expandStringForm">

        <form:hidden path="id"/>
        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
        <form:hidden path="item.id"/>
        <form:hidden path="expandMain.id"/>
        <form:hidden path="date"/>
        <div class="innerDivLogin">
            <div>Товар</div>
            <div> ${expandStringForm.item.name}</div>
        </div>
        <div class="innerDivLogin">
            <form:label path="count">Количество</form:label>
            <form:input type="number" path="count" placeholder="Количество"
                        onchange="handlePrice()" min="0"
                        required="true" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="salePriceDouble">Цена продажи, руб.</form:label>
            <form:input type="number" min = "0" step="0.01" path="salePriceDouble" id="salePriceDouble"
                        onchange="handlePrice()"
                        placeholder="Цена покупки, руб." required="true" class="inputClassLight"/>
        </div>

<%--        <div class="innerDivLogin">--%>
<%--            <form:label path="batchNumber">Номер партии</form:label>--%>
<%--            <form:input type="text" path="Number" placeholder="Номер партии"--%>
<%--                        required="true"--%>
<%--                        autofocus="true" class="inputClassLight"/>--%>
<%--        </div>--%>
        <div class="innerDivLogin">
            <div>Сумма продажи, руб.</div>
            <div id="ppSum">${expandStringForm.count*expandStringForm.salePrice/100}</div>
        </div>

        <div class="innerDivLogin">
            <form:label path="note">Примечание</form:label>
            <form:input type="text" path="note"
                        placeholder="Примечание"
                        autofocus="true"
                        class="inputClassLight"/>
        </div>

        <div class="innerDivLogin">
            <button type="submit" class="inputClassLight">Сохранить</button>
        </div>
    </form:form>
</div>
</body>
</html>