<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Show incomeMain</title>
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
        function handleStore(){
            const id = 'incomeStringTable';
            const table = document.getElementById(id);
            let index;
            for (index = 1; index < table.rows.length; ++index) {
                const storeItem = document.getElementById("store"+index);
                storeItem.value=document.getElementById("incomeMainStore").value
            }
        }
        function handleItem(index){
            const inputItem = document.getElementById('item'+index).value;
            if(inputItem.indexOf("::") === -1){
                if('${eans}'.indexOf(inputItem) === -1){
                    const answer = window.confirm("Такого товара нет в базе. Создать?");
                    if (answer) {
                        window.location = "add_item";
                    }
                }
            }
            const tr = document.getElementById("tr" + (index +1));
            tr.hidden=false;
        }
        function addIncomeMain() {
        var isSubmit = true;
        let incomeStrings = new Array();
        const table = document.getElementById('incomeStringTable');
        let index;
        for (index = 1; index < table.rows.length; ++index) {
            // const selectedIndex = document.getElementById("select" + index).options.selectedIndex;
            // const itemId = document.getElementById("select" + index).options[selectedIndex].value;
            var itemId = document.getElementById("item" + index).value;
            const count = document.getElementById("count" + index).value;
            const purPrice = document.getElementById("purPrice" + index).value;
            const purPriceAct = document.getElementById("purPriceAct" + index).value;
            const storeArticle = document.getElementById("storeArticle" + index).value;
            const store = document.getElementById("store" + index).value;
            const batchNumber = document.getElementById("batchNumber" + index).value;
            const itemString = new Object();

            if (itemId.length > 0) {
                itemId=itemId.split("::")[2]
                //     alert("Выберите товар");
                //     isSubmit = false;
                // }
                if (count.length == 0) {
                    alert("Введите количество");
                    isSubmit = false;
                }
                else if (purPrice.length == 0) {
                    alert("Введите цену");
                    isSubmit = false;
                }
                else if (purPriceAct.length == 0) {
                    alert("Введите фактическую цену");
                    isSubmit = false;
                }
                else if (storeArticle.length == 0) {
                    alert("Введите артикул товара в магазине покупки");
                    isSubmit = false;
                }
                else if (store.length == 0) {
                    alert("Введите магазин покупки");
                    isSubmit = false;
                }
                else if (batchNumber.length == 0) {
                    alert("Введите номер партии");
                    isSubmit = false;
                }

                itemString.itemId = itemId;
                itemString.count = count;
                itemString.purPrice = purPrice;
                itemString.purPriceAct = purPriceAct;
                itemString.storeArticle = storeArticle;
                itemString.store = store;
                itemString.batchNumber = batchNumber;
                incomeStrings.push(itemString);
            }
        }
        if (isSubmit) {
            let incomeMain = document.getElementById('incomeMainForm');
            const incomeJson = document.createElement('input');
            incomeJson.name = "incomeJson";
            incomeJson.value = JSON.stringify(incomeStrings);
            incomeJson.hidden = true;
            incomeMain.appendChild(incomeJson);
            incomeMain.submit();
        }
        return isSubmit;
    }
        function handlePrice(){
        const id = 'incomeStringTable';
        const table = document.getElementById(id);
        let index;
        // let submitDisable;
        let generalSum = 0;
        let generalSumAct = 0;

        for (index = 1; index < table.rows.length; ++index) {
            // const selectedIndex = document.getElementById("select" + index).options.selectedIndex;
            // const itemId = document.getElementById("select" + index).options[selectedIndex].value;
            const itemId = document.getElementById("item" + index).value;
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
        document.getElementById("ppMainSum").innerHTML = Math.round(generalSum*100)/100
        document.getElementById("ppMainSumAct").innerHTML = Math.round(generalSumAct*100)/100
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
        const tr = document.getElementById('tr' + rowIndex);
        tr.closest('tr' + rowIndex)
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

    <h2 class="h2Light">Приход</h2>
    <form:form method="POST" modelAttribute="incomeMain" id="incomeMain" onsubmit="return addIncomeMain();">

    <div class="leftright">
        <div class="left">
            <div class="outerDivLogin">
                    <form:hidden path="id"/>
                    <form:hidden path="incomeStrings"/>
                    <div class="innerDivLogin">
                        <form:input type="text" path="userName"  readonly="true"
                                    class="inputClassLight"/>
                    </div>
                    <div class="innerDivLogin">
                        <form:input readonly="true" type="text" path="date"
                                    placeholder="Дата" class="inputClassLight"/>
                    </div>
                    <div class="innerDivLogin">
                        <form:input readonly="true" type="text" path="store"
                                    placeholder="Магазин покупки"
                                    id="incomeMainStore"
                                    onchange="javascript:handleStore()"
                                    class="inputClassLight"/>
                    </div>
<%--                    <div class="innerDivLogin">--%>
<%--                        <button type="button" class="inputClassLight" onclick="javascript:addIncomeString()">Добавить товар</button>--%>
<%--                    </div>--%>
<%--                    <div class="innerDivLogin">--%>
<%--                        <button type="submit" class="inputClassLight" id="submit">Добавить</button>--%>
<%--                    </div>--%>
            </div>
        </div>
            <div class="right">
            <div class="outerDivLogin">
                <div class="innerDivLogin">
                    <div class="label">Итог, руб.</div>
                    <div id="ppMainSum" class="addIncomeInput">${incomeMain.sum/100}</div>
                </div>
                <div class="innerDivLogin">
                    <div class="label">Итог факт., руб.</div>
                    <div id="ppMainSumAct" class="addIncomeInput">${incomeMain.sumAct/100}</div>
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
    </form:form>

    <div class="innerDivTr">
        <table class="addIncome" id="incomeStringTable">
            <tr id="incomeStringTableHeader">
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

            <c:forEach items="${incomeMain.incomeStrings}"
                       var="incomeString" varStatus="index">
                    <tr id="tr${index.count}">
                        <td>
                            <input readonly="true" autocomplete="off" name="inputItem" list="dataList${index.count}"
                                    placeholder="Товар" id="item${index.count}" autofocus="true"
                                    onchange="javascript:handleItem(${index.count})"
                                    value="${incomeString.item.name}::${incomeString.item.count}::${incomeString.item.id}::${incomeString.item.ean}">
                            <datalist id="dataList${index.count}">
                                <c:forEach var="item" items="${items}">
                                    <option value="${item.name}::${item.count}::${item.id}::${item.ean}" ></option>
                                </c:forEach>
                            </datalist>
                        </td>
                        <td>
                            <input readonly="true" type="number" required="true" id="count${index.count}"
                                   placeholder="Количество" min = "0" value="${incomeString.count}"
                                   onchange="javascript:handlePrice()" onfocus="javascript:clearCount()"/>
                        </td>
                        <td><input readonly="true" type="number" placeholder="Цена покупки"
                                   id="purPrice${index.count}"
                                   min = "0" step="0.01"
                                   required="true" value="${incomeString.purchasePrice/100}"
                                   onchange="javascript:handlePrice()" onfocus="javascript:clearPpDouble()"/>
                        </td>
                        <td><input readonly="true" type="number" id="purPriceAct${index.count}" required="true"
                                   placeholder="Цена покупки окончательная" min = "0" step="0.01"
                                   value="${incomeString.purchasePriceAct/100}"
                                   oninput="javascript:handlePrice()"
                                   onchange="javascript:clearPpActDouble()"/>
                        </td>
                        <td><input readonly="true" type="text" id="storeArticle${index.count}"
                                   placeholder="Артикул в магазине" required="true"
                                   value="${incomeString.storeArticle}"/></td>
                        <td><input readonly="true" type="text" id="store${index.count}" required="true"
                                   placeholder="Магазин покупки" value="${incomeString.store}" /></td>
                        <td><input readonly="true" type="text" id="batchNumber${index.count}" required="true"
                                   placeholder="Номер партии"  path="batchNumber" value="${incomeString.batchNumber}"/></td>
                        <td id="purPriceSum${index.count}">
                            <div type="text" id="ppSum${index.count}" class="addIncomeInput">
                                ${incomeString.count * incomeString.purchasePrice/100}
                            </div>
                        </td>
                        <td id="purPriceActSum${index.count}">
                            <div type="text" id="ppActSum${index.count}" class="addIncomeInput">
                                    ${incomeString.count * incomeString.purchasePriceAct/100}
                            </div>
                        </td>
                    </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>