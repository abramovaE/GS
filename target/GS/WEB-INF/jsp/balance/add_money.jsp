<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add money</title>
    <style>
        <%@include file="/resources/style.css" %>
        <%@include file="/resources/index_style.css" %>
    </style>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/datePicker.js"></script>
</head>

<body class="bodyClassGreen">
<sec:authorize access="!isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<%--<script type="text/javascript">--%>
<%--    function saveItem() {--%>
<%--        let isSubmit = 1;--%>
<%--        const ean = document.getElementById('ean').value;--%>
<%--        if(`${eans}`.indexOf(ean) == -1){--%>
<%--            incomeMain.submit();--%>
<%--        } else {--%>
<%--            isSubmit = 0;--%>
<%--            alert("Товар с таким штрих-кодом уже есть в базе")--%>
<%--        }--%>
<%--        return isSubmit === 1;--%>
<%--    }--%>
<%--</script>--%>


<div class="topPanel">
    <div class="topPanelFirst">
        <div class="username">${pageContext.request.userPrincipal.name}</div>
    </div>



    <div class="topPanelLast">
        <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
    </div>
</div>
<div class="outerDivLogin">
    <form:form method="POST" modelAttribute="money">
        <h2 class="h2Light">Добавить поступление</h2>
        <form:hidden path="userName" value="${pageContext.request.userPrincipal.name}"/>
        <div class="innerDivLogin">
            <form:input
                    id="datepicker"
                    type="text"
                    path="date"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <form:input
                    type="number"
                    path="doubleSum"
                    placeholder="Сумма"
                    required="true"
                    min="0"
                    step="0.01"
                    class="inputClassLight"/>
        </div>
        <div class="innerDivLogin">
            <button type="submit" class="inputClassLight" >Добавить</button>
        </div>
    </form:form>
</div>
</body>
</html>