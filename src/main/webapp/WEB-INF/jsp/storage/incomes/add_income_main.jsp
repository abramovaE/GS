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
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/createItem.js"></script>
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
                itemObj = {};
                itemObj.id = ${item.id};
                itemObj.name = `${item.name}`;
                itemObj.ean = `${item.ean}`;
                if(globalItems.indexOf(itemObj) === -1) {
                    globalItems.push(itemObj);
                }
            </c:forEach>
        });
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
        <div class="topPanelLast">
            <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
        </div>
    </div>

    <h2 class="h2Light">Создать приход</h2>
    <div class="leftright">
        <div class="left">
            <div class="outerDivLogin">
                <form:form method="POST" modelAttribute="incomeMainForm" id="incomeMainForm"
                           onsubmit="return saveIncomeMain();">
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
                                   required
                                   placeholder="Номер партии"/></td>
                        <td>
                            <input  autocomplete="off"
                                    name="inputItem"
                                    list="dataList"
                                    placeholder="Товар"
                                    id="item${index.count}"
                                    autofocus
                                    onchange="handleItem(globalEans, globalItems, ${index.count})"
                                    onclick="updateItems()">
                            <datalist id="dataList">
                                <c:forEach var="item" items="${eans}">
                                    <option value="${item}" ></option>
                                </c:forEach>
                            </datalist>
                        </td>

                        <td>
                            <input required
                                   id="name${index.count}"
                                   placeholder="Наименование"/>
                        </td>

                        <td hidden>
                            <input required
                                   id="itemId${index.count}"/>
                        </td>
                        <td>
                            <input type="number" required
                                   id="count${index.count}"
                                   placeholder="Количество" min = "0"
                                   onchange="handlePrice(0, 0)" onfocus="clearCount()"/>
                        </td>
                        <td><input type="number" placeholder="Цена покупки"
                                   id="purPrice${index.count}"
                                   min = "0" step="0.01"
                                   required
                                   onchange="handlePrice(0, 0)"/>
                        </td>
                        <td><input type="number" id="purPriceAct${index.count}" required
                                   placeholder="Цена покупки окончательная" min = "0" step="0.01"
                                   oninput="handlePrice(0, 0)"/>
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