<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new income</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
        <%@include file="/resources/index_style.css" %>

    </style>

</head>

<body class="bodyClassGreen">
<script type="text/javascript">
    function handlePrice(){
        var count = document.getElementById('expandForm').count.value
        var ppDouble = document.getElementById('expandForm').salePriceDouble.value
        document.getElementById('ppSum').innerHTML = Math.round(count * ppDouble * 100)/100
    }
    function clearCount(){
        let value = document.getElementById('expandForm').count.value;
        if(value == 0){
            document.getElementById('expandForm').count.value = "";
        }
    }
    function  clearPpDouble() {
        let value = document.getElementById('expandForm').salePriceDouble.value;
        if (value == 0) {
            document.getElementById('expandForm').salePriceDouble.value = "";
        }
    }

    function showEditPanel(id){
        // document.getElementById('edit' + id).style.display = 'block'
        document.getElementById('delete' + id).style.display = 'block'
        // document.getElementById('editth').style.display = 'block'
        document.getElementById('deleteth').style.display = 'block'
    }
    function hideEditPanel(id){
        // document.getElementById('edit' + id).style.display = 'none'
        document.getElementById('delete' + id).style.display = 'none'
        // document.getElementById('editth').style.display = 'none'
        document.getElementById('deleteth').style.display = 'none'
    }
</script>
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="topPanel">
    <div class="topPanelFirst">
        <div class="username">${pageContext.request.userPrincipal.name}</div>
    </div>
    <div class="topPanelLast">
        <div><a href="/">На главную</a></div>
    </div>
</div>
<div class="outerDivTr">
        <h2 class="h2Light">Создать расход</h2>
        <div class="innerDivTr">
            <form:form method="POST" modelAttribute="expandForm">
            <table class="addIncome">
                <tr>
                    <th>Товар</th>
                    <th>Количество</th>
                    <th>Цена продажи, руб.</th>
                    <th>Номер партии</th>
                    <th>Сумма продажи, руб.</th>
                </tr>
                <tr>
                    <td>
                        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
                        <form:select path="item">
                            <form:option value="-" label=""/>
                                <c:forEach items="${items}" var="item">
                                    <form:option value="${item.id}" label="${item.name}"/>
                                </c:forEach>
                        </form:select>
                    <td><form:input type="text" path="count" placeholder="Количество" autofocus="true"
                                    onchange="javascript:handlePrice()" onfocus="javascript:clearCount()"/></td>
                    <td><form:input type="text" path="salePriceDouble" placeholder="Цена продажи" min = "0" step="0.01"
                                    onchange="javascript:handlePrice()" onfocus="javascript:clearPpDouble()"/></td>
                    <td><form:input type="text" path="batchNumber"/></td>
                    <td><div id="ppSum" class="addIncomeInput">0.00</div></td>
                </tr>
                <tr>
                    <td colspan="5">
                        <button type="submit">Добавить</button>
                    </td>
                </tr>
            </table>
            </form:form>
        </div>

    <h2 class="h2TodayIncomes">Расходы ${date}</h2>
    <div class="innerDivTr">
        <table class="todayIncomes">
            <tr>
                <th>Добавил</th>
                <th>Дата</th>
                <th>Товар</th>
                <th>Количество</th>
                <th>Цена продажи, руб.</th>
                <th>Номер партии</th>
                <th>Сумма продажи, руб.</th>
                <th id="editth" class="edit" hidden></th>
                <th id="deleteth" class="edit" hidden></th>
            </tr>
                <c:forEach items="${todayExpands}" var="expand">
                    <tr onmouseover="javascript:showEditPanel(${expand.id})"
                        onmouseout="javascript:hideEditPanel(${expand.id})">
                        <td>${expand.userName}</td>
                        <td>${expand.date}</td>
                        <td>${expand.item.name}</td>
                        <td>${expand.count}</td>
                        <td>${expand.salePrice}</td>
                        <td>${expand.batchNumber}</td>
                        <td>${expand.count * expand.salePrice/100}</td>
                        <td class="edit" id="edit${expand.id}" hidden>
                            <a href="/edit_expand/${expand.id}">Редактировать</a>
                        </td>
                        <td class="edit" id="delete${expand.id}" hidden>
                            <a href="/delete_expand/${expand.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>