<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>

    <style>
        #btn{
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
    <form action="/login" method ="get">
        <div style="background-color: white; width: 500px; height: 260px; margin: auto; margin-top: 15px; border-radius: 10px; border: 2px solid grey">
            <p>Ваша електронна пошта підтверджена</p>
            <input id="btn" type="submit" value="Ввійти" style="float: left; clear: both">
        </div>
    </form>
</body>
</html>