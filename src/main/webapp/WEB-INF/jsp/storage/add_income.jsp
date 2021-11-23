<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new income</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/add_income_style.css" %>
    </style>

</head>

<body class="bodyClassGreen">
<script type="text/javascript">
    function handleSelect(elm)
    {
        if(elm.value == 0) {
            window.location = "/add_item";
        }
    }
</script>
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="outerDivTr">
        <h2 class="h2Light">Создать приход</h2>
        <div class="innerDivTr">

            <form:form method="POST" modelAttribute="incomeForm">
            <table class="addIncome">
                <tr>
                    <th>Товар</th>
                    <th>Количество</th>
                    <th>Цена покупки</th>
                    <th>Цена покупки окончательная</th>
                    <th>Артикул в магазине</th>
                    <th>Магазин покупки</th>
                    <th>Номер партии</th>
                </tr>
                <tr>

                    <td>
                        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
                        <form:select path="item" onchange="javascript:handleSelect(this)">
                            <form:option value="-" label=""/>
                            <form:option value="0" label="Добавить товар"/>
                                <c:forEach items="${items}" var="item">
                                    <form:option value="${item.id}" label="${item.name}"/>
                                </c:forEach>
                        </form:select>
                    <td><form:input type="text" path="count" placeholder="Количество" autofocus="true"/></td>
                    <td><form:input type="text" path="purchasePrice"/></td>
                    <td><form:input type="text" path="purchasePriceAct"/></td>
                    <td><form:input type="text" path="storeArticle" placeholder="Артикул в магазине"/></td>
                    <td><form:input type="text" path="store" placeholder="Магазин покупки"/></td>
                    <td><form:input type="text" path="batchNumber"/></td>
                </tr>
                <tr>
                    <td colspan="7">
                        <button type="submit">Добавить</button>
                    </td>
                </tr>
            </table>
            </form:form>




        </div>

    <div class="innerDivTr">

        <table class="todayIncomes">
            <tr>
                <th>Добавил</th>
                <th>Дата</th>
                <th>Товар</th>
                <th>Количество</th>
                <th>Цена покупки</th>
                <th>Цена покупки окончательная</th>
                <th>Артикул в магазине</th>
                <th>Магазин покупки</th>
                <th>Номер партии</th>
            </tr>


                <c:forEach items="${todayIncomes}" var="income">
                    <tr>
                    <td>${income.userName}</td>
                    <td>${income.date}</td>
                    <td>${income.item.name}</td>
                    <td>${income.count}</td>
                    <td>${income.purchasePrice}</td>
                    <td>${income.purchasePriceAct}</td>
                    <td>${income.storeArticle}</td>
                    <td>${income.store}</td>
                    <td>${income.batchNumber}</td>
                    </tr>
                </c:forEach>

        </table>


    </div>
</div>
</body>
</html>