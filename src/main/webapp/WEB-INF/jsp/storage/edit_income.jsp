<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit income</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/edit_income_style.css" %>
        <%@include file="/resources/index_style.css" %>

    </style>
</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>

<script>
    function handlePrice() {
        var count = document.getElementById('incomeForm').count.value;
        var ppDouble = document.getElementById('incomeForm').purchasePriceDouble.value;
        var ppActDouble = document.getElementById('incomeForm').purchasePriceActDouble.value;
        document.getElementById('ppSum').innerHTML = Math.round(count * ppDouble * 100) / 100;
        document.getElementById('ppActSum').innerHTML = Math.round(count * ppActDouble * 100) / 100;
    }
    function clearCount(){
        let value = document.getElementById('incomeForm').count.value;
        if(value == 0){
            document.getElementById('incomeForm').count.value = "";
        }
    }
    function  clearPpDouble() {
        let value = document.getElementById('incomeForm').purchasePriceDouble.value;
        if (value == 0) {
            document.getElementById('incomeForm').purchasePriceDouble.value = "";
        }
    }
    function clearPpActDouble() {
        let value = document.getElementById('incomeForm').purchasePriceActDouble.value;
        if (value == 0) {
            document.getElementById('incomeForm').purchasePriceActDouble.value = "";
        }
    }
</script>

<div class="topPanel">
    <div class="topPanelFirst">
        ${pageContext.request.userPrincipal.name}
    </div>
    <div class="topPanelLast">
        <div><a href="/">На главную</a></div>
    </div>
</div>
<div class="outerDivLogin">
    <form:form method="POST" modelAttribute="incomeForm">
        <h2 class="h2Light">Редактировать приход</h2>
        <form:hidden path="id"/>
        <form:hidden path="userName"/>
        <form:hidden path="date"/>
        <div class="innerDivLogin">
            <form:label path="item.id">Товар</form:label>
            <form:select required="true" path="item.id" class="inputClassLight">
                <c:forEach items="${items}" var="it">
                    <form:option value="${it.id}" label="${it.name}"/>
                </c:forEach>
            </form:select>
        </div>
        <div class="innerDivLogin">
            <form:label path="count">Количество</form:label>
            <form:input type="number" placeholder="Количество" min = "0" path="count"
                        onchange="javascript:handlePrice()" onfocus="javascript:clearCount()" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="purchasePriceDouble">Цена покупки, руб.</form:label>
            <form:input type="number" placeholder="Цена покупки" min = "0" step="0.01"
                        path="purchasePriceDouble"
                        onchange="javascript:handlePrice()" onfocus="javascript:clearPpDouble()" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="purchasePriceActDouble">Цена покупки окончательная, руб.</form:label>
            <form:input type="number" placeholder="Цена покупки окончательная" min = "0" step="0.01"
                        path="purchasePriceActDouble"
                        onchange="javascript:handlePrice()" onfocus="javascript:clearPpActDouble()" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="storeArticle">Артикул в магазине</form:label>
            <form:input type="text" path="storeArticle" placeholder="Артикул в магазине" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="store">Магазин покупки</form:label>
            <form:input type="text" path="store" placeholder="Магазин покупки" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:label path="batchNumber">Номер партии</form:label>
            <form:input type="text" placeholder="Номер партии" path="batchNumber" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <div class="label">Сумма покупки, руб.</div>
            <div id="ppSum" class="inputClassLight input">${ppSum}</div>
        </div>
        <div class="innerDivLogin">
            <div class="label">Сумма покупки окончательная, руб.</div>
            <div id="ppActSum" class="inputClassLight input">${ppSumAct}</div>
        </div>
        <div class="innerDivLogin">
            <button type="submit" id="subm" class="inputClassLight">Сохранить изменения</button>
        </div>
    </form:form>
</div>
</body>
</html>