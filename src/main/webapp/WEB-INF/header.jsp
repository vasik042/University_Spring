<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Document</title>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="navbar" style="width: 100%">
  <div class="navbar-inner" style="width: 100%">
    <div class="container" style="width: 100%">
      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <img src="/logo.png" style="height: 40px; float: left">
      <a class="brand" href="/" style="margin-left: -10px">НУЛО</a>
      <div class="nav-collapse">
        <ul class="nav">
          <li class=""><a href="/"><spring:message code="header.mainPage"/></a></li>
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="header.faculties" /><b class="caret"></b></a>
            <ul class="dropdown-menu">
                <c:forEach var="faculty" items="${faculties}">
                    <li><a href="/faculty?id=${faculty.id}">${faculty.name}</a></li>
                </c:forEach>
            </ul>
          </li>
        </ul>    
        <ul class="nav pull-right">
            <li class="divider-vertical"></li>
            <li>
                <select id="locates" style="width: 60px; margin-top: 5 px; float: right">
                    <option value="en">En</option>
                    <option value="ua">Ua</option>
                </select>
            </li>
            <sec:authorize access="!isAuthenticated()">
                <li id="reg"><a href="/register"><spring:message code="header.register" /></a></li>
                <li><a href="/login"><spring:message code="header.login" /></a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="/cabinet/"><spring:message code="header.cabinet" /></a></li>
                <li><a href="/logout"><spring:message code="header.logout" /></a></li>
            </sec:authorize>
        </ul>
      </div><!-- /.nav-collapse -->
    </div>
  </div><!-- /navbar-inner -->
</div>
<script>
    var selItem = localStorage.getItem("lang");
    $('#locates').val(selItem ? selItem : 'en');
    $("#locates").change(function () {
        var selectedlang = $('#locates').val();
        if (selectedlang) {
            localStorage.setItem("lang", selectedlang);
            window.location.replace('?lang=' + selectedlang);
        }
    });
</script>
</body>
</html>