<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Incomes</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
        <%@include file="/resources/index_style.css" %>
    </style>
</head>

<body class="bodyClassGreen">
<script>
    function showEditPanel(id){
        // // document.getElementById('edit' + id).style.display = 'block'
        // document.getElementById('delete' + id).style.display = 'block'
        // // document.getElementById('editth').style.display = 'block'
        // document.getElementById('deleteth').style.display = 'block'
    }
    function hideEditPanel(id){
        // // document.getElementById('edit' + id).style.display = 'none'
        // document.getElementById('delete' + id).style.display = 'none'
        // // document.getElementById('editth').style.display = 'none'
        // document.getElementById('deleteth').style.display = 'none'
    }
</script>
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="topPanel">
    <div class="topPanelFirst">
        <div class="username">${pageContext.request.userPrincipal.name}</div>
    </div>
    <div class="topPanelCentral">
        <div><a href="add_income_string">Создать приход</a></div>
    </div>
    <div class="topPanelLast">
            <div><a href="/GS">На главную</a></div>
    </div>
</div>

<div class="outerDivTr">

    <div class="innerDivTr">
    <h2 class="h2Light">Приходы</h2>
    <table class="todayIncomeStrings">
        <tr>
            <th>Добавил</th>
            <th>Дата</th>
            <th>Товар</th>
            <th>Количество</th>
            <th>Цена покупки</th>
            <th>Цена покупки окончательная</th>
            <th>Артикул в магазине</th>
<%--            <th>Магазин покупки</th>--%>
            <th>Номер партии</th>
            <th>Сумма покупки, руб.</th>
            <th>Сумма покупки окончательная, руб.</th>
            <th id="editth" class="edit" hidden></th>
            <th id="deleteth" class="edit" hidden></th>
        </tr>
        <c:forEach items="${incomeStrings}" var="incomeString">
            <tr onmouseover="javascript:showEditPanel(${incomeString.id})"
                onmouseout="javascript:hideEditPanel(${incomeString.id})">
                <td>${incomeString.userName}</td>
                <td>${incomeString.date}</td>
                <td>${incomeString.item.name}</td>
                <td>${incomeString.count}</td>
                <td>${incomeString.purchasePrice/100}</td>
                <td>${incomeString.purchasePriceAct/100}</td>
                <td>${incomeString.storeArticle}</td>
<%--                <td>${incomeString.store}</td>--%>
                <td>${incomeString.batchNumber}</td>
                <td>${incomeString.count * incomeString.purchasePrice/100}</td>
                <td>${incomeString.count * incomeString.purchasePriceAct/100}</td>
                <td class="edit" id="edit${incomeString.id}" hidden>
                    <a href="edit_income/${incomeString.id}">Редактировать</a>
                </td>
                <td class="edit" id="delete${incomeString.id}" hidden>
                    <a href="delete_income_string/${incomeString.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>

</body>
</html>
