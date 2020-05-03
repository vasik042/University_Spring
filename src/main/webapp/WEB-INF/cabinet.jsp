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
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <c:if test="${role == 'NOT_VERIFIER_ENTRANT'}">
        <p>Для подачі заявок адміністратр повинен підтвердити вашу заяву</p>
    </c:if>
    <c:if test="${role == 'ENTRANT'}">
        <table>
             <tr>
                <th>№</th>
                <th>Факультет</th>
                <th>Кількість балів</th>
             </tr>
             <c:forEach var="application" items="${applications}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                      <td><a href="/faculty?id=${application.facultySavedId}">${application.facultyName}</td>
                      <td>${application.GPA}</td>
                  </tr>
             </c:forEach>
        </table>
    </c:if>
    <c:if test="${role == 'ADMIN'}">
        <table>
             <tr>
                <th>№</th>
                <th>Прізвище</th>
                <th>Імя</th>
                <th>Електронна пошта</th>
                <th>Середня оцінка в атестаті</th>
                <th>Предмет№1</th>
                <th>Предмет№2</th>
                <th>Предмет№3</th>
                <th>Предмет№4</th>
             </tr>
             <c:forEach var="entrant" items="${entrants}" varStatus="сounter">
                  <tr>
                      <td class ="number">${сounter.count}</td>
                          <th>${entrant.surname}</th>
                          <th>${entrant.name}</th>
                          <th>${entrant.email}</th>
                          <th>${entrant.schoolGPA} </th>

                          <c:forEach var="subject" items="${subjects}">
                             <c:if test="${entrant.id == subject.entrantSavedId}">
                                <th>${subject.subjectName} - ${subject.grade}</th>
                             </c:if>
                          </c:forEach>
                          <th><a href="/activate?id=${entrant.id}">Підтвердити</th>
                  </tr>
             </c:forEach>
        </table>
    </c:if>
</body>
</html>