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
            <%--const editUserName = document.createAttribute('div');--%>
            <%--editUserName.hidden = true;--%>
            <%--editUserName.value = ${pageContext.request.userPrincipal.name};--%>
            <%--incomeMain.appendChild(editUserName);--%>
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
        function showEditPanel(id){
            document.getElementById('edit' + id).style.display = 'block'
            document.getElementById('delete' + id).style.display = 'block'
            document.getElementById('editth').style.display = 'block'
            document.getElementById('deleteth').style.display = 'block'
        }
        function hideEditPanel(id){
            document.getElementById('edit' + id).style.display = 'none'
            document.getElementById('delete' + id).style.display = 'none'
            document.getElementById('editth').style.display = 'none'
            document.getElementById('deleteth').style.display = 'none'
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

    <h2 class="h2Light">Редактировать приход</h2>
    <form:form method="POST" modelAttribute="incomeMain" id="incomeMain" onsubmit="return addIncomeMain();">

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
                        <form:input type="text" path="date"
                                    placeholder="Дата" class="inputClassLight"/>
                    </div>
                    <div class="innerDivLogin">
                        <form:input type="text" path="store"
                                    placeholder="Магазин покупки"
                                    id="incomeMainStore"
                                    onchange="javascript:handleStore()"
                                    class="inputClassLight"/>
                    </div>
<%--                    <div class="innerDivLogin">--%>
<%--                        <button type="button" class="inputClassLight" onclick="javascript:addIncomeString()">Добавить товар</button>--%>
<%--                    </div>--%>
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
                    <tr id="tr${index.count}"
                        onmouseover="javascript:showEditPanel(${index.count})"
                        onmouseout="javascript:hideEditPanel(${index.count})">
                        <td>${incomeString.item.name}</td>
                        <td>${incomeString.count}</td>
                        <td>${incomeString.purchasePrice/100}</td>
                        <td>${incomeString.purchasePriceAct/100}</td>
                        <td>${incomeString.storeArticle}</td>
                        <td>${incomeString.store}</td>
                        <td>${incomeString.batchNumber}</td>
                        <td>${incomeString.count * incomeString.purchasePrice/100}</td>
                        <td>${incomeString.count * incomeString.purchasePriceAct/100}</td>
                        <td class="edit" id="edit${index.count}" hidden>
                            <a href="show_income_string/${incomeString.id}/${pageContext.request.userPrincipal.name}">Редактировать</a>
                        </td>
                        <td class="edit" id="delete${index.count}" hidden>
                            <a href="delete_income_string/${incomeString.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                        </td>
                    </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>