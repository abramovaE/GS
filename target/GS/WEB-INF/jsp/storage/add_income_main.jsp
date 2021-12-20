<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Add new incomeMain</title>
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
                let dataList = new Array()
                <c:forEach items="${items}" var="item">
                dataList.push(${item.article})
                </c:forEach>
                alert(dataList.values())
                if(dataList.indexOf(inputItem) === -1){
                    const answer = window.confirm("Такого товара нет в базе. Создать?");
                    if (answer) {
                        window.location = "add_item";
                    }
                }
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

<%--    class Search {--%>
<%--        constructor(rowIndex) {--%>
<%--            this.rowIndex = rowIndex;--%>
<%--        }--%>
<%--        handleEvent(event) {--%>
<%--            let method = 'on' + event.type[0].toUpperCase() + event.type.slice(1);--%>
<%--            this[method](event);--%>
<%--        }--%>
<%--        onKeyup(){--%>
<%--            let arr = [];--%>
<%--            response.--%>
<%--            let items = document.getElementById("items");--%>
<%--            &lt;%&ndash;items.push(${items})&ndash;%&gt;--%>
<%--            &lt;%&ndash;items.forEach(function(item, i,  items) {&ndash;%&gt;--%>
<%--            &lt;%&ndash;    arr.push(item.name)&ndash;%&gt;--%>
<%--            &lt;%&ndash;});&ndash;%&gt;--%>
<%--&lt;%&ndash;            <c:forEach var="item" items="${items}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                arr.push(${item.name})&ndash;%&gt;--%>
<%--&lt;%&ndash;            </c:forEach>&ndash;%&gt;--%>
<%--            alert(items.values())--%>
<%--            // let result = document.getElementById('result');--%>
<%--            // let search = document.getElementById('item' + this.rowIndex);--%>
<%--            // let l = search.value.length;--%>
<%--            // if(l>0){--%>
<%--            //     for(var i=0;i<arr.length;i++){--%>
<%--            //         var _ = arr[i].split('').slice(0,l).join('');--%>
<%--            //         if(_==search.value){--%>
<%--            //             result.innerHTML+=arr[i]+'<br/>';--%>
<%--            //         }--%>
<%--            //     }--%>
<%--            // }--%>
<%--        }--%>
<%--    }--%>



    function addIncomeString() {
        const id = 'incomeStringTable';
        const table = document.getElementById(id);
        let rowIndex = table.rows.length;
        const tableHeader = document.getElementById('incomeStringTableHeader');
        tableHeader.hidden = false;


        const tr = document.getElementById('tr' + rowIndex);
        tr.closest('tr' + rowIndex)

        //
        // alert(tr)
        // tr.hidden=false




        <%--const row = document.createElement('tr');--%>
        <%--const item = document.createElement('td');--%>
        <%--const inputItem = document.createElement('input');--%>

        <%--const dataList = document.createElement('datalist');--%>
        <%--dataList.id="dataList"+rowIndex;--%>
        <%--<c:forEach var="item" items="${items}">--%>
        <%--    var option = document.createElement('option');--%>
        <%--    option.value="${item.id}" + "-" + "${item.name}" + "-" + "${item.count}";--%>
        <%--    dataList.appendChild(option);--%>
        <%--</c:forEach>--%>
        <%--inputItem.autocomplete="off";--%>
        <%--inputItem.name="inputItem";--%>
        <%--inputItem.placeholder="Товар";--%>
        <%--inputItem.list=dataList;--%>
        <%--inputItem.id="item"+rowIndex;--%>
        <%--inputItem.autofocus=true;--%>
        <%--// inputItem.addEventListener("input", handleItem);--%>

        <%--item.appendChild(inputItem);--%>


        <%--const count = document.createElement('td');--%>
        <%--let countInput = document.createElement('input');--%>
        <%--countInput.id="count" + rowIndex;--%>
        <%--countInput.type="number";--%>
        <%--countInput.required=true;--%>
        <%--countInput.placeholder="Количество";--%>
        <%--countInput.min="0";--%>
        <%--count.addEventListener("input", handlePrice);--%>
        <%--// count.addEventListener("focus", clearCount);--%>
        <%--count.appendChild(countInput);--%>


        <%--const purPrice = document.createElement('td');--%>
        <%--const purPriceInput = document.createElement('input');--%>
        <%--purPriceInput.id="purPrice" + rowIndex;--%>
        <%--purPriceInput.type="number";--%>
        <%--purPriceInput.placeholder="Цена покупки";--%>
        <%--purPriceInput.min="0";--%>
        <%--purPriceInput.step="0.01";--%>
        <%--purPriceInput.addEventListener("input", handlePrice);--%>
        <%--purPriceInput.required=true;--%>
        <%--// purPriceInput.addEventListener("focus", clearPpDouble);--%>
        <%--purPrice.appendChild(purPriceInput);--%>

        <%--const purPriceAct = document.createElement('td');--%>
        <%--const purPriceActInput = document.createElement('input');--%>
        <%--purPriceActInput.id="purPriceAct" + rowIndex;--%>
        <%--purPriceActInput.type="number";--%>
        <%--purPriceActInput.placeholder="Цена покупки окончательная";--%>
        <%--purPriceActInput.min="0";--%>
        <%--purPriceActInput.step="0.01";--%>
        <%--purPriceActInput.addEventListener("input", handlePrice);--%>
        <%--purPriceActInput.required=true--%>
        <%--// purPriceActInput.addEventListener("focus", clearPpActDouble);--%>
        <%--purPriceAct.appendChild(purPriceActInput);--%>

        <%--const storeArticle = document.createElement('td');--%>
        <%--const storeArticleInput = document.createElement('input');--%>
        <%--storeArticleInput.id="storeArticle" + rowIndex;--%>
        <%--storeArticleInput.type="text";--%>
        <%--storeArticleInput.placeholder="Артикул в магазине";--%>
        <%--storeArticleInput.required=true--%>
        <%--storeArticle.appendChild(storeArticleInput);--%>

        <%--const store = document.createElement('td');--%>
        <%--const storeInput = document.createElement('input');--%>
        <%--storeInput.id="store" + rowIndex;--%>
        <%--storeInput.type="text";--%>
        <%--storeInput.placeholder="Магазин покупки";--%>
        <%--storeInput.value = document.getElementById("incomeMainStore").value--%>
        <%--storeInput.required=true--%>
        <%--store.appendChild(storeInput);--%>

        <%--const batchNumber = document.createElement('td');--%>
        <%--const batchNumberInput = document.createElement('input');--%>
        <%--batchNumberInput.id= "batchNumber"+rowIndex;--%>
        <%--batchNumberInput.type="text";--%>
        <%--batchNumberInput.placeholder="Номер партии";--%>
        <%--batchNumberInput.required=true;--%>
        <%--batchNumber.appendChild(batchNumberInput);--%>

        <%--const purPriceSum = document.createElement('td');--%>
        <%--const purPriceSumDiv = document.createElement('div');--%>
        <%--purPriceSumDiv.id="ppSum"+rowIndex;--%>
        <%--purPriceSumDiv.className="addIncomeInput";--%>
        <%--purPriceSumDiv.appendChild(document.createTextNode('0.00'));--%>
        <%--purPriceSum.appendChild(purPriceSumDiv);--%>
        <%--purPriceSum.id="purPriceSum" + rowIndex;--%>

        <%--const purPriceActSum = document.createElement('td');--%>
        <%--const purPriceActSumDiv = document.createElement('div');--%>
        <%--purPriceActSumDiv.id="ppActSum"+rowIndex;--%>
        <%--purPriceActSumDiv.className="addIncomeInput";--%>
        <%--purPriceActSumDiv.appendChild(document.createTextNode('0.00'));--%>
        <%--purPriceActSum.appendChild (purPriceActSumDiv);--%>
        <%--purPriceActSum.id="purPriceActSum" + rowIndex;--%>

        <%--row.appendChild(item);--%>
        <%--row.appendChild(count);--%>
        <%--row.appendChild(purPrice);--%>
        <%--row.appendChild(purPriceAct);--%>
        <%--row.appendChild(storeArticle);--%>
        <%--row.appendChild(store);--%>
        <%--row.appendChild(batchNumber);--%>
        <%--row.appendChild(purPriceSum);--%>
        <%--row.appendChild(purPriceActSum);--%>
        <%--table.appendChild(row);--%>
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

    <h2 class="h2Light">Создать приход</h2>
    <div class="leftright">
        <div class="left">
            <div class="outerDivLogin">
                <form:form method="POST" modelAttribute="incomeMainForm" id="incomeMainForm" onsubmit="return addIncomeMain();">
                    <form:hidden path="id"/>
                    <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
                    <form:hidden path="incomeStrings"/>
                    <div class="innerDivLogin">
                        <form:input type="text" path="date" placeholder="Дата" class="inputClassLight"/>
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

            <c:forEach var="rowIndex" begin="1" end="100" step="1" varStatus="index">
            <tr id="tr${index.count}">
                <td>
                    <input  autocomplete="off" name="inputItem" list="dataList${index.count}"
                            placeholder="Товар" id="item${index.count}" autofocus="true"
                            onchange="javascript:handleItem(${index.count})">
                    <datalist id="dataList${index.count}">
<%--                        <form:option value="0" label="Добавить товар"/>--%>
                        <c:forEach var="item" items="${items}">
                            <option value="${item.name}::${item.count}::${item.id}::${item.article}" ></option>
                        </c:forEach>
                    </datalist>
                </td>
                <td>
                    <input type="number" required="true" id="count${index.count}"
                           placeholder="Количество" min = "0"
                           onchange="javascript:handlePrice()" onfocus="javascript:clearCount()"/>
                </td>
                <td><input type="number" placeholder="Цена покупки"
                           id="purPrice${index.count}"
                           min = "0" step="0.01"
                           required="true"
                           onchange="javascript:handlePrice()" onfocus="javascript:clearPpDouble()"/>
                </td>
                <td><input type="number" id="purPriceAct${index.count}" required="true"
                           placeholder="Цена покупки окончательная" min = "0" step="0.01"
                                oninput="javascript:handlePrice()"
                           onchange="javascript:clearPpActDouble()"/>
                </td>
                <td><input type="text" id="storeArticle${index.count}"
                           placeholder="Артикул в магазине" required="true"/></td>
                <td><input type="text" id="store${index.count}" required="true"
                           placeholder="Магазин покупки"/></td>
                <td><input type="text" id="batchNumber${index.count}" required="true"
                           placeholder="Номер партии"  path="batchNumber"/></td>
                <td id="purPriceSum${index.count}">
                    <div type="text" id="ppSum${index.count}" class="addIncomeInput">0.00</div>
                </td>
                <td id="purPriceActSum"+${index.count}>
                    <div type="text" id="ppActSum${index.count}" class="addIncomeInput">0.00</div>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>