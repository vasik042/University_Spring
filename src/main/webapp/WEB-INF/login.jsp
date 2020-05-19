<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    
    <style>
        label{
            float: left;
            clear: both;
            width: 80%;
            margin-left: 10%;
            font-family: inherit;
            font-size: 18px;
            text-align: center;
        }input{
            float: right;
        }.inp1{
            font-size: 22px;
            float: left;
            width: 100%;
            height: 40px;
            border-radius: 5px;
            
        }#btn{
            margin-left: 25%;
            float: left;
            width: 50%;
            height: 30px;
            border-radius: 5px;
            background-color: gainsboro;
            color: grey;
        }
    </style>
</head>
<body>
    <form action="/login" method ="POST">
        <div style="background-color: white; width: 500px; height: 260px; margin: auto; margin-top: 15px; border-radius: 10px; border: 2px solid grey">
            
              <div style="height: 20px"></div>
              
              <p style="margin-left: 220px; font-size: 28px; margin-top: 0"><spring:message code="login.login" /></p>
               
            <label for="email" style="margin-top: -10px">
                <input class="inp1" type="email" placeholder="Email" required id="email" name ="email">
            </label>
            <div style="height: 10px; width: 100%; float: left"></div>
            <label for="password">
                <input class="inp1" type="text" placeholder="Password" required id="password" name ="password">
            </label>
            <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            
            <div style="float: left; width: 100%; height: 20px"></div>
            <input id="btn" type="submit" value="Submit" style="float: left; clear: both">
        </div>
    </form>
    <script src="js/login.js"></script>
</body>
</html>