<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
  <title>Expands</title>
  <style>
    <%@include file="/resources/style.css" %>
    <%@include file="/resources/add_income_style.css" %>
    <%@include file="/resources/index_style.css" %>
  </style>
</head>
<body class="bodyClassGreen">
<script>
  function showEditPanel(id){
    document.getElementById('edit' + id).style.display = 'block'
    document.getElementById('delete' + id).style.display = 'block'
    document.getElementById('editth').style.display = 'block'
    document.getElementById('deleteth').style.display = 'block'
  }
  function hideEditPanel(id){
    document.getElementById('edit' + id).style.display = 'none'
    document.getElementById('delete' + id).style.display = 'none'
    document.getElementById('editth').style.display = 'none'
    document.getElementById('deleteth').style.display = 'none'
  }
</script>
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
    <div><a href="/GS">На главную</a></div>
  </div>
</div>


<div class="outerDivTr">
  <div class="innerDivTr">
    <h2 class="h2Light">Расходы</h2>
    <table class="todayIncomeStrings">
      <tr>
        <th>Создал</th>
        <th>Дата</th>
        <th>Магазин</th>
        <th>Сумма продажи, руб.</th>
      </tr>
      <c:forEach items="${expandsMain}" var="expandMain">
        <tr onmouseover="javascript:showEditPanel(${expandMain.id})"
            onmouseout="javascript:hideEditPanel(${expandMain.id})">
          <td>${expandMain.userName}</td>
          <td>${expandMain.date}</td>
          <td>${expandMain.store}</td>
          <td>${expandMain.sum/100}</td>
          <td class="edit" id="edit${expandMain.id}" hidden>
            <a href="show_expand_main/${expandMain.id}/${pageContext.request.userPrincipal.name}">Редактировать</a>
          </td>
          <td class="edit" id="delete${expandMain.id}" hidden>
            <a href="delete_expandMain_main/${expandMain.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>
