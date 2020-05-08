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
            width: 80%;
            margin-left: 10%;
            font-family: inherit;
            font-size: 18px;
            text-align: center;
        }
        span{
            float: left;
            clear: both;
        }input{
            float: right;
        }.inp1{
            width: 100%;
            height: 25px;
            border-radius: 5px;

        }#btn{
            margin-left: 25%;
            float: left;
            width: 50%;
            height: 30px;
            border-radius: 5px;
            background-color: gainsboro;
            color: grey;

        }select{
            border: 0px;
            font-family: inherit;
            font-size: 18px;
        }.znoBox{
            float: left;
            width: 90%;
            margin-left: 5%;
            height: 120px;
            text-align: center;
        }
    </style>
</head>
<body>
       <c:if test="${create}">
       <form action="/addAdmin" method ="post">
       </c:if>

       <c:if test="${!create}">
       <form action="/editAdmin" method ="post">
       </c:if>
        <div style="background-color: white; width: 500px; height: 300px; margin: auto; margin-top: 20px; border-radius: 10px; border: 2px solid grey">

              <c:if test="${create}">
                <p style="text-align: center; font-size: 28px">Cтворення адміністратора</p>
              </c:if>

              <c:if test="${!create}">
                <p style="text-align: center; font-size: 28px">Редагування адміністратора</p>
              </c:if>

            <input type="hidden" id="dtoId" name ="dtoId" value="${admin.id}">
            <label for="email">Електронна пошта
               <br>
                <input class="inp1" type="email" required id="email" name ="email" value="${admin.email}">
            </label>
            <label for="password">Пароль
               <br>
                <input class="inp1" type="text" required id="password" name ="password" value="${admin.password}">
            </label>

            <div style="float: left; width: 100%; height: 20px"></div>
            <input id="btn" type="submit" value="Відправити" style="float: left; clear: both">
        </div>
    </form>
    <script src="js/adminCreate.js"></script>
</body>
</html>