<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Add new expandMain</title>
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

        $(document).ready(function() {
            handleItem(index);
        });

        function handleItem(index){
            let inputItem = document.getElementById('item'+index).value;
            const table = document.getElementById('expandStringTable');
            let c = 0;
                if('${eans}'.indexOf(inputItem) === -1){
                    window.alert("Такого товара нет в базе");
                    document.getElementById('item'+index).value = "";
                }
                else {
                    setMiddlePrice(inputItem, index)
                    c = incrementCount(table, inputItem, index)
                    if(c == 0) {
                        const tr = document.getElementById("tr" + (index + 1));
                        tr.hidden = false;
                    }
                }
            handlePrice();
        }

        function incrementCount(table, inputItem, index){
            for(let j = 1; j < table.rows.length; j++){
                let itemId = document.getElementById("item" + j).value;
                if(itemId === inputItem){
                    let countCell = document.getElementById("count" + j);
                    countCell.value = Number(countCell.value) + 1
                    if(countCell.value > 1) {
                        document.getElementById('item' + index).value = '';
                        document.getElementById('price' + index).value = '';
                        document.getElementById('itemId' + index).value = '';
                        document.getElementById('name' + index).value = '';
                        return 1;
                    }
                }
            }
            return 0;
        }

        function setMiddlePrice(ean, index){
            <c:forEach var="model" items="${items}">
            if("${model.ean}" == ean){
                let priceCell = document.getElementById('price' + index);
                priceCell.value = ${model.middlePrice/100};
                let itemIdCell = document.getElementById('itemId' + index);
                itemIdCell.value = ${model.id};
                let itemNameCell = document.getElementById('name' + index);
                itemNameCell.value = '${model.name}';
            }
            </c:forEach>
        }

        function addExpandMain() {
        let isSubmit = true;
        let expandStrings = [];
        const table = document.getElementById('expandStringTable');
        let index;
        for (index = 1; index < table.rows.length; index++) {
            let itemId = document.getElementById("itemId" + index).value;
            const count = document.getElementById("count" + index).value;
            const price = document.getElementById("price" + index).value;
            const batchNumber = document.getElementById("batchNumber" + index).value;
            const expandString = {};
            if (itemId.length > 0) {
                // itemId=itemId.split("::")[2]
                if (count.length === 0) {
                    alert("Введите количество");
                    isSubmit = false;
                }
                else if (price.length === 0) {
                    alert("Введите цену");
                    isSubmit = false;
                }
                else if (batchNumber.length === 0) {
                    alert("Введите номер партии");
                    isSubmit = false;
                }
                expandString.itemId = itemId;
                expandString.count = count;
                expandString.price = price;
                expandString.batchNumber = batchNumber;
                expandStrings.push(expandString);
            }
        }
        if (isSubmit) {
            let expandMain = document.getElementById('expandMainForm');
            const expandJson = document.createElement('input');
            expandJson.name = "expandJson";
            expandJson.value = JSON.stringify(expandStrings);
            expandJson.hidden = true;
            expandMain.appendChild(expandJson);
            expandMain.submit();
        }
        return isSubmit;
    }
        function handlePrice(){
        const id = 'expandStringTable';
        const table = document.getElementById(id);
        let index;
        let generalSum = 0;
        for (index = 1; index < table.rows.length; index++) {
            const count = document.getElementById("count" + index).value;
            const price = document.getElementById("price" + index).value;
            let ppSum = Math.round(count * price * 100)/100
            document.getElementById('ppSum'+index).innerHTML = ppSum
            generalSum = generalSum + ppSum
        }
        document.getElementById("ppMainSum").innerHTML = Math.round(generalSum*100)/100
    }
        function addExpandString() {
        const id = 'expandStringTable';
        const table = document.getElementById(id);
        let rowIndex = table.rows.length;
        const tableHeader = document.getElementById('expandStringTableHeader');
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
            <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
        </div>
    </div>

    <h2 class="h2Light">Создать расход</h2>
    <div class="leftright">
        <div class="left">
            <div class="outerDivLogin">
                <form:form method="POST" modelAttribute="expandMainForm" id="expandMainForm"
                           onsubmit="return addExpandMain();">
                    <form:hidden path="id"/>
                    <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
                    <form:hidden path="expandStrings"/>

                    <div class="innerDivLogin">
                        <form:input
                                autocomplete="false"
                                id="datepicker"
                                path="date"
                                placeholder="Дата"
                                class="inputClassLight"/>
                    </div>
                    <div class="innerDivLogin">
                        <form:input type="text"
                                    path="store"
                                    placeholder="Контрагент"
                                    id="expandMainStore" class="inputClassLight"/>
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
            </div>
        </div>
    </div>

    <div class="innerDivTr">
        <table class="addIncome" id="expandStringTable">
            <tr id="expandStringTableHeader">
                <th>Номер партии</th>
                <th>EAN</th>
                <th>Товар</th>
                <th hidden>Id</th>
                <th>Количество</th>
                <th>Цена продажи, руб.</th>
                <th>Сумма продажи, руб.</th>
            </tr>
            <c:forEach var="rowIndex" begin="1" end="100" step="1" varStatus="index">
                    <tr <c:if test="${index.count>1}">
                                hidden
                            </c:if>
                        id="tr${index.count}">
                        <td>
                            <input type="text"
                                   id="batchNumber${index.count}"
                                   required
                                   placeholder="Номер партии"/>
                        </td>
                        <td>
                            <input autocomplete="off"
                                   autofocus
                                   id="item${index.count}"
                                   list="dataList${index.count}"
                                   name="inputItem"
                                   onchange="handleItem(${index.count})"
                                   placeholder="Товар"/>
                            <datalist id="dataList${index.count}">
                                <c:forEach var="item" items="${items}">
                                    <option value="${item.ean}" ></option>
                                </c:forEach>
                            </datalist>
                        </td>
                        <td>
                            <input required
                                   type="text"
                                   id="name${index.count}"
                                   placeholder="Наименование"/>
                        </td>
                        <td hidden>
                            <input
                                   id="itemId${index.count}"/>
                        </td>
                        <td>
                            <input type="number"
                                   required
                                   id="count${index.count}"
                                   placeholder="Количество"
                                   min = "0"
                                   onchange="handlePrice()"/>
                        </td>
                        <td><input type="number"
                                   placeholder="Цена продажи"
                                   id="price${index.count}"
                                   min = "0"
                                   step="0.01"
                                   required
                                   onchange="handlePrice()"/>
                        </td>
                        <td id="priceSum${index.count}">
                            <div id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                        </td>
                    </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>