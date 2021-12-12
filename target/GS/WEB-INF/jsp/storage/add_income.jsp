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
        if(elm.value == 0) {
            window.location = "add_item";
        }
        if(elm.value > 0){
            document.getElementById('subm').disabled = false
        }
    }

    function handlePrice(){
        var count = document.getElementById('incomeForm').count.value
        var ppDouble = document.getElementById('incomeForm').purchasePriceDouble.value
        var ppActDouble = document.getElementById('incomeForm').purchasePriceActDouble.value
        document.getElementById('ppSum').innerHTML = Math.round(count * ppDouble * 100)/100
        document.getElementById('ppActSum').innerHTML = Math.round(count * ppActDouble * 100)/100
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
            <h2 class="h2Light">Создать приход</h2>
            <form:form method="POST" modelAttribute="incomeForm" id="incomeForm">
            <table class="addIncome">
                <tr>
                    <th>Товар</th>
                    <th>Количество</th>
                    <th>Цена покупки, руб.</th>
                    <th>Цена покупки окончательная, руб.</th>
                    <th>Артикул в магазине</th>
                    <th>Магазин покупки</th>
                    <th>Номер партии</th>
                    <th>Сумма покупки, руб.</th>
                    <th>Сумма покупки окончательная, руб.</th>
                </tr>
                <tr>
                    <td>
                        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
                        <form:select required="true" path="item" onchange="javascript:handleSelect(this)">
                            <form:option value="-1" label=""/>
                            <form:option value="0" label="Добавить товар"/>
                                <c:forEach items="${items}" var="item">
                                    <form:option value="${item.id}" label="${item.name}"/>
                                </c:forEach>
                        </form:select>
                    <td><form:input type="number" placeholder="Количество" min = "0" path="count"
                                    onchange="javascript:handlePrice()" onfocus="javascript:clearCount()"/></td>
                    <td><form:input type="number" placeholder="Цена покупки" min = "0" step="0.01" path="purchasePriceDouble"
                                    onchange="javascript:handlePrice()" onfocus="javascript:clearPpDouble()"/></td>
                    <td><form:input type="number" placeholder="Цена покупки окончательная" min = "0" step="0.01" path="purchasePriceActDouble"
                                    onchange="javascript:handlePrice()" onfocus="javascript:clearPpActDouble()"/></td>
                    <td><form:input type="text" path="storeArticle" placeholder="Артикул в магазине"/></td>
                    <td><form:input type="text" path="store" placeholder="Магазин покупки"/></td>
                    <td><form:input type="text" placeholder="Номер партии"  path="batchNumber"/></td>
                    <td><div id="ppSum" class="addIncomeInput">0.00</div></td>
                    <td><div id="ppActSum" class="addIncomeInput">0.00</div></td>
                </tr>
                <tr>
                    <td colspan="9">
                        <button type="submit" id="subm" disabled="true">Добавить</button>
                    </td>
                </tr>
            </table>
            </form:form>
        </div>

    <div class="innerDivTr">
        <h2 class="h2Light">Приходы ${date}</h2>
        <table class="todayIncomes">
            <tr>
                <th>Добавил</th>
                <th>Дата</th>
                <th>Товар</th>
                <th>Количество</th>
                <th>Цена покупки</th>
                <th>Цена покупки окончательная</th>
                <th>Артикул в магазине</th>
                <th>Магазин покупки</th>
                <th>Номер партии</th>
                <th>Сумма покупки, руб.</th>
                <th>Сумма покупки окончательная, руб.</th>
                <th id="editth" class="edit" hidden></th>
                <th id="deleteth" class="edit" hidden></th>
            </tr>
                <c:forEach items="${todayIncomes}" var="income">
                    <tr onmouseover="javascript:showEditPanel(${income.id})"
                        onmouseout="javascript:hideEditPanel(${income.id})">
                        <td>${income.userName}</td>
                        <td>${income.date}</td>
                        <td>${income.item.name}</td>
                        <td>${income.count}</td>
                        <td>${income.purchasePrice/100}</td>
                        <td>${income.purchasePriceAct/100}</td>
                        <td>${income.storeArticle}</td>
                        <td>${income.store}</td>
                        <td>${income.batchNumber}</td>
                        <td>${income.count * income.purchasePrice/100}</td>
                        <td>${income.count * income.purchasePriceAct/100}</td>
                        <td class="edit" id="edit${income.id}" hidden>
                            <a href="edit_income/${income.id}">Редактировать</a>
                        </td>
                        <td class="edit" id="delete${income.id}" hidden>
                            <a href="delete_income/${income.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>