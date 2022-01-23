<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
  <title>Expands</title>
  <style>
    <%@include file="/resources/style.css" %>
    <%@include file="/resources/add_income_style.css" %>
    <%@include file="/resources/index_style.css" %>
  </style>

  <script type="text/javascript"
          src="${pageContext.request.contextPath}/resources/hideShowEditPanel.js"></script>
</head>
<body class="bodyClassGreen">

<sec:authorize access="!isAuthenticated()">
  <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="topPanel">
  <div class="topPanelFirst">
    <div class="username">${pageContext.request.userPrincipal.name}</div>
  </div>
  <div class="topPanelCentral">
    <div><a href="add_expand_main">Создать расход</a></div>
  </div>
  <div class="topPanelLast">
    <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
  </div>
</div>

<div class="outerDivTr">
  <div class="innerDivTr">
    <h2 class="h2Light">Расходы</h2>
    <table class="todayIncomeStrings">
      <tr>
        <th>Номер</th>
        <th>Дата</th>
        <th>Создал</th>
        <th>Контрагент</th>
        <th>Сумма продажи, руб.</th>
      </tr>
      <c:forEach items="${expandsMain}" var="expandMain">
        <tr onmouseover="showEditPanel(${expandMain.id})"
            onmouseout="hideEditPanel(${expandMain.id})"
            onclick="location.href='show_expand_main/${expandMain.id}/${pageContext.request.userPrincipal.name}'">
          <td>${expandMain.id}</td>
          <td>${expandMain.date}</td>
          <td>${expandMain.userName}</td>
          <td>${expandMain.store}</td>
          <td>${expandMain.sum/100}</td>
          <td id="deleteTd${expandMain.id}">
            <a id="delete${expandMain.id}" hidden
               href="delete_expand_main/${expandMain.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>
