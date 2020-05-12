<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
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
          <li class=""><a href="/">Головна сторінка</a></li>
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Факультети<b class="caret"></b></a>
            <ul class="dropdown-menu">
                <c:forEach var="faculty" items="${faculties}">
                    <li><a href="/faculty?id=${faculty.id}">${faculty.name}</a></li>
                </c:forEach>
            </ul>
          </li>                    
        
        </ul>    
            <ul class="nav pull-right">
          <li class="divider-vertical"></li>
            <c:if test="${UserId == null}">
                <li class=""><a href="/register">Реєстрація</a></li>
                <li class=""><a href="/login">Вхід</a></li>
            </c:if>
            <c:if test="${UserId != null}">
                <li class=""><a href="/cabinet">Кабінет</a></li>
                <li class=""><a href="/logout">Вихід</a></li>
            </c:if>
        </ul>
      </div><!-- /.nav-collapse -->
    </div>
  </div><!-- /navbar-inner -->
</div>
</body>
</html>