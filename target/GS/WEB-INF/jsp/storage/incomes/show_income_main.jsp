<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Show incomeMain</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
        <%@include file="/resources/index_style.css" %>
        <%@include file="/resources/add_income_main_style.css" %>
        <%@include file="/resources/show_income_main_style.css" %>
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="Stylesheet" type="text/css" />
</head>
<body  class="bodyClassGreen">
    <sec:authorize access="!isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>

    <script type="text/javascript">

        <%--function handleItem(index){--%>
        <%--    if(index === 1){--%>
        <%--        index = index + ${incomeMain.incomeStrings.size()}--%>
        <%--    }--%>
        <%--    const inputItem = document.getElementById('item'+index).value;--%>
        <%--    const table = document.getElementById('incomeStringTable');--%>
        <%--    var c = 0;--%>

        <%--    if(inputItem.indexOf("::") === -1){--%>
        <%--        if('${eans}'.indexOf(inputItem) === -1){--%>
        <%--            const answer = window.confirm("Такого товара нет в базе. Создать?");--%>
        <%--            if (answer) {--%>
        <%--                window.location = "add_item";--%>
        <%--            }--%>
        <%--        } else {--%>
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
        <%--        }--%>
        <%--    }--%>
        <%--    if(c == 0) {--%>
        <%--        const tr = document.getElementById("tr" + (index + 1));--%>
        <%--        tr.hidden = false;--%>
        <%--    }--%>
        <%--}--%>

        function handleItem(index){
            if(index === 1){
                index = index + ${incomeMain.incomeStrings.size()}
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

        function saveIncomeMain() {
        let isSubmit = 1;
        let incomeStrings = [];
        const table = document.getElementById('incomeStringTable');
        for (let index = 1; index < table.rows.length; index++) {
            let it = document.getElementById("item" + index);
            if(it != null) {
                let itemId = it.value;
                if ((itemId !== 0) && (itemId.indexOf("::") !== -1)) {
                    const count = document.getElementById("count" + index).value;
                    const purPrice = document.getElementById("purPrice" + index).value;
                    const purPriceAct = document.getElementById("purPriceAct" + index).value;
                    const storeArticle = document.getElementById("storeArticle" + index).value;
                    const batchNumber = document.getElementById("batchNumber" + index).value;
                    itemId=itemId.split("::")[2]
                    if (count.length === 0) {
                        alert("Введите количество");
                        isSubmit = 0;
                    }
                    else if (purPrice.length === 0) {
                        alert("Введите цену");
                        isSubmit = 0;
                    }
                    else if (purPriceAct.length === 0) {
                        alert("Введите фактическую цену");
                        isSubmit = 0;
                    }
                    else if (storeArticle.length === 0) {
                        alert("Введите артикул товара в магазине покупки");
                        isSubmit = 0;
                    }
                    else if (batchNumber.length === 0) {
                        alert("Введите номер партии");
                        isSubmit = 0;
                    }
                    let itemString = {};
                    itemString.itemId = itemId;
                    itemString.count = count;
                    itemString.purPrice = purPrice;
                    itemString.purPriceAct = purPriceAct;
                    itemString.storeArticle = storeArticle;
                    itemString.batchNumber = batchNumber;
                    incomeStrings.push(itemString);
                }
            }
        }
        if (isSubmit === 1) {
            let incomeMain = document.getElementById('incomeMain');
            const incomeJson = document.createElement('input');
            incomeJson.name = "incomeJson";
            incomeJson.value = JSON.stringify(incomeStrings);
            incomeJson.hidden = true;
            incomeMain.appendChild(incomeJson);
            incomeMain.submit();
        }

        return isSubmit === 1;
    }
        function handlePrice(s1, s2){
        const id = 'incomeStringTable';
        const table = document.getElementById(id);
        let index;
        let generalSum = 0;
        let generalSumAct = 0;
        for (index = 1; index < table.rows.length; index++) {
            const c = document.getElementById("count" + index);
            if(c != null){
                const count = document.getElementById("count" + index).value;
                if (count.length > 0) {
                    const purPrice = document.getElementById("purPrice" + index).value;
                    const purPriceAct = document.getElementById("purPriceAct" + index).value;
                    let ppSum = Math.round(count * purPrice * 100) / 100
                    let ppActSum = Math.round(count * purPriceAct * 100) / 100
                    document.getElementById('ppSum' + index).innerHTML = String(ppSum)
                    document.getElementById('ppActSum' + index).innerHTML = String(ppActSum)
                    generalSum = generalSum + ppSum
                    generalSumAct = generalSumAct + ppActSum
                }
            }
        }

        document.getElementById("ppMainSum").innerHTML = String(Math.round(s1 + generalSum*100)/100)
        document.getElementById("ppMainSumAct").innerHTML = String(Math.round(s2 + generalSumAct*100)/100)


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
        $(function () {
            $("#datepicker").datepicker({dateFormat: 'dd.mm.yy'});
        });
</script>
    <div class="topPanel">
        <div class="topPanelFirst">
            <div class="username">${pageContext.request.userPrincipal.name}</div>
        </div>
        <div class="topPanelCentral">
            <div><a href="create_income_blank">Создать накладную</a></div>
        </div>
        <div class="topPanelLast">
            <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
        </div>
    </div>

    <h2 class="h2Light">Редактировать приход</h2>
    <form:form method="POST" modelAttribute="incomeMain" id="incomeMain"
               onsubmit="return saveIncomeMain();">

    <div class="leftright">
        <div class="left">
            <div class="outerDivLogin">
                    <form:hidden path="id"/>
                    <form:hidden path="incomeStrings"/>
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
                                placeholder="Дата"
                                class="inputClassLight"/>
                    </div>
                    <div class="innerDivLogin">
                        <form:input type="text" path="store"
                                    placeholder="Магазин покупки"
                                    id="incomeMainStore" class="inputClassLight"/>
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
                    <div id="ppMainSum" class="addIncomeInput">${incomeMain.sum/100}</div>
                </div>
                <div class="innerDivLogin">
                    <div class="label">Итог факт., руб.</div>
                    <div id="ppMainSumAct" class="addIncomeInput">${incomeMain.sumAct/100}</div>
                </div>
            </div>
        </div>
    </div>
    </form:form>
    <div class="innerDivTr">
        <table class="todayIncomeStrings" id="incomeStringTable">
            <tr id="incomeStringTableHeader">
                <th>Номер партии</th>

                <th>Товар</th>
                <th>Количество</th>
                <th>Цена покупки, руб.</th>
                <th>Цена покупки окончательная, руб.</th>
                <th>Артикул в магазине</th>
                <th>Сумма покупки, руб.</th>
                <th>Сумма покупки окончательная, руб.</th>
            </tr>
            <c:forEach items="${incomeMain.incomeStrings}"
                       var="incomeString" varStatus="ind">
                    <tr id="tr${ind.count}"
                        onmouseover="showEditPanel(${ind.count})"
                        onmouseout="hideEditPanel(${ind.count})"
                        onclick="location.href='show_income_string/${incomeString.id}/${pageContext.request.userPrincipal.name}'">
                        <td>${incomeString.batchNumber}</td>

                        <td>${incomeString.item.name}</td>
                        <td>${incomeString.count}</td>
                        <td>${incomeString.purchasePrice/100}</td>
                        <td>${incomeString.purchasePriceAct/100}</td>
                        <td>${incomeString.storeArticle}</td>
                        <td>${incomeString.count * incomeString.purchasePrice/100}</td>
                        <td>${incomeString.count * incomeString.purchasePriceAct/100}</td>
<%--                        <td class="edit" id="edit${ind.count}" hidden>--%>
<%--                            <a href="show_income_string/${incomeString.id}/${pageContext.request.userPrincipal.name}">Редактировать</a>--%>
<%--                        </td>--%>
                        <td class="edit" id="delete${ind.count}" hidden>
                            <a href="delete_income_string/${incomeString.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                        </td>
                    </tr>
            </c:forEach>
                        <c:forEach var="rowIndex" begin="1"
                                   end="100" step="1" varStatus="index">
                            <c:if test="${index.count>1}">
                                <tr id="tr${index.count}" hidden class="showIncome">

                                    <td><input type="text" id="batchNumber${index.count}" required
                                               placeholder="Номер партии" /></td>

                                    <td>
                                        <input autocomplete="off" name="inputItem" list="dataList${index.count}"
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
                                               onchange="handlePrice(${incomeMain.sum},${incomeMain.sumAct})"/>
                                    </td>
                                    <td><input type="number" placeholder="Цена покупки"
                                               id="purPrice${index.count}"
                                               min = "0" step="0.01"
                                               required
                                               onchange="handlePrice(${incomeMain.sum},${incomeMain.sumAct})"/>
                                    </td>
                                    <td><input type="number" id="purPriceAct${index.count}" required
                                               placeholder="Цена покупки окончательная" min = "0" step="0.01"
                                               oninput="handlePrice(${incomeMain.sum},${incomeMain.sumAct})"/>
                                    </td>
                                    <td><input type="text" id="storeArticle${index.count}"
                                               placeholder="Артикул в магазине" required/></td>

                                    <td id="purPriceSum${index.count}">
                                        <div id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                                    </td>
                                    <td id="purPriceActSum${index.count}">
                                        <div id="ppActSum${index.count}" class="addIncomeInput">0.00</div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${index.count==1}">
                                <tr id="tr${index.count}" class="showIncome">

                                    <td><input type="text" id="batchNumber${index.count}" required
                                               placeholder="Номер партии"/></td>
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
                                               onchange="handlePrice(${incomeMain.sum},${incomeMain.sumAct})"/>
                                    </td>
                                    <td><input type="number" placeholder="Цена покупки"
                                               id="purPrice${index.count}"
                                               min = "0" step="0.01"
                                               required
                                               onchange="handlePrice(${incomeMain.sum},${incomeMain.sumAct})"/>
                                    </td>
                                    <td><input type="number" id="purPriceAct${index.count}" required
                                               placeholder="Цена покупки окончательная" min = "0" step="0.01"
                                               oninput="handlePrice(${incomeMain.sum},${incomeMain.sumAct})"/>
                                    </td>
                                    <td><input type="text" id="storeArticle${index.count}"
                                               placeholder="Артикул в магазине" required/></td>

                                    <td id="purPriceSum${index.count}">
                                        <div id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                                    </td>
                                    <td id="purPriceActSum${index.count}">
                                        <div id="ppActSum${index.count}" class="addIncomeInput">0.00</div>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
        </table>
    </div>
</body>
</html>