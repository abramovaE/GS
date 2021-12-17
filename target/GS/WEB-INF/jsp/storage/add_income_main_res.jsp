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
    function addIncomeMain(){
        <%--var incomeStrings = ${incomeStrings}--%>
        <%--var testStrings = ${testStrings}--%>

        const id = 'incomeStringTable'
        const table = document.getElementById(id)
        // for (var i = 0, row; row = table.rows[i]; i++) {
        //     var itemId = row.getElementById('item')
        //     var count = row.getElementById('count')
        //     var purPrice=row.getElementById('purPrice')
        //     var storeArticle=row.getElementById('storeArticle')
        //     var store=row.getElementById('store')
        //     var batchNumber=row.getElementById('batchNumber')
        //     var purPriceSum=row.getElementById('purPriceSum')
        //     var purPriceActSum=row.getElementById('purPriceActSum')
        //     // testStrings.add(itemId)
        //     // testStrings.add(count)
        // }

            // testStrings.add("dfgf")
        document.getElementById('incomeMainForm').submit();

    }
    function handlePrice(){}
    function clearCount(){}
    function clearPpDouble(){}
    function clearPpActDouble(){}
    function handleSelect(elm){}
    function addIncomeString() {
        const id = 'incomeStringTable';
        const table = document.getElementById(id);
        const tableHeader = document.getElementById('incomeStringTableHeader');
        tableHeader.hidden = false;
        const row = document.createElement('tr');
        const item = document.createElement('td');
        const select = document.createElement('select');
        select.required=true
        select.onchange=handleSelect(this)
        select.options[select.options.length]=new Option("", "-1");
        select.options[select.options.length]=new Option("Добавить товар", "0");
        <c:forEach items="${items}" var="item">
            <%--const str = ${item.name} + ' '--%>
            select.options[select.options.length]=new Option("text", ${item.id});
        </c:forEach>
        item.appendChild(select)
        const count = document.createElement('td');
        const countInput = document.createElement('input');
        countInput.type="number"
        countInput.required=true
        countInput.placeholder="Количество"
        countInput.min="0"
        countInput.onchange=handlePrice()
        countInput.onfocus=clearCount()
        count.appendChild(countInput)

        const purPrice = document.createElement('td');
        const purPriceInput = document.createElement('input');
        purPriceInput.type="number"
        purPriceInput.placeholder="Цена покупки"
        purPriceInput.min="0"
        purPriceInput.step="0.01"
        purPriceInput.onchange=handlePrice()
        purPriceInput.onfocus=clearPpDouble()
        purPrice.appendChild(purPriceInput)

        const purPriceAct = document.createElement('td');
        const purPriceActInput = document.createElement('input');
        purPriceActInput.type="number"
        purPriceActInput.placeholder="Цена покупки окончательная"
        purPriceActInput.min="0"
        purPriceActInput.step="0.01"
        purPriceActInput.onchange=handlePrice()
        purPriceActInput.onfocus=clearPpActDouble()
        purPriceAct.appendChild(purPriceActInput)

        const storeArticle = document.createElement('td');
        const storeArticleInput = document.createElement('input');
        storeArticleInput.type="text"
        storeArticleInput.placeholder="Артикул в магазине"
        storeArticle.appendChild(storeArticleInput)

        const store = document.createElement('td');
        const storeInput = document.createElement('input');
        storeInput.type="text"
        storeInput.placeholder="Магазин покупки"
        store.appendChild(storeInput)

        const batchNumber = document.createElement('td');
        const batchNumberInput = document.createElement('input');
        batchNumberInput.type="text"
        batchNumberInput.placeholder="Номер партии"
        batchNumber.appendChild(batchNumberInput)

        const purPriceSum = document.createElement('td');
        const purPriceSumDiv = document.createElement('div');
        purPriceSumDiv.id="ppSum"
        purPriceSumDiv.className="addIncomeInput"
        purPriceSumDiv.appendChild(document.createTextNode('0.00'))
        purPriceSum.appendChild(purPriceSumDiv)

        const purPriceActSum = document.createElement('td');
        const purPriceActSumDiv = document.createElement('div');
        purPriceActSumDiv.id="ppActSum"
        purPriceActSumDiv.className="addIncomeInput"
        purPriceActSumDiv.appendChild(document.createTextNode('0.00'))
        purPriceActSum.appendChild (purPriceActSumDiv)

        item.id="item"
        count.id="count"
        purPrice.id="purPrice"
        purPriceAct.id="purPriceAct"
        storeArticle.id="storeArticle"
        store.id="store"
        batchNumber.id="batchNumber"
        purPriceSum.id="purPriceSum"
        purPriceActSum.id="purPriceActSum"

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
    <form:form method="POST" modelAttribute="incomeMainForm" id="incomeMainForm">
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
            <button type="button" class="inputClassLight" onclick="javascript:addIncomeMain()">Добавить</button>

<%--            <button type="submit" class="inputClassLight">Добавить</button>--%>
        </div>



    </form:form>
</div>


</body>
</html>