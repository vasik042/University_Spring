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
            }
        </style>
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>

   <div class="menu">
    <form action="/superAdminCabinetFaculty" method ="get">
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
   <c:when test="${coose == admins}">
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
                <th style="width: 150px;"></th>
                <th style="width: 150px;"></th>
             </tr>
             <c:forEach var="admin" items="${admins}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td>${admin.email}</td>
                      <td>${admin.password}</td>
                      <td><a href="/deleteAdmin?id=${admin.id}" style="color: red">Видалити</a></td>
                      <td><a href="/editAdmin?id=${admin.id}" style="color: blue">Редагувати</a></td>
                  </tr>
             </c:forEach>
        </table>
    </c:when>
    <c:when test="${coose == entrants}">
       <p style="font-size: 30px">Вступники:</p>
        <table>
             <tr>
                <th style="width: 50px;">№</th>
                <th style="width: 150px;">Прізвище</th>
                <th style="width: 150px;">Імя</th>
                <th style="width: 150px;">Електронна пошта</th>
                <th style="width: 150px;"></th>
                <th style="width: 150px;"></th>
             </tr>

             <c:forEach var="entrant" items="${entrants}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td>${entrant.surname}</td>
                      <td>${entrant.name}</td>
                      <td>${entrant.email}</td>

                      <td><a href="/deleteEntrant?id=${entrant.id}" style="color: red">Відхилити</a></td>
                      <c:if  test="${entrant.role == NOT_VERIFIED_ENTRANT}">
                          <td><a href="/activate?id=${entrant.id}" style="color: green">Підтвердити</a></td>
                      </c:if>
                  </tr>
             </c:forEach>
        </table>
    </c:when>
    <c:when test="${coose == faculties}">

        <table>
            <p style="font-size: 30px">Подані заявки:</p>
            <form action="/addAdmin" method ="get">
            <input class="btn" type="submit" value="Додати факультет"  style="background-color: lightgreen; border-radius: 5px;">
        </form>
        <br>
        <br>
        <br>
             <tr>
                <th style="width: 50px;">№</th>
                <th style="width: 300px;">Факультет</th>
                <th style="width: 150px;">Кількість місць</th>
                <th style="width: 250px;">Необхідні предмети а коефіціенти</th>
                <th style="width: 150px;">Описання</th>
                <th style="width: 90px;"></th>
             </tr>
             <c:forEach var="faculty" items="${faculties}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td>${faculty.name}</td>
                      <td>${faculty.places}</td>

                      <td>
                      <c:forEach var="subject" items="${subjects}">
                          <c:if test="${faculty.id == subject.facultySavedId}">
                              <p>${subject.subjectName} - ${subject.coefficient}</p>
                              <br>
                          </c:if>
                      </c:forEach>
                      </td>

                      <td><input class="facBtn" type="button" value="Детальніше" onclick="alert(${faculty.description})"></td>
                      <td><a href="/editfaculty?id=${faculty.id}" style="color: blue">Редагувати</a></td>
                  </tr>
             </c:forEach>
        </table>
    </c:when>
    </c:choose>
    <script src="cabin.js"></script>
</body>
</html>