<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>

    <style>
        label{
            float: left;
            clear: both;
        }
        span{
            float: left;
            clear: both;
        }
    </style>
</head>
<body>
    <form action="/login" method ="post">
        <div style="background-color: blue; width: 500px; height: 600px; margin: auto; margin-top: 20px; border-radius: 10px">
            <label for="email">Електронна пошта
                <input type="email" placeholder="type here..." required id="email" name ="email" value="${entrant.email}">
            </label>
            <label for="password">Пароль
                <input type="text" placeholder="type here..." required id="password" name ="password" value="${entrant.password}">
            </label>
            <input id="btn" type="submit" value="Отправить" style="float: left; clear: both">
        </div>
    </form>
</body>
</html>