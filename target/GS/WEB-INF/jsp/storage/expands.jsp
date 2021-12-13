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
    function handleSelect(elm) {
        var count = document.getElementById('expandForm').count.value
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
        var item = document.getElementById('expandForm').item.value
        var count = document.getElementById('expandForm').count.value
        var ppDouble = document.getElementById('expandForm').salePriceDouble.value
        document.getElementById('ppSum').innerHTML = Math.round(count * ppDouble * 100)/100
        if(count > 0 && item > 0){
            document.getElementById('subm').disabled = false
        } else {
            document.getElementById('subm').disabled = true
        }
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
        <div><a href="add_expand">Создать расход</a></div>
    </div>
    <div class="topPanelLast">
        <div><a href="/GS">На главную</a></div>
    </div>
</div>
<div class="outerDivTr">
    <div class="innerDivTr">
        <h2 class="h2Light">Расходы</h2>
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
                <c:forEach items="${expands}" var="expand">
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
                            <a href="edit_expand/${expand.id}">Редактировать</a>
                        </td>
                        <td class="edit" id="delete${expand.id}" hidden>
                            <a href="delete_expand/${expand.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>