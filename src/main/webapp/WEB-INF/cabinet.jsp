<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
          }.info{
                position: absolute;
                background-color: lightgray;
                width: 600px;
                height: 330px;
                border-radius: 10px;
                border: 2px solid black;
            }.infoHolder{
                position: absolute;
                width: 100%;
                height: 100%;
                display: flex;
                justify-content: center;
                margin-left: 20px;
            }.mainInfoHolder{
                width: 56%;
                margin-left: 22%;
            }.studentPhoto{
                height: 120px;
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
            <p><spring:message code="cabinet.email" /> - ${entrant.email}</p>
            <p><spring:message code="cabinet.dateOfBirth" /> - ${entrant.dateOfBirth}</p>
            <p><spring:message code="cabinet.schoolGpa" /> - ${entrant.schoolGPA}</p>
            <p><spring:message code="cabinet.applicationsLeft" /> - ${entrant.applicationsLeft}</p>

            <table style="margin: 0;">
                <tr>
                    <th style='width: 280px;'><spring:message code="cabinet.subject" /></th>
                    <th style='width: 160px;'><spring:message code="cabinet.grade" /></th>
                </tr>
                <tr>
                    <c:forEach var="grade" items="${grades}">
                        <tr>
                            <td><spring:message code="subject.${grade.subject.englishName}" /></td>
                            <td>${grade.grade}</td>
                        </tr>
                    </c:forEach>
                </tr>
            </table>
        </div>
        <br>
    </c:if>

    <c:if test="${role == 'NOT_VERIFIED_ENTRANT'}">
        <p><spring:message code="cabinet.notVerified" /></p>
    </c:if>

    <c:if test="${role == 'ENTRANT' || role == 'PAST' || role == 'NOT_PAST'}">
     <div class="mainInfoHolder1">
        <table>
            <c:if test="${role == 'ENTRANT'}">
                <p style="font-size: 30px"><spring:message code="cabinet.applications" /></p>
            </c:if>
            <c:if test="${role == 'PAST'}">
                <p style="font-size: 30px"><spring:message code="cabinet.past" /></p>
            </c:if>
            <c:if test="${role == 'NOT_PAST'}">
                <p style="font-size: 30px"><spring:message code="cabinet.notPast" /></p>
            </c:if>

            <c:if test="${role == 'ENTRANT'}">
             <tr>
                <th style="width: 50px;"><spring:message code="cabinet.priority" /></th>
                <th style="width: 300px;"><spring:message code="cabinet.faculty" /></th>
                <th style="width: 150px;"><spring:message code="cabinet.gpa" /></th>
                <th style="width: 150px;"><spring:message code="cabinet.changePriority" /></th>
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
                                <a  style="font-size:25px; color: green" href="/cabinet/entrant/increasePriority?id=${application.id}">↑</a>
                            </div>
                        </c:if>
                        <c:if test="${application.priority < applications.size()}">
                            <div class="priority">
                                <a  style="font-size:25px; color: red" href="/cabinet/entrant/reducePriority?id=${application.id}">↓</a>
                            </div>
                        </c:if>
                      </td>
                      <td><a href="/cabinet/entrant/deleteApplication?id=${application.id}" style="color: red"><spring:message code="cabinet.deleteApplication" /></a></td>
                  </tr>
             </c:forEach>
             </c:if>

            <c:if test="${role == 'PAST'}">
             <tr>
                <th style="width: 50px;"><spring:message code="cabinet.priority" /></th>
                <th style="width: 300px;"><spring:message code="cabinet.faculty" /></th>
                <th style="width: 150px;"><spring:message code="cabinet.gpa" /></th>
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

                        <c:forEach var="grade" items="${grades}">
                            <c:if test="${entrant.id == grade.entrant.id}">
                                <input type="hidden" class="${entrant.id}subName" value="${grade.subject.ukrainianName}">
                                <input type="hidden" class="${entrant.id}subGrade" value="${grade.grade}">
                            </c:if>
                        </c:forEach>
                      </td>

                      <c:if  test="${entrant.role == 'NOT_VERIFIED_ENTRANT'}">
                          <td><a href="/cabinet/admin/activateEntrant?id=${entrant.id}" style="color: green">Підтвердити</a></td>
                      </c:if>
                      <c:if  test="${entrant.role == 'NOT_VERIFIED_EMAIL_ENTRANT'}">
                          <td>Емейл не підтверджено</td>
                      </c:if>
                      <c:if  test="${entrant.role == 'ENTRANT'}">
                          <td>Підтверджено</td>
                      </c:if>
                      <td><a href="/cabinet/admin/deleteEntrant?id=${entrant.id}" style="color: red">Відхилити</a></td>
                  </tr>
             </c:forEach>
        </table>
        </div>
    </c:if>
</div>
    <script src="/js/cabinet.js"></script>
</body>
</html>