<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Add new expandMain</title>
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
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript">
        function handleItem(index){
            let inputItem = document.getElementById('item'+index).value;
            const table = document.getElementById('expandStringTable');
            var c = 0;
            if(inputItem.indexOf("::") === -1){
                if('${eans}'.indexOf(inputItem) === -1){
                    window.alert("Такого товара нет в базе");
                    document.getElementById('item'+index).value = "";
                }
                else {
                    const ean = Number(inputItem.split("::")[3])
                    for(var j = 1; j < table.rows.length; j++){
                        var itemId = document.getElementById("item" + j).value;
                        if(itemId === inputItem){
                            var countCell = document.getElementById("count" + j);
                            countCell.value = Number(countCell.value) + 1
                            if(countCell.value > 1) {
                                document.getElementById('item' + index).value = "";
                                c = 1;
                            }
                        }
                    }
                    for(var i = 0; i < '${items.size()}'; i++){
                        if(Number('${items.get(i).ean}') == ean){
                            var priceCell = document.getElementById("price" + index);
                            priceCell.value = ${items.get(i).middlePrice/100}
                        }
                    }
                    if(c == 0) {
                        const tr = document.getElementById("tr" + (index + 1));
                        tr.hidden = false;
                    }
                }
            } else {
                const ean = Number(inputItem.split("::")[3])
                for(var j = 1; j < table.rows.length; j++){
                    var itemId = document.getElementById("item" + j).value;
                    if(itemId === inputItem){
                        var countCell = document.getElementById("count" + j);
                        countCell.value = Number(countCell.value) + 1
                        if(countCell.value > 1) {
                            document.getElementById('item' + index).value = "";
                            c = 1;
                        }
                    }
                }
                for(var i = 0; i < '${items.size()}'; i++){
                    if(Number('${items.get(i).ean}') == ean){
                        var priceCell = document.getElementById("price" + index);
                        priceCell.value = ${items.get(i).middlePrice/100}
                    }
                }
                if(c == 0) {
                    const tr = document.getElementById("tr" + (index + 1));
                    tr.hidden = false;
                }
            }
        }

        function addExpandMain() {
        let isSubmit = true;
        let expandStrings = [];
        const table = document.getElementById('expandStringTable');
        let index;
        for (index = 1; index < table.rows.length; index++) {
            var itemId = document.getElementById("item" + index).value;
            const count = document.getElementById("count" + index).value;
            const price = document.getElementById("price" + index).value;
            const batchNumber = document.getElementById("batchNumber" + index).value;
            const expandString = {};
            if (itemId.length > 0) {
                itemId=itemId.split("::")[2]
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
        $(function () {
            $("#datepicker").datepicker({dateFormat: "dd.mm.yy"});
        });

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
                        <form:input type="text" path="store"
                                    placeholder="Магазин"
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
                <th>Товар</th>
                <th>Количество</th>
                <th>Цена продажи, руб.</th>
                <th>Номер партии</th>
                <th>Сумма продажи, руб.</th>
            </tr>
            <c:forEach var="rowIndex" begin="1" end="100" step="1" varStatus="index">
                <c:if test="${index.count>1}">
                    <tr hidden="true" id="tr${index.count}">
                        <td>
                            <input autocomplete="off" name="inputItem" list="dataList${index.count}"
                                   placeholder="Товар" id="item${index.count}" autofocus="true"
                                   onchange="javascript:handleItem(${index.count})">
                            <datalist id="dataList${index.count}">
                                <c:forEach var="item" items="${items}">
                                    <option value="${item.name}::${item.count}::${item.id}::${item.ean}" ></option>
                                </c:forEach>
                            </datalist>
                        </td>
                        <td>
                            <input type="number" required="true" id="count${index.count}"
                                   placeholder="Количество" min = "0"
                                   onchange="javascript:handlePrice()" onfocus="javascript:clearCount()"/>
                        </td>
                        <td><input type="number" placeholder="Цена продажи"
                                   id="price${index.count}"
                                   min = "0" step="0.01"
                                   required="true"
                                   onchange="javascript:handlePrice()"/>
                        </td>
                        <td><input type="text" id="batchNumber${index.count}" required="true"
                                   placeholder="Номер партии"  path="batchNumber"/></td>
                        <td id="priceSum${index.count}">
                            <div type="text" id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${index.count==1}">
                    <tr id="tr${index.count}">
                        <td>
                            <input  autocomplete="off" name="inputItem" list="dataList${index.count}"
                                    placeholder="Товар" id="item${index.count}" autofocus="true"
                                    onchange="javascript:handleItem(${index.count})">
                            <datalist id="dataList${index.count}">
                                <c:forEach var="item" items="${items}">
                                    <option value="${item.name}::${item.count}::${item.id}::${item.ean}" ></option>
                                </c:forEach>
                            </datalist>
                        </td>
                        <td>
                            <input type="number" required="true" id="count${index.count}"
                                   placeholder="Количество" min = "0"
                                   onchange="javascript:handlePrice()" onfocus="javascript:clearCount()"/>
                        </td>
                        <td><input type="number" placeholder="Цена продажи"
                                   id="price${index.count}"
                                   min = "0" step="0.01"
                                   required="true"
                                   onchange="javascript:handlePrice()"/>
                        </td>
                        <td><input type="text" id="batchNumber${index.count}" required="true"
                                   placeholder="Номер партии"  path="batchNumber"/></td>
                        <td id="priceSum${index.count}">
                            <div type="text" id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</body>
</html>