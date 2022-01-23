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
    <div class="topPanelCentral">
        <div><a href="add_item" target="_blank">Создать товар</a></div>
    </div>
    <div class="topPanelLast">
            <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
    </div>
</div>

<div class="innerDivTr">
    <h2  class="h2Light">Все товары</h2>
    <table class="addIncome">
        <th><a href="<c:url value="/items_main/sortBy/article/${count}"/>">Артикул внутренний</a></th>
        <th><a href="<c:url value="/items_main/sortBy/name/${count}"/>">Наименование</a></th>
        <th><a href="<c:url value="/items_main/sortBy/artYandex/${count}"/>">Артикул на Яндексе</a></th>
        <th><a href="<c:url value="/items_main/sortBy/artSber/${count}"/>">Артикул на Сбермаркете</a></th>
        <th><a href="<c:url value="/items_main/sortBy/linkYandex/${count}"/>">Ссылка на Яндексе</a></th>
        <th><a href="<c:url value="/items_main/sortBy/linkSber/${count}"/>">Ссылка на Сбермаркете</a></th>

        <th><a href="<c:url value="/items_main/sortBy/ean/${count}"/>">Штрих-код</a></th>
        <th><a href="<c:url value="/items_main/sortBy/username/${count}"/>">Создал</a></th>
        <th><a href="<c:url value="/items_main/sortBy/date/${count}"/>">Дата добавления</a></th>

        <th><a href="<c:url value="/items_main/sortBy/editUserName/${count}"/>">Редактировал</a></th>
        <th><a href="<c:url value="/items_main/sortBy/editDate/${count}"/>">Дата редактирования</a></th>

        <th><a href="<c:url value="/items_main/sortBy/count/${count}"/>">Актуальное количество</a></th>
        <c:forEach items="${items}" var="item">
            <tr onclick=openItem(${item.id})>

            <%--            <tr onclick="window.open('show_item/${item.id}/${pageContext.request.userPrincipal.name}')">--%>
                <td>${item.article}</td>
                <td>${item.name}</td>
                <td>${item.yandexArt}</td>
                <td>${item.sberArt}</td>
                <td>${item.mpLinkYandex}</td>
                <td>${item.mpLinkSber}</td>
                <td>${item.ean}</td>
                <td>${item.userName}</td>
                <td>${item.date}</td>

                <td>${item.lastEditedItem.editUserName}</td>
                <td>${item.lastEditedItem.editDate}</td>

                <td>${item.count}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>