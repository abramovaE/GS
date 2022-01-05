<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Show expandMain</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
        <%@include file="/resources/index_style.css" %>
        <%@include file="/resources/add_income_main_style.css" %>
        <%@include file="/resources/show_income_main_style.css" %>
    </style>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/hideShowEditPanel.js"></script>
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
            if(index === 1){
                index = index + ${expandMain.expandStrings.size()}
            }
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
        <%--function handleItem(index){--%>
        <%--    if(index === 1){--%>
        <%--        index = index + ${expandMain.expandStrings.size()}--%>
        <%--    }--%>
        <%--    let inputItem = document.getElementById('item'+index).value;--%>
        <%--    const table = document.getElementById('expandStringTable');--%>
        <%--    // var c = 0;--%>
        <%--    if(inputItem.indexOf("::") === -1){--%>
        <%--        if('${eans}'.indexOf(inputItem) === -1){--%>
        <%--            window.alert("Такого товара нет в базе");--%>
        <%--            document.getElementById('item'+index).value = "";--%>
        <%--        }--%>
        <%--        else {--%>
        <%--            const ean = Number(inputItem.split("::")[3])--%>
        <%--            for(var j = 1; j < table.rows.length; j++){--%>
        <%--                var itemId = document.getElementById("item" + j).value;--%>
        <%--                if(itemId === inputItem){--%>
        <%--                    var countCell = document.getElementById("count" + j);--%>
        <%--                    countCell.value = Number(countCell.value) + 1--%>
        <%--                    if(countCell.value > 1) {--%>
        <%--                        document.getElementById('item' + index).value = "";--%>
        <%--                        // c = 1;--%>
        <%--                    }--%>
        <%--                }--%>
        <%--            }--%>
        <%--            for(var i = 0; i < '${items.size()}'; i++){--%>
        <%--                if(Number('${items.get(i).ean}') == ean){--%>
        <%--                    var priceCell = document.getElementById("price" + index);--%>
        <%--                    priceCell.value = ${items.get(i).middlePrice/100}--%>
        <%--                }--%>
        <%--            }--%>
        <%--            // if(c == 0) {--%>
        <%--                const tr = document.getElementById("tr" + (index + 1));--%>
        <%--                tr.hidden = false;--%>
        <%--            // }--%>
        <%--        }--%>
        <%--    } else {--%>
        <%--        const ean = Number(inputItem.split("::")[3])--%>
        <%--        for(var j = 1; j < table.rows.length; j++){--%>
        <%--            var itemId = document.getElementById("item" + j).value;--%>
        <%--            if(itemId === inputItem){--%>
        <%--                var countCell = document.getElementById("count" + j);--%>
        <%--                countCell.value = Number(countCell.value) + 1--%>
        <%--                if(countCell.value > 1) {--%>
        <%--                    document.getElementById('item' + index).value = "";--%>
        <%--                    // c = 1;--%>
        <%--                }--%>
        <%--            }--%>
        <%--        }--%>
        <%--        for(var i = 0; i < '${items.size()}'; i++){--%>
        <%--            if(Number('${items.get(i).ean}') == ean){--%>
        <%--                var priceCell = document.getElementById("price" + index);--%>
        <%--                priceCell.value = ${items.get(i).middlePrice/100}--%>
        <%--            }--%>
        <%--        }--%>
        <%--        // if(c == 0) {--%>
        <%--            const tr = document.getElementById("tr" + (index + 1));--%>
        <%--            tr.hidden = false;--%>
        <%--        // }--%>
        <%--    }--%>
        <%--}--%>

        function saveExpandMain() {
        let isSubmit = 1;
        let expandStrings = [];
        const table = document.getElementById('expandStringTable');
        for (let index = 1; index < table.rows.length; index++) {
            let it = document.getElementById("item" + index);
            if(it != null) {
                let itemId = document.getElementById("item" + index).value;
                if (itemId.length > 0) {
                    const count = document.getElementById("count" + index).value;
                    const price = document.getElementById("price" + index).value;
                    const batchNumber = document.getElementById("batchNumber" + index).value;
                    let itemString = {};
                    itemId=itemId.split("::")[2]
                    if (count.length === 0) {
                        alert("Введите количество");
                        isSubmit = 0;
                    }
                    else if (price.length === 0) {
                        alert("Введите цену");
                        isSubmit = 0;
                    }
                    else if (batchNumber.length === 0) {
                        alert("Введите номер партии");
                        isSubmit = 0;
                    }
                    itemString.itemId = itemId;
                    itemString.count = count;
                    itemString.price = price;
                    itemString.batchNumber = batchNumber;
                    expandStrings.push(itemString);
                }
                else {

                }
            } else {

            }
        }
        if (isSubmit === 1) {
            let expandMain = document.getElementById('expandMain');
            const expandJson = document.createElement('input');
            expandJson.name = "expandJson";
            expandJson.value = JSON.stringify(expandStrings);
            expandJson.hidden = true;
            expandMain.appendChild(expandJson);
            expandMain.submit();
        }
        return isSubmit === 1;
    }
        function handlePrice(sum){
            const id = 'expandStringTable';
            const table = document.getElementById(id);
            let index;
            let generalSum = 0;
            for (index = 1; index < table.rows.length; index++) {
                let c = document.getElementById("count" + index);
                if(c != null){
                    const count = document.getElementById("count" + index).value;
                    if (count.length > 0) {
                        const price = document.getElementById("price" + index).value;
                        let ppSum = Math.round(count * price * 100) / 100
                        document.getElementById('ppSum' + index).innerHTML = String(ppSum)
                        generalSum = generalSum + ppSum
                    }
                }
            }
            document.getElementById("ppMainSum").innerHTML =  String(Math.round(generalSum*100 + sum)/100);
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
            $("#datepicker").datepicker({dateFormat: "dd.mm.yyyy"});
        });
