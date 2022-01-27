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
            let date = document.getElementById("datepicker");
            date.value = '${date}'
        });
        function addExpandMain() {
        let isSubmit = true;
        let expandStrings = [];
        const table = document.getElementById('expandStringTable');
        let index;
        for (index = 1; index < table.rows.length; index++) {
            let itemId = document.getElementById("itemId" + index).value;
            const count = document.getElementById("count" + index).value;
            const price = document.getElementById("price" + index).value;
            const expandString = {};
            if (itemId.length > 0) {
                if (count.length === 0) {
                    alert("Введите количество");
                    isSubmit = false;
                    break;
                }
                else if (price.length === 0) {
                    alert("Введите цену");
                    isSubmit = false;
                    break;
                }
                expandString.itemId = itemId;
                expandString.count = count;
                expandString.price = price;
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
    </script>
    <div class="topPanel">
        <div class="topPanelFirst">
            <div class="username">${pageContext.request.userPrincipal.name}</div>
        </div>
        <div class="topPanelCentral">
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
                <th>Штрих-код</th>
                <th>Товар</th>
                <th hidden>Id</th>
                <th>Количество</th>
                <th>Цена продажи, руб.</th>
                <th>Сумма продажи, руб.</th>
            </tr>
            <c:forEach items="${items}" var="item" varStatus="index">
                    <tr id="tr${index.count}">
                        <td>
                            <input readonly
                                   id="item${index.count}"
                                   value="${item.ean}"
                                   placeholder="Товар"/>
                        </td>
                        <td>
                            <input required
                                   readonly
                                   type="text"
                                   id="name${index.count}"
                                   value="${item.name}"
                                   placeholder="Наименование"/>
                        </td>
                        <td hidden>
                            <input value="${item.id}"
                                    id="itemId${index.count}"/>
                        </td>
                        <td>
                            <input type="number"
                                   required
                                   id="count${index.count}"
                                   placeholder="Количество"
                                   value="${item.count}"
                                   min = "0"
                                   readonly
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