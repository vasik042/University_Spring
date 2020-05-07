<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>

<html lang="en">
<head>
  <title>Кабінет</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <style>
            .btn{
                float: left;
                width: 200px;
                height: 30px;
                font-size: 15px;
            }.facBtn{
                float: left;
                width: 200px;
                height: 30px;
                font-size: 15px;
            }.menu{
                widows: 100%;
                justify-content: center;
                display: flex;
            }th{
                border: 1px solid black;
                border-bottom: 2px solid black;
            }td{
                border: 1px solid black;
                font-family: sans-serif;

            }table{
                border: 2px solid black;
                border-collapse: collapse;
            }.info{
                position: absolute;
                background-color: lightgray;
                width: 600px;
                height: 600px;
                border-radius: 10px;
                border: 2px solid black;
            }.infoHolder{
                position: absolute;
                width: 100%;
                height: 100%;
                display: flex;
                justify-content: center;
                margin-left: 20px;
            }
        </style>
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>

   <div class="menu">
    <form action="/superAdminCabinetFaculties" method ="get">
        <input class="btn" type="submit" value="Факультети">
    </form>
    <form action="/superAdminCabinetAdmins" method ="get">
        <input class="btn" type="submit" value="Адміністратори">
    </form>
    <form action="/superAdminCabinetEntrants" method ="get">
        <input class="btn" type="submit" value="Вступники">
    </form>

   </div>
   <c:choose>
   <c:when test="${choose == 'admins'}">
        <table>
            <p style="font-size: 30px">Адміністратори:</p>
            <form action="/addAdmin" method ="get">
                <input class="btn" type="submit" value="Додати адміністратора"  style="background-color: lightgreen; border-radius: 5px;">
            </form>
            <br>
            <br>
            <br>
             <tr>
                <th style="width: 50px;">№</th>
                <th style="width: 300px;">Пошта</th>
                <th style="width: 150px;">Пароль</th>
                <th style="width: 150px;">Роль</th>
                <th style="width: 150px;"></th>
                <th style="width: 150px;"></th>
             </tr>
             <c:forEach var="admin" items="${admins}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td>${admin.email}</td>
                      <td>${admin.password}</td>
                      <td>${admin.role}</td>
                      <td><a href="/deleteAdmin?id=${admin.id}" style="color: red">Видалити</a></td>
                      <td><a href="/editAdmin?id=${admin.id}" style="color: blue">Редагувати</a></td>
                  </tr>
             </c:forEach>
        </table>
    </c:when>
    <c:when test="${choose == 'entrants'}">
        <table>
        <p style="font-size: 30px">Вступники:</p>
             <tr>
                <th style="width: 50px;">№</th>
                <th style="width: 180px;">Прізвище та ім'я</th>
                <th style="width: 150px;">Електронна пошта</th>
                <th style="width: 50px;">Оцінка</th>
                <th style="width: 150px;">ЗНО</th>
                <th style="width: 150px;"></th>
                <th style="width: 150px;"></th>
             </tr>

             <c:forEach var="entrant" items="${entrants}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td>${entrant.surname} ${entrant.name}</td>
                      <td>${entrant.email}</td>
                      <td>${entrant.schoolGPA}</td>

                      <td>
                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${entrant.id == subject.entrantSavedId}">
                                <p>${subject.subjectName} - ${subject.grade}</p>
                                <br>
                            </c:if>
                        </c:forEach>
                      </td>

                      <td><a href="/deleteEntrant?id=${entrant.id}" style="color: red">Відхилити</a></td>
                      <c:if  test="${entrant.role == NOT_VERIFIED_ENTRANT}">
                          <td><a href="/activate?id=${entrant.id}" style="color: green">Підтвердити</a></td>
                      </c:if>
                      <c:if  test="${entrant.role != NOT_VERIFIED_ENTRANT}">
                          <td>Підтверджено</td>
                      </c:if>
                  </tr>
             </c:forEach>
        </table>
    </c:when>
    <c:when test="${choose == 'faculties'}">
        <table>
            <p style="font-size: 30px">Подані заявки:</p>

            <form action="/addFaculty" method ="get">
                <input class="btn" type="submit" value="Додати факультет"  style="background-color: lightgreen; border-radius: 5px;">
            </form>
            <br>
            <br>
            <br>

             <tr>
                <th style="width: 50px;">№</th>
                <th style="width: 300px;">Факультет</th>
                <th style="width: 100px;">Описання</th>
                <th style="width: 150px;"></th>

             </tr>
             <c:forEach var="faculty" items="${faculties}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td>${faculty.name}</td>
                      <td><input class="facBtn" type="button" value="Детальніше" onClick="reply_click('${faculty.id}',
                       '${faculty.name}', '${faculty.places}', '${faculty.description}')"></td>
                      <td><a href="/editFaculty?id=${faculty.id}" style="color: blue">Редагувати</a></td>

                      <c:forEach var="subject" items="${subjects}">
                        <c:if test="${faculty.id == subject.facultySavedId}">
                            <input type="hidden" class="${faculty.name}Name" value="${subject.subjectName}">
                            <input type="hidden" class="${faculty.name}Coef" value="${subject.coefficient}">
                        </c:if>
                      </c:forEach>
                  </tr>
             </c:forEach>
        </table>
    </c:when>
    </c:choose>
    <script>
          function out(){
                var element = document.getElementsByClassName("infoHolder")[0];
                element.parentNode.removeChild(element);
          }

          function reply_click(id, name, places, description){
                var newDiv = document.createElement("div");

                var subNames = document.getElementsByClassName(name+"Name");
                var subCoefs = document.getElementsByClassName(name+"Coef");

                var str1 = "<div class='infoHolder'><div class='info'>" +
                    "<input id='out' type='button' value='' onClick='out()' style='width: 20px;" +
                    "height: 20px; background-color: red; float: right'>" +
                    "<h1>"+ name +"</h1>" +
                    "<h3>" + "Місць - " + places + "</h3>" +
                    "<table>" + "<tr><th style='width: 150px;'>Необхідні предмети</th>" +
                    "<th style='width: 50px;'>Коефіціенти</th></tr>" +
                    "<div><tr><td>" + subNames[0].value + "</td>" +
                    "<td>" + subCoefs[0].value + "</td></tr>" +
                    "<tr><td>" + subNames[1].value + "</td>" +
                    "<td>" + subCoefs[1].value + "</td></tr>" +
                    "<tr><td>" + subNames[2].value + "</td>" +
                    "<td>" + subCoefs[2].value + "</td></tr>";

                var str2 = "";

                if(subNames.length == 4){
                    str2 = "<tr><td>" + subNames[3].value + "</td>" +
                        "<td>" + subCoefs[3].value + "</td></tr></div>" +
                        "<h3>" + "Описання" + "</h3>" +
                        "<p>" + description + "</p>"
                        "</div></div>";
                }else{
                    str2 = "</div>" +
                        "<h3>" + "Описання" + "</h3>" +
                        "<p>" + description + "</p>"
                        "</div></div>";
                }

                newDiv.innerHTML = str1 + str2;

                menu = document.getElementsByClassName("menu")[0];
                document.body.insertBefore(newDiv, menu);
          }
    </script>
</body>
</html>