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
<script type="text/javascript">
    function handleSelect(elm) {
        var count = document.getElementById('expandStringForm').count.value
        if(elm.value == 0) {
            window.location = "add_item";
        }
        if(elm.value > 0 && count > 0){
            document.getElementById('subm').disabled = false
        } else {
            document.getElementById('subm').disabled = true
        }
    }

    function handlePrice(){
        var item = document.getElementById('expandStringForm').item.value
        var count = document.getElementById('expandStringForm').count.value
        var ppDouble = document.getElementById('expandStringForm').salePriceDouble.value
        document.getElementById('ppSum').innerHTML = Math.round(count * ppDouble * 100)/100
        if(count > 0 && item > 0){
            document.getElementById('subm').disabled = false
        } else {
            document.getElementById('subm').disabled = true
        }
    }
    function clearCount(){
        let value = document.getElementById('expandStringForm').count.value;
        if(value == 0){
            document.getElementById('expandStringForm').count.value = "";
        }
    }
    function  clearPpDouble() {
        let value = document.getElementById('expandStringForm').salePriceDouble.value;
        if (value == 0) {
            document.getElementById('expandStringForm').salePriceDouble.value = "";
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
        <div><a href="/GS">На главную</a></div>
    </div>
</div>
<div class="outerDivTr">
        <div class="innerDivTr">
            <h2 class="h2Light">Создать расход</h2>
            <form:form method="POST" modelAttribute="expandStringForm">
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
                        <form:select path="item" required="true" onchange="javascript:handleSelect(this)">
                            <form:option value="-1" label=""/>
                                <c:forEach items="${items}" var="item">
                                    <form:option value="${item.id}" label="${item.name}"/>
                                </c:forEach>
                        </form:select>
                    <td><form:input type="text" path="count" required="true" placeholder="Количество" autofocus="true"
                                    onchange="javascript:handlePrice()" onfocus="javascript:clearCount()"/></td>
                    <td><form:input type="text" path="salePriceDouble" placeholder="Цена продажи" min = "0" step="0.01"
                                    onchange="javascript:handlePrice()" onfocus="javascript:clearPpDouble()"/></td>
                    <td><form:input type="text" path="batchNumber"/></td>
                    <td><div id="ppSum" class="addIncomeInput">0.00</div></td>
                </tr>
                <tr>
                    <td colspan="5">
                        <button type="submit" id="subm" disabled="true">Добавить</button>
                    </td>
                </tr>
            </table>
            </form:form>
        </div>
    <div class="innerDivTr">
        <h2 class="h2Light">Расходы ${date}</h2>
        <table class="todayIncomeStrings">
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
                <c:forEach items="${todayExpandStrings}" var="expandString">
                    <tr onmouseover="javascript:showEditPanel(${expandString.id})"
                        onmouseout="javascript:hideEditPanel(${expandString.id})">
                        <td>${expandString.userName}</td>
                        <td>${expandString.date}</td>
                        <td>${expandString.item.name}</td>
                        <td>${expandString.count}</td>
                        <td>${expandString.salePrice}</td>
                        <td>${expandString.batchNumber}</td>
                        <td>${expandString.count * expandString.salePrice/100}</td>
                        <td class="edit" id="edit${expandString.id}" hidden>
                            <a href="edit_expand/${expandString.id}">Редактировать</a>
                        </td>
                        <td class="edit" id="delete${expandString.id}" hidden>
                            <a href="delete_expand_string/${expandString.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>