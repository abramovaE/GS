<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
  <title>Incomes</title>
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
    <div><a href="add_income_main">Создать приход</a></div>
  </div>
  <div class="topPanelLast">
    <div><a href="${pageContext.request.contextPath}/">На главную</a></div>
  </div>
</div>

<div class="outerDivTr">
  <div class="innerDivTr">
    <h2 class="h2Light">Приходы</h2>

    <table class="todayIncomeStrings">
      <tr>
        <th>Номер</th>
        <th>Дата</th>
        <th>Добавил</th>
        <th>Магазин</th>
        <th>Сумма покупки, руб.</th>
        <th>Сумма покупки окончательная, руб.</th>
        <th>Примечание</th>
      </tr>
      <c:forEach items="${incomesMain}" var="incomeMain">
        <tr onmouseover="showEditPanel(${incomeMain.id})"
            onmouseout="hideEditPanel(${incomeMain.id})"
            onclick="location.href='show_income_main/${incomeMain.id}/${pageContext.request.userPrincipal.name}'">
          <td>${incomeMain.id}</td>
          <td>${incomeMain.date}</td>
          <td>${incomeMain.userName}</td>
          <td>${incomeMain.store}</td>
          <td>${incomeMain.sum/100}</td>
          <td>${incomeMain.sumAct/100}</td>
          <td>${incomeMain.note}</td>



            <td<sec:authorize access="hasAuthority('ADMIN')">
                    id="deleteTd${incomeMain.id}"
                    </sec:authorize>>
              <sec:authorize access="hasAuthority('ADMIN')">
<%--              <a id="addNote${incomeMain.id}" hidden--%>
<%--                 href="add_note_income_main/${incomeMain.id}">Примечание</a>--%>
              <a id="delete${incomeMain.id}" hidden
                 href="delete_income_main/${incomeMain.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
              </sec:authorize>
            </td>


        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>


<%--<sec:authorize access="hasAuthority('ADMIN')">--%>
<%--  <h2 class="h2Light">Приходы</h2>--%>
<%--</sec:authorize>--%>
<%--          <sec:authorize access="hasRole('ADMIN')">--%>
<%--            <td id="deleteTd${incomeMain.id}">--%>
<%--              <a id="delete${incomeMain.id}" hidden--%>
<%--                 href="delete_income_main/${incomeMain.id}/${pageContext.request.userPrincipal.name}">--%>
<%--                Удалить--%>
<%--              </a>--%>
<%--            </td>--%>
<%--          </sec:authorize>--%>