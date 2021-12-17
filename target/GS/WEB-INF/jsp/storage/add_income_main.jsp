<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Add new incomeMain</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
        <%@include file="/resources/index_style.css" %>
    </style>
</head>
<body  class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<script type="text/javascript">

    function handlePrice(){}
    function clearCount(){}

    function addIncomeString() {
        var id = 'incomeStringTable';
        var table = document.getElementById(id);
        var tableHeader = document.getElementById('incomeStringTableHeader');
        tableHeader.hidden = false;
        var row = document.createElement('tr')
        var item = document.createElement('td')
        var select = document.createElement('select')
        item.appendChild(document.createTextNode('item'))

        var count = document.createElement('td')
        var countInput = document.createElement('input')
        countInput.type="number"
        countInput.required=true
        countInput.placeholder="Количество"
        countInput.min="0"
        countInput.onchange=handlePrice()
        countInput.onfocus=clearCount()
        count.appendChild(countInput)

        var purPrice = document.createElement('td')
        purPrice.appendChild (document.createTextNode('purPrice'))
        var purPriceAct = document.createElement('td')
        purPriceAct.appendChild (document.createTextNode('purPriceAct'))
        var storeArticle = document.createElement('td')
        storeArticle.appendChild (document.createTextNode('article'))
        var store = document.createElement('td')
        store.appendChild (document.createTextNode('store'))
        var batchNumber = document.createElement('td')
        batchNumber.appendChild (document.createTextNode('batchNumber'))
        var purPriceSum = document.createElement('td')
        purPriceSum.appendChild (document.createTextNode('purPriceSum'))
        var purPriceActSum = document.createElement('td')
        purPriceActSum.appendChild (document.createTextNode('purPriceActSum'))

        row.appendChild(item);
        row.appendChild(count);
        row.appendChild(purPrice);
        row.appendChild(purPriceAct);
        row.appendChild(storeArticle);
        row.appendChild(store);
        row.appendChild(batchNumber);
        row.appendChild(purPriceSum);
        row.appendChild(purPriceActSum);
        table.appendChild(row);



                    <%--<tr>--%>
                    <%--    <td>--%>
                    <%--        <select required="true" path="item" onchange="javascript:handleSelect(this)">--%>
                    <%--            <option value="-1" label=""/>--%>
                    <%--            <option value="0" label="Добавить товар"/>--%>
                    <%--        <c:forEach items="${items}" var="item">--%>
                    <%--            <option value="${item.id}" label="${item.name}"/>--%>
                    <%--        </c:forEach>--%>
                    <%--        </select>--%>

                    <%--    <td><input type="number" placeholder="Цена покупки" min = "0" step="0.01" path="purchasePriceDouble"--%>
                    <%--                    onchange="javascript:handlePrice()" onfocus="javascript:clearPpDouble()"/></td>--%>
                    <%--    <td><input type="number" placeholder="Цена покупки окончательная" min = "0" step="0.01" path="purchasePriceActDouble"--%>
                    <%--                    onchange="javascript:handlePrice()" onfocus="javascript:clearPpActDouble()"/></td>--%>
                    <%--    <td><input type="text" path="storeArticle" placeholder="Артикул в магазине"/></td>--%>
                    <%--    <td><input type="text" path="store" placeholder="Магазин покупки"/></td>--%>
                    <%--    <td><input type="text" placeholder="Номер партии"  path="batchNumber"/></td>--%>
                    <%--    <td><div id="ppSum" class="addIncomeInput">0.00</div></td>--%>
                    <%--    <td><div id="ppActSum" class="addIncomeInput">0.00</div></td>--%>
                    <%--</tr>--%>

                    // </tr>
    }
</script>

<div class="topPanel">
    <div class="topPanelFirst">
        <div class="username">${pageContext.request.userPrincipal.name}</div>
    </div>
<%--    <div class="topPanelCentral">--%>
<%--        <div><a href="add_income_string">Добавить строку</a></div>--%>
<%--    </div>--%>
    <div class="topPanelLast">
        <div><a href="/GS">На главную</a></div>
    </div>
</div>



<div class="outerDivLogin">
    <form:form method="POST" modelAttribute="incomeMainForm">
        <h2 class="h2Light">Создать приход</h2>
        <form:hidden path="id"/>
        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
        <form:hidden path="incomeStrings"/>
        <div class="innerDivLogin">
            <form:input type="text" path="date" placeholder="Дата" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input type="text" path="store" placeholder="Магазин покупки" class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <button type="button" class="inputClassLight" onclick="javascript:addIncomeString()">Добавить товар</button>
        </div>
        <div class="innerDivLogin">
            <button type="submit" class="inputClassLight">Добавить</button>
        </div>
    </form:form>
</div>

<div class="innerDivTr">
<%--    <h2 class="h2Light">Создать приход</h2>--%>
<%--    <form:form method="POST" id="incomeStringForm">--%>
        <table class="addIncome" id="incomeStringTable">
            <tr id="incomeStringTableHeader" hidden="true">
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
        </table>
<%--    </form:form>--%>
</div>

</body>
</html>