</script>
    <div class="topPanel">
        <div class="topPanelFirst">
            <div class="username">${pageContext.request.userPrincipal.name}</div>
        </div>
        <div class="topPanelCentral">
            <div><a href="create_expand_blank">Создать накладную</a></div>
        </div>
        <div class="topPanelLast">
            <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
        </div>
    </div>

    <h2 class="h2Light">Редактировать расход</h2>
    <form:form method="POST" modelAttribute="expandMain" id="expandMain"
               onsubmit="return saveExpandMain();">

    <div class="leftright">
        <div class="left">
            <div class="outerDivLogin">
                    <form:hidden path="id"/>
                    <form:hidden path="expandStrings"/>
                    <div class="innerDivLogin">
                        <form:input type="text" path="userName" readonly="true"
                                    class="inputClassLight"/>
                    </div>
                    <div class="innerDivLogin">
                        <form:input
                                autocomplete="false"
                                id="datepicker"
                                type="text"
                                path="date"
                                placeholder="Дата" class="inputClassLight"/>
                    </div>
                <div class="innerDivLogin">
                    <form:input type="text" path="store"
                                placeholder="Контрагент"
                                id="expandMainStore" class="inputClassLight"/>
                </div>
                    <div class="innerDivLogin">
                        <button type="submit" class="inputClassLight" id="submit">Сохранить</button>
                    </div>
            </div>
        </div>
            <div class="right">
            <div class="outerDivLogin">
                <div class="innerDivLogin">
                    <div class="label">Итог, руб.</div>
                    <div id="ppMainSum" class="addIncomeInput">${expandMain.sum/100}</div>
                </div>
            </div>
        </div>
    </div>
    </form:form>
    <div class="innerDivTr">
        <table class="todayIncomeStrings" id="expandStringTable">
            <tr id="expandStringTableHeader">
                <th>Товар</th>
                <th>Количество</th>
                <th>Цена продажи, руб.</th>
                <th>Номер партии</th>
                <th>Сумма продажи, руб.</th>
            </tr>
            <c:forEach items="${expandMain.expandStrings}"
                       var="expandString" varStatus="ind">
                    <tr id="tr${ind.count}"
                        onmouseover="showEditPanel(${ind.count})"
                        onmouseout="hideEditPanel(${ind.count})"
                        onclick="location.href='show_expand_string/${expandString.id}/${pageContext.request.userPrincipal.name}'">
                        <td>${expandString.item.name}</td>
                        <td>${expandString.count}</td>
                        <td>${expandString.salePrice/100}</td>
                        <td>${expandString.batchNumber}</td>
                        <td>${expandString.count * expandString.salePrice/100}</td>
                        <td class="edit" id="deleteTd${ind.count}"  >
                            <a id="delete${ind.count}" hidden
                               href="delete_expand_string/${expandString.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                        </td>
                    </tr>
            </c:forEach>
                        <c:forEach var="rowIndex" begin="1"
                                   end="100" step="1" varStatus="index">
                            <c:if test="${index.count>1}">
                                <tr id="tr${index.count}" hidden class="showIncome">
                                    <td>
                                        <input autocomplete="off" autofocus
                                               name="inputItem" list="dataList${index.count}"
                                                placeholder="Товар" id="item${index.count}"
                                                onchange="handleItem(${index.count})">
                                        <datalist id="dataList${index.count}">
                                            <c:forEach var="item" items="${items}">
                                                <option value="${item.name}::${item.count}::${item.id}::${item.ean}" ></option>
                                            </c:forEach>
                                        </datalist>
                                    </td>
                                    <td>
                                        <input type="number" required id="count${index.count}"
                                               placeholder="Количество" min = "0"
                                               onchange="handlePrice(${expandMain.sum})"/>
                                    </td>
                                    <td><input type="number" placeholder="Цена продажи"
                                               id="price${index.count}"
                                               min = "0" step="0.01"
                                               required
                                               onchange="handlePrice(${expandMain.sum})"/>
                                    </td>
                                    <td><input type="text" id="batchNumber${index.count}" required
                                               placeholder="Номер партии"/></td>
                                    <td id="priceSum${index.count}">
                                        <div id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${index.count==1}">
                                <tr id="tr${index.count}" class="showIncome">
                                    <td>
                                        <input  autocomplete="off" name="inputItem" list="dataList${index.count}"
                                                placeholder="Товар" id="item${index.count}" autofocus
                                                onchange="handleItem(${index.count})">
                                        <datalist id="dataList${index.count}">
                                            <c:forEach var="item" items="${items}">
                                                <option value="${item.name}::${item.count}::${item.id}::${item.ean}" ></option>
                                            </c:forEach>
                                        </datalist>
                                    </td>
                                    <td>
                                        <input type="number" required id="count${index.count}"
                                               placeholder="Количество" min = "0"
                                               onchange="handlePrice(${expandMain.sum})"/>
                                    </td>
                                    <td><input type="number" placeholder="Цена продажи"
                                               id="price${index.count}"
                                               min = "0" step="0.01"
                                               required
                                               onchange="handlePrice(${expandMain.sum})"/>
                                    </td>
                                    <td><input type="text" id="batchNumber${index.count}" required
                                               placeholder="Номер партии"/></td>
                                    <td id="priceSum${index.count}">
                                        <div id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
        </table>
    </div>
</body>
</html>