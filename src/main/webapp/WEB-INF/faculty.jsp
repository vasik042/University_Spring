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
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>

    <c:choose>
        <c:when test="${canReg == 0}">
            <a href="/makeApplication?id=${facultyId}">Подати заявку на вступ</a>
        </c:when>
        <c:when test="${canReg == 1}">
            <p>Почекайте, поки адміністратор підтвердить вашу анкету і тоді ви зможете подавати заявки на всуп</p>
        </c:when>
        <c:when test="${canReg == 2}">
            <p>Ви більше не можете подавати заявки на вступ</p>
        </c:when>
        <c:when test="${canReg == 3}">
            <p>Ви вже зареєстровані в конкурсі на цей факультет</p>
        </c:when>
        <c:when test="${canReg == 4}">
            <p>Ви не здали предметів, потрібних для вступу на цей факультет</p>
        </c:when>
        <c:otherwise>
            <p>Для подачі заявок потрібно зареєструватись</p>
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