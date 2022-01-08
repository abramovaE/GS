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
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/datePicker.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/createItem.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/hideShowEditPanel.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="Stylesheet" type="text/css" />
</head>
<body  class="bodyClassGreen">
    <sec:authorize access="!isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>

    <script type="text/javascript">

        let globalEans = [];
        let globalItems = [];

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
            let c = 0;
            if(globalEans.indexOf(inputItem) === -1){
                const answer = window.confirm("Такого товара нет в базе. Создать?");
                    if (answer) {
                        c = 1;
                        createItem(index)
                    }
                } else{
                        setMiddlePrice(globalItems, inputItem, index)
                        // c = incrementCount(table, inputItem, index)
                }
            addTr(c, index)
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

        function updateItems() {
            let dataList = document.getElementById("dataList")
            let xhr = new XMLHttpRequest();
            xhr.overrideMimeType("application/json");
            xhr.open('GET', 'update', true);
            xhr.onload = function() {
                let arr = JSON.parse(xhr.response);
                arr.forEach(function(item) {
                    if(globalEans.indexOf(item.ean) === -1){
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
            xhr.onerror = function() {
                alert('Ошибка соединения');
            };
            xhr.send();
        }


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
    <form:form method="POST" modelAttribute="incomeMain" id="incomeMainForm"
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
            <c:forEach items="${incomeMain.incomeStrings}"
                       var="incomeString" varStatus="ind">
                    <tr id="trReadonly${ind.count}"
                        onmouseover="showEditPanel(${ind.count})"
                        onmouseout="hideEditPanel(${ind.count})"
                        onclick="location.href='show_income_string/${incomeString.id}/${pageContext.request.userPrincipal.name}'">
                        <td>${incomeString.batchNumber}</td>
                        <td>${incomeString.item.ean}</td>
                        <td>${incomeString.item.name}</td>
                        <td hidden>Id</td>
                        <td>${incomeString.count}</td>
                        <td>${incomeString.purchasePrice/100}</td>
                        <td>${incomeString.purchasePriceAct/100}</td>
                        <td>${incomeString.storeArticle}</td>
                        <td>${incomeString.count * incomeString.purchasePrice/100}</td>
                        <td>${incomeString.count * incomeString.purchasePriceAct/100}</td>
                        <td class="edit" id="deleteTd${ind.count}">
                            <a id="delete${ind.count}" hidden
                               href="delete_income_string/${incomeString.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
                        </td>
                    </tr>
            </c:forEach>
                        <c:forEach var="rowIndex" begin="1"
                                   end="100" step="1" varStatus="index">

                                <tr id="tr${index.count}"
                                        <c:if test="${index.count>1}">
                                            hidden
                                        </c:if>

                                    class="showIncome">

                                    <td><input type="text" id="batchNumber${index.count}" required
                                               placeholder="Номер партии" /></td>
                                    <td>
                                        <input autocomplete="off"
                                               autofocus="true"
                                               id="item${index.count}"
                                               list="dataList"
                                               name="inputItem"
                                               onchange="handleItem(${index.count})"
                                               onclick="updateItems()"
                                               placeholder="Товар">
                                        <datalist id="dataList">
                                            <c:forEach var="item" items="${eans}">
                                                <option value="${item}" ></option>
                                            </c:forEach>
                                        </datalist>
                                    </td>
                                    <td>
                                        <input id="name${index.count}"
                                               placeholder="Наименование" min = "0"/>
                                    </td>
                                    <td hidden>
                                        <input id="itemId${index.count}"/>
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
                        </c:forEach>
        </table>
    </div>
</body>
</html>