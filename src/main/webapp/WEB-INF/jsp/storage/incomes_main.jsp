<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
  <title>Incomes</title>
  <style>
    <%@include file="/resources/style.css" %>
    <%@include file="/resources/add_income_style.css" %>
    <%@include file="/resources/index_style.css" %>
  </style>
</head>
<body class="bodyClassGreen">
<script>
  function showIncome(id){
    alert("khjhj")
    // window.location("show_income_main/" + id)
  }


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
    <div><a href="add_income_main">Создать приход</a></div>
  </div>
  <div class="topPanelLast">
    <div><a href="/GS">На главную</a></div>
  </div>
</div>


<div class="outerDivTr">

  <div class="innerDivTr">
    <h2 class="h2Light">Приходы</h2>
    <table class="todayIncomeStrings">
      <tr>
        <th>Добавил</th>
        <th>Дата</th>
        <th>Магазин</th>
        <th>Сумма покупки, руб.</th>
        <th>Сумма покупки окончательная, руб.</th>
      </tr>
      <c:forEach items="${incomesMain}" var="incomeMain">
        <tr onmouseover="javascript:showEditPanel(${incomeMain.id})"
            onmouseout="javascript:hideEditPanel(${incomeMain.id})">

<%--                onclick="window.location.href='show_income_main/${incomeMain.id}';">--%>
          <td>${incomeMain.userName}</td>
          <td>${incomeMain.date}</td>
          <td>${incomeMain.store}</td>
          <td>${incomeMain.sum/100}</td>
          <td>${incomeMain.sumAct/100}</td>
          <td class="edit" id="edit${incomeMain.id}" hidden>
            <a href="show_income_main/${incomeMain.id}/${pageContext.request.userPrincipal.name}">Редактировать</a>
          </td>
          <td class="edit" id="delete${incomeMain.id}" hidden>
            <a href="delete_income_main/${incomeMain.id}/${pageContext.request.userPrincipal.name}">Удалить</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>

</body>
</html>
