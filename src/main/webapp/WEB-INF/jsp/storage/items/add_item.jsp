<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new item</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/index_style.css" %>
    </style>

</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<script type="text/javascript">
    function saveItem() {
        let isSubmit = 1;
        const ean = document.getElementById('ean').value;
        if(`${eans}`.indexOf(ean) == -1){
            incomeMain.submit();
        } else {
            isSubmit = 0;
            alert("Товар с таким штрих-кодом уже есть в базе")
        }
        return isSubmit === 1;
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
    <div class="outerDivLogin">
    <form:form method="POST" modelAttribute="itemForm" onsubmit="return saveItem();">
        <h2 class="h2Light">Добавить товар</h2>
        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
        <div class="innerDivLogin">
            <form:input
                    readonly="true"
                    type="text"
                    path="article"
                    placeholder="Артикул внутренний"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input
                    type="text"
                    path="name"
                    placeholder="Наименование"
                    required="true"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input
                    type="text"
                    path="yandexArt"
                    placeholder="Артикул на Яндексе"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input
                    type="text"
                    path="sberArt"
                    placeholder="Артикул на Сбермаркете"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input
                    id = "ean"
                    type="text"
                    path="ean"
                    placeholder="Штрих-код"
                    autofocus="true"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input
                    type="text"
                    path="mpLinkYandex"
                    placeholder="Ссылка на карточку товара (Яндекс)"
                    autofocus="true"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input
                    type="text"
                    path="mpLinkSber"
                    placeholder="Ссылка на карточку товара (Сбермаркет)"
                    autofocus="true"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <button type="submit" class="inputClassLight" >Добавить</button>
        </div>
    </form:form>
</div>
</body>
</html>