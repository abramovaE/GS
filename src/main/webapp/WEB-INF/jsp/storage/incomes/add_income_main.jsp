<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Add new incomeMain</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
        <%@include file="/resources/index_style.css" %>
        <%@include file="/resources/add_income_main_style.css" %>
    </style>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/datePicker.js"></script>
</head>
<body  class="bodyClassGreen">
    <sec:authorize access="!isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>

    <script type="text/javascript">
        let globalEans = new Array();
        let globalItems = new Array();

        $(document).ready(function() {
            let itemObj;
            <c:forEach var="ean" items="${eans}">
                if(globalEans.indexOf(`${ean}`) === -1){
                    globalEans.push(`${ean}`);
                }
            </c:forEach>
            <c:forEach var="item" items="${items}">
                itemObj = new Object();
                itemObj.id = ${item.id};
                itemObj.name = `${item.name}`;
                itemObj.ean = `${item.ean}`;
                if(globalItems.indexOf(itemObj) === -1) {
                    globalItems.push(itemObj);
                }
            </c:forEach>
        });

        function handleItem(index){
            const inputItem = document.getElementById('item'+index).value;
            const table = document.getElementById('incomeStringTable');
            let c = 0;
            if(globalEans.indexOf(inputItem) === -1){
                const answer = window.confirm("Такого товара нет в базе. Создать?");
                if (answer) {
                    document.activeElement.blur();
                    c = 1;
                    document.getElementById('item' + index).value = '';
                    document.getElementById('itemId' + index).value = '';
                    document.getElementById('name' + index).value = '';
                    window.open("add_item");
                }
            } else {
                setMiddlePrice(inputItem, index)
                c = incrementCount(table, inputItem, index)
            }
            if(c == 0) {
                const tr = document.getElementById("tr" + (index + 1));
                tr.hidden = false;
            }
        }

        function setMiddlePrice(ean, index){
            globalItems.forEach(function(item, i, arr){
                if(item.ean == ean){
                    let itemIdCell = document.getElementById('itemId' + index);
                    itemIdCell.value = item.id;
                    let itemNameCell = document.getElementById('name' + index);
                    itemNameCell.value = item.name;
                }
            });

<%--            <c:forEach var="model" items="${items}">--%>
<%--            if("${model.ean}" == ean){--%>
<%--                let itemIdCell = document.getElementById('itemId' + index);--%>
<%--                itemIdCell.value = ${model.id};--%>
<%--                let itemNameCell = document.getElementById('name' + index);--%>
<%--                itemNameCell.value = '${model.name}';--%>
<%--            }--%>
<%--            </c:forEach>--%>
        }

        function incrementCount(table, inputItem, index){
            for(let j = 1; j < table.rows.length; j++){
                let itemId = document.getElementById("item" + j).value;
                if(itemId === inputItem){
                    let countCell = document.getElementById("count" + j);
                    countCell.value = Number(countCell.value) + 1
                    if(countCell.value > 1) {
                        document.getElementById('item' + index).value = "";
                        document.getElementById('itemId' + index).value = '';
                        document.getElementById('name' + index).value = '';
                        return 1;
                    }
                }
            }
            return 0;
        }

        function addIncomeMain() {
        var isSubmit = true;
        let incomeStrings = new Array();
        const table = document.getElementById('incomeStringTable');
        let index;
        for (index = 1; index < table.rows.length; index++) {
            var itemId = document.getElementById("itemId" + index).value;
            const count = document.getElementById("count" + index).value;
            const purPrice = document.getElementById("purPrice" + index).value;
            const purPriceAct = document.getElementById("purPriceAct" + index).value;
            const storeArticle = document.getElementById("storeArticle" + index).value;
            const batchNumber = document.getElementById("batchNumber" + index).value;
            const itemString = new Object();
            if (itemId.length > 0) {
                // itemId=itemId.split("::")[2]
                if (count.length === 0) {
                    alert("Введите количество");
                    isSubmit = false;
                    break
                }
                else if (purPrice.length === 0) {
                    alert("Введите цену");
                    isSubmit = false;
                    break

                }
                else if (purPriceAct.length === 0) {
                    alert("Введите фактическую цену");
                    isSubmit = false;
                    break

                }
                else if (storeArticle.length === 0) {
                    alert("Введите артикул товара в магазине покупки");
                    isSubmit = false;
                    break

                }
                else if (batchNumber.length === 0) {
                    alert("Введите номер партии");
                    isSubmit = false;
                    break

                }
                itemString.itemId = itemId;
                itemString.count = count;
                itemString.purPrice = purPrice;
                itemString.purPriceAct = purPriceAct;
                itemString.storeArticle = storeArticle;
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
        let generalSum = 0;
        let generalSumAct = 0;

        for (index = 1; index < table.rows.length; index++) {
            const count = document.getElementById("count" + index).value;
            const purPrice = document.getElementById("purPrice" + index).value;
            const purPriceAct = document.getElementById("purPriceAct" + index).value;
            let ppSum = Math.round(count * purPrice * 100)/100
            let ppActSum = Math.round(count * purPriceAct * 100)/100
            document.getElementById('ppSum'+index).innerHTML = ppSum
            document.getElementById('ppActSum'+index).innerHTML = ppActSum
            generalSum = generalSum + ppSum
            generalSumAct = generalSumAct + ppActSum
        }
        document.getElementById("ppMainSum").innerHTML = Math.round(generalSum*100)/100
        document.getElementById("ppMainSumAct").innerHTML = Math.round(generalSumAct*100)/100
    }

        function addIncomeString() {
        const id = 'incomeStringTable';
        const table = document.getElementById(id);
        let rowIndex = table.rows.length;
        const tableHeader = document.getElementById('incomeStringTableHeader');
        tableHeader.hidden = false;
        const tr = document.getElementById('tr' + rowIndex);
        tr.closest('tr' + rowIndex)
    }

        function updateItems() {
            let dataList = document.getElementById("dataList")
            let xhr = new XMLHttpRequest();
            xhr.overrideMimeType("application/json");
            xhr.open('GET', 'update', true);
            xhr.onload = function() {
                let arr = JSON.parse(xhr.response);
                arr.forEach(function(item, i, arr) {
                    if(globalEans.indexOf(item.ean) === -1){
                        // alert(item.ean)
                        globalEans.push(item.ean)
                        ${eans.add(item.ean)}
                        let newItem = Object();
                        newItem.id = item.id;
                        newItem.ean = item.ean;
                        newItem.name = item.name;
                        globalItems.push(newItem);
                        let option = document.createElement("option")
                        option.value = item.ean
                        dataList.appendChild(option)
                     }
                });
            };
            xhr.onerror = function() { // происходит, только когда запрос совсем не получилось выполнить
                alert('Ошибка соединения');
            };
            xhr.send();
        }

</script>
    <div class="topPanel">
        <div class="topPanelFirst">
            <div class="username">${pageContext.request.userPrincipal.name}</div>
        </div>

<%--        <div class="topPanelMiddle">--%>
<%--            <div><a href="#" onclick="updateItems()">Update</a></div>--%>
<%--        </div>--%>
        <div class="topPanelLast">
            <div><a href="/GS">На главную</a></div>
        </div>
    </div>

    <h2 class="h2Light">Создать приход</h2>
    <div class="leftright">
        <div class="left">
            <div class="outerDivLogin">
                <form:form method="POST" modelAttribute="incomeMainForm" id="incomeMainForm"
                           onsubmit="return addIncomeMain();">
                    <form:hidden path="id"/>
                    <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
                    <form:hidden path="incomeStrings"/>

                    <div class="innerDivLogin">
                        <form:input
                                autocomplete="false"
                                id="datepicker"
                                type="text"
                                path="date"
                                placeholder="Дата"
                                class="inputClassLight"/>
                    </div>
                    <div class="innerDivLogin">
                        <form:input type="text" path="store"
                                    placeholder="Магазин покупки"
                                    id="incomeMainStore" class="inputClassLight"/>
                    </div>
                    <div class="innerDivLogin">
                        <button type="submit" class="inputClassLight" id="submit">Добавить</button>
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
            <tr id="incomeStringTableHeader">
                <th>Номер партии</th>
                <th>Штрих-код</th>

                <th>Товар</th>
                <th hidden>Id</th>

                <th>Количество</th>
                <th>Цена покупки, руб.</th>
                <th>Цена покупки окончательная, руб.</th>
                <th>Артикул в магазине</th>

                <th>Сумма покупки, руб.</th>
                <th>Сумма покупки окончательная, руб.</th>
            </tr>

            <c:forEach var="rowIndex" begin="1" end="100" step="1" varStatus="index">
                    <tr
                            <c:if test="${index.count>1}">
                                hidden
                            </c:if>
                            id="tr${index.count}">
                        <td><input type="text"
                                   id="batchNumber${index.count}"
                                   required="true"
                                   placeholder="Номер партии"
                                   path="batchNumber"/></td>
                        <td>
                            <input  autocomplete="off"
                                    name="inputItem"
                                    list="dataList"
                                    placeholder="Товар"
                                    id="item${index.count}"
                                    autofocus="true"
                                    onchange="handleItem(${index.count})"
                                    onclick="updateItems()">
                            <datalist id="dataList">
                                <c:forEach var="item" items="${eans}">
                                    <option value="${item}" ></option>
                                </c:forEach>
                            </datalist>
                        </td>

                        <td>
                            <input required="true" id="name${index.count}"
                                   placeholder="Наименование"/>
                        </td>

                        <td hidden>
                            <input required="true" id="itemId${index.count}"/>
                        </td>
                        <td>
                            <input type="number" required="true" id="count${index.count}"
                                   placeholder="Количество" min = "0"
                                   onchange="handlePrice()" onfocus="clearCount()"/>
                        </td>
                        <td><input type="number" placeholder="Цена покупки"
                                   id="purPrice${index.count}"
                                   min = "0" step="0.01"
                                   required="true"
                                   onchange="javascript:handlePrice()"/>
                        </td>
                        <td><input type="number" id="purPriceAct${index.count}" required="true"
                                   placeholder="Цена покупки окончательная" min = "0" step="0.01"
                                   oninput="javascript:handlePrice()"/>
                        </td>
                        <td><input type="text" id="storeArticle${index.count}"
                                   placeholder="Артикул в магазине" required="true"/></td>

                        <td id="purPriceSum${index.count}">
                            <div type="text" id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                        </td>
                        <td id="purPriceActSum${index.count}">
                            <div type="text" id="ppActSum${index.count}" class="addIncomeInput">0.00</div>
                        </td>
                    </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>