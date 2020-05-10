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
          }.mainInfoHolder1{
              width: 56%;
              margin-left: 22%;
          }.mainInfoHolder2{
              width: 100%;
          }
      </style>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

     <c:if test="${role == 'NOT_VERIFIED_ENTRANT'}">
        <p>Для подачі заявок адміністратр повинен підтвердити вашу заяву</p>
    </c:if>
    <c:if test="${role == 'ENTRANT'}">
     <div class="mainInfoHolder1">
        <table>
            <p style="font-size: 30px">Подані заявки:</p>
             <tr>
                <th style="width: 50px;">№</th>
                <th style="width: 300px;">Факультет</th>
                <th style="width: 150px;">Кількість балів</th>
                <th style="width: 150px;"></th>
             </tr>
             <c:forEach var="application" items="${applications}" varStatus="counter">
                  <tr>
                      <td class ="number">${counter.count}</td>
                      <td><a href="/faculty?id=${application.facultySavedId}">${application.facultyName}</a></td>
                      <td>${application.GPA}</td><td><a href="/deleteApplication?id=${application.id}" style="color: red">Відхилити заявку</a></td>
                  </tr>
             </c:forEach>
        </table>
        </div>
    </c:if>
    <c:if test="${role == 'ADMIN'}">
       <div class="mainInfoHolder2">
        <table>
        <p style="font-size: 30px">Вступники:</p>
             <tr>
                <th style="width: 30px;">№</th>
                <th style="width: 180px;">Прізвище та ім'я</th>
                <th style="width: 150px;">Електронна пошта</th>
                <th style="width: 50px;">Детальніше</th>
                <th style="width: 150px;"></th>
                <th style="width: 150px;"></th>
             </tr>

             <c:forEach var="entrant" items="${entrants}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td>${entrant.surname} ${entrant.name}</td>
                      <td>${entrant.email}</td>

                      <td>
                      <input class="facBtn" type="button" value="Детальніше" onClick="showEntrant('${entrant.id}',
                                             '${entrant.name} ${entrant.surname}', '${entrant.email}', '${entrant.schoolGPA}')">

                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${entrant.id == subject.entrantSavedId}">
                                <input type="hidden" class="${entrant.id}Name" value="${subject.subjectName}">
                                <input type="hidden" class="${entrant.id}Grade" value="${subject.grade}">
                            </c:if>
                        </c:forEach>
                      </td>

                      <td><a href="/activateEntrant?id=${entrant.id}" style="color: green">Підтвердити</a></td>
                      <td><a href="/deleteEntrant?id=${entrant.id}" style="color: red">Відхилити</a></td>
                  </tr>
             </c:forEach>
        </table>
        </div>
    </c:if>

    <script>
          function out(){
                var element = document.getElementsByClassName("infoHolder")[0];
                element.parentNode.removeChild(element);
          }

          function showEntrant(id, name, email, gpa){
                var newDiv = document.createElement("div");

                var subNames = document.getElementsByClassName(id+"Name");
                var subGrades = document.getElementsByClassName(id+"Grade");

                var str1 = "<div class='infoHolder'><div class='info'>" +
                    "<input id='out' type='button' value='' onClick='out()' style='width: 20px;" +
                    "height: 20px; background-color: red; float: right'>" +
                    "<h1>"+ name +"</h1>" +
                    "<h5>" + "Email - " + email + "</h5>" +
                    "<h5>" + "Середня оцінка - " + gpa + "</h5>" +
                    "<table>" + "<tr><th style='width: 150px;'>Здані предмети</th>" +
                    "<th style='width: 50px;'>Оцінки</th></tr>" +
                    "<div><tr><td>" + subNames[0].value + "</td>" +
                    "<td>" + subGrades[0].value + "</td></tr>" +
                    "<tr><td>" + subNames[1].value + "</td>" +
                    "<td>" + subGrades[1].value + "</td></tr>" +
                    "<tr><td>" + subNames[2].value + "</td>" +
                    "<td>" + subGrades[2].value + "</td></tr>";

                if(subNames.length == 4){
                    str1 = str1 + "<tr><td>" + subNames[3].value + "</td>" +
                        "<td>" + subGrades[3].value + "</td></tr></div>" +
                        "</div></div>";
                }else{
                    str1 = str1 + "</div>" +
                        "</div></div>";
                }

                newDiv.innerHTML = str1;

                menu = document.getElementsByClassName("menu")[0];
                document.body.insertBefore(newDiv, menu);
          }
    </script>

</body>
</html>