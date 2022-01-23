<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Show all items</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/all_items.css" %>
        <%@include file="/resources/index_style.css" %>
    </style>
    <script type="text/javascript">
        let isUpdate = false;

        window.onfocus = function mouseMove(){
            if(!isUpdate) {
                isUpdate = true
                window.location.reload();
            }
        }
        function openItem(itemId){
            isUpdate = false;
            window.open('show_item/' + itemId + '/${pageContext.request.userPrincipal.name}')
        }

    </script>
</head>

<body  class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="topPanel">
    <div class="topPanelFirst">
        <div class="username">${pageContext.request.userPrincipal.name}</div>
    </div>
    <div class="topPanelLast">
            <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
    </div>
</div>


<div class="innerDivTr">
    <h2  class="h2Light">Остатки</h2>
    <table class="addIncome">
        <th><a href="<c:url value="/allItems/sortBy/article/${count}"/>">Артикул внутренний</a></th>
        <th><a href="<c:url value="/allItems/sortBy/name/${count}"/>">Наименование</a></th>
<%--        <th><a href="<c:url value="/allItems/sortBy/artYandex/${count}"/>">Артикул на Яндексе</a></th>--%>
<%--        <th><a href="<c:url value="/allItems/sortBy/artSber/${count}"/>">Артикул на Сбермаркете</a></th>--%>
        <th><a href="<c:url value="/allItems/sortBy/ean/${count}"/>">Штрих-код</a></th>
        <th><a href="<c:url value="/allItems/sortBy/username/${count}"/>">Создал</a></th>



        <th><a href="<c:url value="/allItems/sortBy/date/${count}"/>">Дата добавления</a></th>
        <th><a href="<c:url value="/allItems/sortBy/count/${count}"/>">Актуальное количество</a></th>
        <th><a href="<c:url value="/allItems/sortBy/middlePrice/${count}"/>">Средняя стоимость</a></th>
        <th><a href="<c:url value="/allItems/sortBy/sum/${count}"/>">Сумма</a></th>

        <c:forEach items="${items}" var="item">

            <tr onclick="openItem(${item.id})">
            <td>${item.article}</td>
                <td>${item.name}</td>
<%--                <td>${item.yandexArt}</td>--%>
<%--                <td>${item.sberArt}</td>--%>
                <td>${item.ean}</td>
                <td>${item.userName}</td>



                <td>${item.date}</td>
                <td>${item.count}</td>
                <td>${item.middlePrice/100}</td>
                <td>${item.middlePrice * item.count / 100}</td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>