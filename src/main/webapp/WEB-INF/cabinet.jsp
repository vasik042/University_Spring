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
          }.priority{
              float: left;
              width: 30px;
          }.mainInfo{
              margin: auto;
              width: 440px;
          }.mainInfoHolder{
              width: 56%;
              margin-left: 22%;
          }
      </style>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <div class="mainInfoHolder">
    <c:if test="${role == 'ENTRANT' || role == 'PAST' || role == 'NOT_PAST'}">
        <div class="mainInfo">
            <div style='float: right; width: 150px'><img src='/getPhoto?id=${entrant.id}' class = 'studentPhoto'></img></div>
            <h3>${entrant.surname} ${entrant.name}</h2>
            <p>Пошта - ${entrant.email}</p>
            <p>Дата народження - ${entrant.dateOfBirth}</p>
            <p>Оцінка в атестаті - ${entrant.schoolGPA}</p>
            <p>Залишилось заявок - ${entrant.applicationsLeft}</p>

            <table style="margin: 0;">
                <tr>
                    <th style='width: 280px;'>Зданний предмет</th>
                    <th style='width: 160px;'>Оцінка</th>
                </tr>
                <tr>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>${subject.subjectName}</td>
                            <td>${subject.grade}</td>
                        </tr>
                    </c:forEach>
                </tr>
            </table>
        </div>
        <br>
    </c:if>

    <c:if test="${role == 'NOT_VERIFIED_ENTRANT'}">
        <p>Для подачі заявок адміністратр повинен підтвердити вашу анкету</p>
    </c:if>

    <c:if test="${role == 'ENTRANT' || role == 'PAST' || role == 'NOT_PAST'}">
     <div class="mainInfoHolder1">
        <table>
            <c:if test="${role == 'ENTRANT'}">
                <p style="font-size: 30px">Подані заявки:</p>
            </c:if>
            <c:if test="${role == 'PAST'}">
                <p style="font-size: 30px">Ви пройшли на:</p>
            </c:if>
            <c:if test="${role == 'NOT_PAST'}">
                <p style="font-size: 30px">Ви не пройшли ні на один факультет =(</p>
            </c:if>

            <c:if test="${role == 'ENTRANT'}">
             <tr>
                <th style="width: 50px;">Пріорітет</th>
                <th style="width: 300px;">Факультет</th>
                <th style="width: 150px;">Кількість балів</th>
                <th style="width: 150px;">Змінити пріорітет</th>
                <th style="width: 150px;"></th>
             </tr>
             <c:forEach var="application" items="${applications}">
                  <tr>
                      <td class ="number">${application.priority}</td>
                      <td><a href="/faculty?id=${application.faculty.id}">${application.faculty.name}</a></td>
                      <td>${application.GPA}</td>
                      <td>
                        <c:if test="${application.priority > 1}">
                            <div class="priority">
                                <a  style="font-size:25px; color: green" href="/increasePriority?id=${application.id}">↑</a>
                            </div>
                        </c:if>
                        <c:if test="${application.priority < applications.size()}">
                            <div class="priority">
                                <a  style="font-size:25px; color: red" href="/reducePriority?id=${application.id}">↓</a>
                            </div>
                        </c:if>
                      </td>
                      <td><a href="/deleteApplication?id=${application.id}" style="color: red">Відхилити заявку</a></td>
                  </tr>
             </c:forEach>
             </c:if>

            <c:if test="${role == 'PAST'}">
             <tr>
                <th style="width: 50px;">Пріорітет</th>
                <th style="width: 300px;">Факультет</th>
                <th style="width: 150px;">Кількість балів</th>
             </tr>
             <c:forEach var="application" items="${applications}">
                  <tr>
                      <td class ="number">${application.priority}</td>
                      <td><a href="/faculty?id=${application.faculty.id}">${application.faculty.name}</a></td>
                      <td>${application.GPA}</td>
                  </tr>
             </c:forEach>
             </c:if>
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
                <th style="width: 150px;">Статус</th>
                <th style="width: 150px;"></th>
             </tr>

             <c:forEach var="entrant" items="${entrants}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td>${entrant.surname} ${entrant.name}</td>
                      <td>${entrant.email}</td>

                      <td>
                      <input class="facBtn" type="button" value="Детальніше" onClick="showEntrant('${entrant.id}','${entrant.surname} ${entrant.name}', '${entrant.email}', '${entrant.schoolGPA}')">

                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${entrant.id == subject.entrant.id}">
                                <input type="hidden" class="${entrant.id}subName" value="${subject.subjectName}">
                                <input type="hidden" class="${entrant.id}subGrade" value="${subject.grade}">
                            </c:if>
                        </c:forEach>
                      </td>

                      <c:if  test="${entrant.role == 'NOT_VERIFIED_ENTRANT'}">
                          <td><a href="/activateEntrant?id=${entrant.id}" style="color: green">Підтвердити</a></td>
                      </c:if>
                      <c:if  test="${entrant.role == 'NOT_VERIFIED_EMAIL_ENTRANT'}">
                          <td>Емейл не підтверджено</td>
                      </c:if>
                      <c:if  test="${entrant.role == 'ENTRANT'}">
                          <td>Підтверджено</td>
                      </c:if>
                      <td><a href="/deleteEntrant?id=${entrant.id}" style="color: red">Відхилити</a></td>
                  </tr>
             </c:forEach>
        </table>
        </div>
    </c:if>
</div>
    <script>
          function out(){
                var element = document.getElementsByClassName("infoHolder")[0];
                element.parentNode.removeChild(element);
          }

          function showEntrant(id, name, email, gpa){
                var newDiv = document.createElement("div");

                var subNames = document.getElementsByClassName(id+"subName");
                var subGrades = document.getElementsByClassName(id+"subGrade");

                var str1 = "<div class='infoHolder'><div class='info'>" +
                    "<input id='out' type='button' value='' onClick='out()' style='width: 20px;" +
                    "height: 20px; background-color: red; float: right'>" +
                    "<div style='float: right; width: 150px'>" +
                    "<img src='/getPhoto?id=" + id + "' class = 'studentPhoto'></img></div>"+
                    "<h1>"+ name +"</h1>" +
                    "<h5>" + "Email - " + email + "</h5>" +
                    "<h5>" + "Середня оцінка - " + gpa + "</h5>" +
                    "<table style='float: left'>" + "<tr><th style='width: 150px; border: 1px solid black;'>Здані предмети</th>" +
                    "<th style='width: 50px;'>Оцінки</th></tr>";

                for (let i = 0; i < subNames.length; i++) {
                    str1 = str1 + "<tr><td>" + subNames[i].value + "</td><td>" + subGrades[i].value + "</td></tr>";
                }

                str1 = str1 + "</table></div></div>";

                newDiv.innerHTML = str1;

                menu = document.getElementsByClassName("menu")[0];
                document.body.insertBefore(newDiv, menu);
          }
    </script>
</body>
</html>