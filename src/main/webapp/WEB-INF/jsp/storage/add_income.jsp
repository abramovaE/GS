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
        <%@include file="/resources/add_income_style.css" %>
    </style>
</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="outerDivTr">

        <h2 class="h2Light">Создать приход</h2>
        <div class="innerDivTr">

            <form:form method="POST" modelAttribute="incomeForm">
            <table class="addIncome">
                <tr>
                    <th>Товар</th>
                    <th>Количество</th>
                    <th>Цена покупки</th>
                    <th>Цена покупки окончательная</th>
                    <th>Артикул в магазине</th>
                    <th>Магазин покупки</th>
                    <th>Номер партии</th>
                </tr>
                <tr>
                    <td><form:input type="text" path="item" placeholder="Товар" autofocus="true"/></td>
                    <td><form:input type="text" path="count" placeholder="Количество" autofocus="true"/></td>
                    <td><form:input type="text" path="purchasePrice"/></td>
                    <td><form:input type="text" path="purchasePriceAct"/></td>
                    <td><form:input type="text" path="storeArticle" placeholder="Артикул в магазине"/></td>
                    <td><form:input type="text" path="store" placeholder="Магазин покупки"/></td>
                    <td><form:input type="text" path="batchNumber"/></td>
                </tr>
                <tr>
                    <td colspan="7">
                        <button type="submit">Добавить</button>
                    </td>
                </tr>
            </table>
            </form:form>
        </div>
</div>
</body>
</html>