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
        <h2 class="h2Light">Создать расход</h2>
        <div class="innerDivTr">
            <form:form method="POST" modelAttribute="expandForm">
            <table class="addIncome">
                <tr>
                    <th>Товар</th>
                    <th>Количество</th>
                    <th>Цена продажи</th>
                    <th>Номер партии</th>
                </tr>
                <tr>
                    <td>
                        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
                        <form:select path="item" onchange="javascript:handleSelect(this)">
                            <form:option value="-" label=""/>
                                <c:forEach items="${items}" var="item">
                                    <form:option value="${item.id}" label="${item.name}"/>
                                </c:forEach>
                        </form:select>
                    <td><form:input type="text" path="count" placeholder="Количество" autofocus="true"/></td>
                    <td><form:input type="text" path="salePrice"/></td>
                    <td><form:input type="text" path="batchNumber"/></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <button type="submit">Добавить</button>
                    </td>
                </tr>
            </table>
            </form:form>
        </div>

    <h2 class="h2TodayIncomes">Расходы ${date}</h2>
    <div class="innerDivTr">
        <table class="todayIncomes">
            <tr>
                <th>Добавил</th>
                <th>Дата</th>
                <th>Товар</th>
                <th>Количество</th>
                <th>Цена продажи, руб.</th>
                <th>Номер партии</th>
                <th>Сумма продажи, руб.</th>
                <th></th>
            </tr>
                <c:forEach items="${todayExpands}" var="expand">
                    <tr>
                        <td>${expand.userName}</td>
                        <td>${expand.date}</td>
                        <td>${expand.item.name}</td>
                        <td>${expand.count}</td>
                        <td>${expand.salePrice}</td>
                        <td>${expand.batchNumber}</td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>