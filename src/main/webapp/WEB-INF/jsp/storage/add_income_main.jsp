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
        <%@include file="/resources/add_income_main_style.css" %>

    </style>
</head>
<body  class="bodyClassGreen">
    <sec:authorize access="!isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>

    <script type="text/javascript">
    function addIncomeMain(){
        let incomeStrings = new Array();
        const table = document.getElementById('incomeStringTable');
        let index;
        for (index = 1; index < table.rows.length; ++index) {
            const selectedIndex = document.getElementById("select" + index).options.selectedIndex;
            const itemId = document.getElementById("select" + index).options[selectedIndex].value;
            const count = document.getElementById("count" + index).value;
            const purPrice = document.getElementById("purPrice" + index).value;
            const purPriceAct = document.getElementById("purPriceAct" + index).value;
            const storeArticle = document.getElementById("storeArticle" + index).value;
            const store = document.getElementById("store" + index).value;
            const batchNumber = document.getElementById("batchNumber" + index).value;
            const itemString = new Object();
            itemString.itemId = itemId;
            itemString.count=count;
            itemString.purPrice=purPrice;
            itemString.purPriceAct=purPriceAct;
            itemString.storeArticle=storeArticle;
            itemString.store=store;
            itemString.batchNumber=batchNumber;
            incomeStrings.push(itemString);
        }
        let incomeMain = document.getElementById('incomeMainForm');
        const incomeJson = document.createElement('input');
        incomeJson.name="incomeJson";
        incomeJson.value=JSON.stringify(incomeStrings);
        incomeJson.hidden=true;
        incomeMain.appendChild(incomeJson);
        // alert(elm)
        incomeMain.submit();
        // HTMLElement

    }

    function handlePrice(){
        const id = 'incomeStringTable';
        const table = document.getElementById(id);
        let index;
        // let submitDisable;
        let generalSum = 0;
        let generalSumAct = 0;

        for (index = 1; index < table.rows.length; ++index) {
            const selectedIndex = document.getElementById("select" + index).options.selectedIndex;
            const itemId = document.getElementById("select" + index).options[selectedIndex].value;
            const count = document.getElementById("count" + index).value;
            const purPrice = document.getElementById("purPrice" + index).value;
            const purPriceAct = document.getElementById("purPriceAct" + index).value;
            let ppSum = Math.round(count * purPrice * 100)/100
            let ppActSum = Math.round(count * purPriceAct * 100)/100
            document.getElementById('ppSum'+index).innerHTML = ppSum
            document.getElementById('ppActSum'+index).innerHTML = ppActSum
            generalSum = generalSum + ppSum
            generalSumAct = generalSumAct + ppActSum
            // if(count > 0 && itemId > 0){
            //     submitDisable = false;
            // } else {
            //     submitDisable = true;
            // }

        }
        document.getElementById("ppMainSum").innerHTML = generalSum
        document.getElementById("ppMainSumAct").innerHTML = generalSumAct
        // document.getElementById('submit').disabled = submitDisable;
    }


    function clearPpDouble(){}
    function clearPpActDouble(){}
    function handleSelect(elm){}

    function addIncomeString() {
        const id = 'incomeStringTable';
        const table = document.getElementById(id);
        let rowIndex = table.rows.length;

        const tableHeader = document.getElementById('incomeStringTableHeader');
        tableHeader.hidden = false;
        const row = document.createElement('tr');

        const item = document.createElement('td');
        const select = document.createElement('select');
        select.id="select"+rowIndex;
        select.required=true;
        select.onchange=handleSelect(this);
        select.options[select.options.length]=new Option("", "-1");
        select.options[select.options.length]=new Option("Добавить товар", "0");
        <c:forEach items="${items}" var="item">
            select.options[select.options.length]=new Option(${item.id}, ${item.id});
        </c:forEach>
        item.appendChild(select);
        item.id="item" + rowIndex;


        const count = document.createElement('td');
        let countInput = document.createElement('input');
        countInput.id="count" + rowIndex;
        countInput.type="number";
        countInput.required=true;
        countInput.placeholder="Количество";
        countInput.min="0";
        count.addEventListener("input", handlePrice);
        // count.addEventListener("focus", clearCount);
        count.appendChild(countInput);


        const purPrice = document.createElement('td');
        const purPriceInput = document.createElement('input');
        purPriceInput.id="purPrice" + rowIndex;
        purPriceInput.type="number";
        purPriceInput.placeholder="Цена покупки";
        purPriceInput.min="0";
        purPriceInput.step="0.01";
        purPriceInput.addEventListener("input", handlePrice);
        // purPriceInput.addEventListener("focus", clearPpDouble);
        purPrice.appendChild(purPriceInput);

        const purPriceAct = document.createElement('td');
        const purPriceActInput = document.createElement('input');
        purPriceActInput.id="purPriceAct" + rowIndex;
        purPriceActInput.type="number";
        purPriceActInput.placeholder="Цена покупки окончательная";
        purPriceActInput.min="0";
        purPriceActInput.step="0.01";
        purPriceActInput.addEventListener("input", handlePrice);
        // purPriceActInput.addEventListener("focus", clearPpActDouble);
        purPriceAct.appendChild(purPriceActInput);

        const storeArticle = document.createElement('td');
        const storeArticleInput = document.createElement('input');
        storeArticleInput.id="storeArticle" + rowIndex;
        storeArticleInput.type="text";
        storeArticleInput.placeholder="Артикул в магазине";
        storeArticle.appendChild(storeArticleInput);

        const store = document.createElement('td');
        const storeInput = document.createElement('input');
        storeInput.id="store" + rowIndex;
        storeInput.type="text";
        storeInput.placeholder="Магазин покупки";
        store.appendChild(storeInput);

        const batchNumber = document.createElement('td');
        const batchNumberInput = document.createElement('input');
        batchNumberInput.id= "batchNumber"+rowIndex;
        batchNumberInput.type="text";
        batchNumberInput.placeholder="Номер партии";
        batchNumber.appendChild(batchNumberInput);

        const purPriceSum = document.createElement('td');
        const purPriceSumDiv = document.createElement('div');
        purPriceSumDiv.id="ppSum"+rowIndex;
        purPriceSumDiv.className="addIncomeInput";
        purPriceSumDiv.appendChild(document.createTextNode('0.00'));
        purPriceSum.appendChild(purPriceSumDiv);
        purPriceSum.id="purPriceSum" + rowIndex;

        const purPriceActSum = document.createElement('td');
        const purPriceActSumDiv = document.createElement('div');
        purPriceActSumDiv.id="ppActSum"+rowIndex;
        purPriceActSumDiv.className="addIncomeInput";
        purPriceActSumDiv.appendChild(document.createTextNode('0.00'));
        purPriceActSum.appendChild (purPriceActSumDiv);
        purPriceActSum.id="purPriceActSum" + rowIndex;

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
        <div class="topPanelLast">
            <div><a href="/GS">На главную</a></div>
        </div>
    </div>

    <h2 class="h2Light">Создать приход</h2>
    <div class="leftright">
        <div class="left">
            <div class="outerDivLogin">
                <form:form method="POST" modelAttribute="incomeMainForm" id="incomeMainForm">
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
                        <button type="submit" class="inputClassLight" id="submit"
                                onclick="javascript:addIncomeMain()">Добавить</button>
                    </div>
                </form:form>
            </div>
        </div>
            <div class="right">
            <div class="outerDivLogin">
                <div class="innerDivLogin">
                    <div class="label">Итог, руб.</div>
                    <div id="ppMainSum" class="addIncomeInput">0.00</div>
                </div>
                <div class="innerDivLogin">
                    <div class="label">Итог факт., руб.</div>
                    <div id="ppMainSumAct" class="addIncomeInput">0.00</div>
                </div>
            </div>
        </div>
    </div>

    <div class="innerDivTr">
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
    </div>
</body>
</html>