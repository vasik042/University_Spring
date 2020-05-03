<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
      <style>
                .someProblems{
                    width: 500px;
                    background-color: lightgrey;
                    height: 40px;
                    font-weight: bold;
                    border: 1px solid black;
                    border-radius: 15px;
                }.img{
                    float: left;
                    width: 12px;
                    margin-top: 2px;
                    margin-left: 5px;
                }.ok{
                    width: 300px;
                    height: 45px;
                    background-color: lightgrey;
                    border: 1px solid black;
                    border-radius: 5px;

                }.p{
                    float: left;
                    float: left;
                    margin-top: 5px;
                    color: firebrick;
                }.span{
                    float: left;
                    color: firebrick;
                }.a{
                    float: left;
                    font-weight: 600;
                    font-size: 25px;
                    color: darkblue;
                    margin-left: 10px;
                }th{
                    border: 1px solid black;
                    border-bottom: 2px solid black;
                }td{
                    border: 1px solid black;

                }table{
                    border: 2px solid black;
                    border-radius: 20px;
                }
            </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <c:choose>
            <c:when test="${canReg == 0}">
               <div class="ok">
                <a  class="a" href="/makeApplication?id=${facultyId}">Подати заявку на вступ </a>
               </div>
            </c:when>
            <c:when test="${canReg == 1}">
               <div class="someProblems" style="width: 420px; height: 55px">
                <img class="img" src="exclamationMark.png" alt="" style="margin-top: 8px;">
                    <div style="width: 420px; height: 80px">
                        <span class="span"> Почекайте, поки адміністратор підтвердить вашу анкету і тоді ви зможете подавати заявки на всуп</span>
                    </div>
                </div>
            </c:when>
            <c:when test="${canReg == 2}">
               <div class="someProblems" style="width: 400px;">
               <img class="img" src="exclamationMark.png" alt="">
                <p class="p">Ви більше не можете подавати заявки на вступ</p>
                </div>
            </c:when>
            <c:when test="${canReg == 3}">
               <div class="someProblems" style="width: 420px;">
               <img class="img" src="exclamationMark.png" alt="">
                <p class="p">Ви вже зареєстровані в конкурсі на цей факультет</p>
                </div>
            </c:when>
            <c:when test="${canReg == 4}">
               <div class="someProblems" style="width: 434px; height: 55px;">
               <img class="img" src="exclamationMark.png" alt="" style="margin-top: 8px;">
                <span class="span">Ви не здали предметів, потрібних для вступу на цей факультет</span>
                </div>
            </c:when>
            <c:otherwise>
               <div class="someProblems" style="width: 380px;">
               <img class="img" src="exclamationMark.png" alt="">
                <p class="p">Для подачі заявок потрібно зареєструватись</p>
                </div>
            </c:otherwise>
        </c:choose>

     <table>
     <tr>
        <th>№</th>
        <th>Імя</th>
        <th>Кількість балів</th>
     </tr>
     <c:forEach var="entrant" items="${entrants}" varStatus="сounter">
            <c:if test="${сounter.count <= entrant.places}">
                <tr style = "background-color: green">
                    <td class ="number">${сounter.count}</td>
                    <td>${entrant.name}</td>
                    <td>${entrant.GPA}</td>
                </tr>
            </c:if>
            <c:if test="${сounter.count > entrant.places}">
                <tr  style = "background-color: orange">
                    <td class ="number">${сounter.count}</td>
                    <td>${entrant.name}</td>
                    <td>${entrant.GPA}</td>
                </tr>
            </c:if>
     </c:forEach>
     </table>
</body>
</html>