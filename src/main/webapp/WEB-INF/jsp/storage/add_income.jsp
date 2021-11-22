<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new income</title>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="outerDivTr">
    <form:form method="POST" modelAttribute="incomeForm">
        <h2 class="h2Light">Создать приход</h2>

        <table class="addIncome">
            <th>Товар</th>
            <th>Количество</th>
            <th class="width100">Цена покупки</th>
            <th class="width100">Цена покупки окончательная</th>
            <th>Артикул в магазине</th>
            <th>Магазин покупки</th>
            <th>Номер партии</th>

            <tr>
                <td class="innerDivTr"><form:input type="text" path="item" placeholder="Товар" autofocus="true"/></td>
                <td class="innerDivTr"><form:input type="text" path="count" placeholder="Количество" autofocus="true"/></td>
                <td class="innerDivTr"><form:input type="text" path="purchasePrice" placeholder="Цена покупки"/></td>
                <td class="innerDivTr"><form:input type="text" path="purchasePriceAct" placeholder="Цена покупки окончательная"/></td>
                <td class="innerDivTr"><form:input type="text" path="storeArticle" placeholder="Артикул в магазине"/></td>
                <td class="innerDivTr"><form:input type="text" path="store" placeholder="Магазин покупки"/></td>
                <td class="innerDivTr"><form:input type="text" path="batchNumber" placeholder="Номер партии"/></td>
            </tr>
            <tr class="innerDivTr">
                <td colspan="7">
                    <button type="submit">Добавить</button>
                </td>
            </tr>
        </table>

<%--        <div class="innerDivTr">--%>
<%--            <form:input type="text" path="item" placeholder="Товар" autofocus="true" class="inputClassLight"/>--%>
<%--        </div>--%>
<%--        <div class="innerDivTr">--%>
<%--            <form:input type="text" path="count" placeholder="Количество" autofocus="true" class="inputClassLight"/>--%>
<%--        </div>--%>
<%--        <div class="innerDivTr">--%>
<%--            <form:input type="text" path="purchasePrice" placeholder="Цена покупки" class="inputClassLight"/>--%>
<%--        </div>--%>
<%--        <div class="innerDivTr">--%>
<%--            <form:input type="text" path="purchasePriceAct" placeholder="Цена покупки окончательная"--%>
<%--                        class="inputClassLight"/>--%>
<%--        </div>--%>
<%--        <div class="innerDivTr">--%>
<%--            <form:input type="text" path="storeArticle" placeholder="Артикул в магазине" class="inputClassLight"/>--%>
<%--        </div>--%>
<%--        <div class="innerDivTr">--%>
<%--            <form:input type="text" path="store" placeholder="Магазин покупки" class="inputClassLight"/>--%>
<%--        </div>--%>
<%--        <div class="innerDivTr">--%>
<%--            <form:input type="text" path="batchNumber" placeholder="Номер партии" class="inputClassLight"/>--%>
<%--        </div>--%>
<%--        <div class="buttonDivTr">--%>
<%--            <button type="submit" class="inputClassLight">Добавить</button>--%>
<%--        </div>--%>
    </form:form>
</div>

</body>
</html>