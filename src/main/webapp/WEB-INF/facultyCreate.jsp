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
    <form action="/addFaculty" method ="post">
    </c:if>

    <c:if test="${!create}">
    <form action="/editFaculty" method ="post">
    </c:if>
        <input type="hidden" id="dtoId" name ="dtoId" value="${faculty.id}">
        <div style="background-color: white; width: 500px; height: 600px; margin: auto; margin-top: 20px; border-radius: 10px; border: 2px solid grey">

            <c:if test="${create}">
              <p style="text-align: center; font-size: 28px">Створення факультету</p>
            </c:if>

            <c:if test="${!create}">
              <p style="text-align: center; font-size: 28px">Редагування факультету</p>
            </c:if>

            <label for="Name">Назва
               <br>
                <input class="inp1" type="text" required id="name" name ="name" value="${faculty.name}">
            </label>
            <label for="places">Кількість місць
               <br>
                <input class="inp1" type="number"  required id="places" name ="places" value="${faculty.places}">
            </label>
            <label for="dateOfBirth">Описання
               <br>
                <p><textarea rows="10" cols="55" required id="description" name="description" value="${faculty.description}" style="margin-left: -5px; margin-top: -10px;">${faculty.description}</textarea></p>
            </label>

            <c:if test="${create}">
            <div class="znoBox">
            <br>
            <span style="width: 100%">ЗНО</span>
            <label for="subject1">Українська мова
                <input min="0" max="1" step="0.05" type="number" style="margin-left: 25px" placeholder="коефіцієнт" required id="subjectCoef1" name ="subjectCoef1" value="${faculty.subjectCoef1}">
            </label>
            <label for="subject2">
                <select name="subjectName2" id ="subjectName2" value="${faculty.subjectName2}" style ="width: 155px">
                    <option value="MATH">Математика</option>
                    <option value="HISTORY">Історія України</option>
                </select>
                <input min="0" max="1" step="0.05" type="number" placeholder="коефіцієнт" required id="subjectCoef2" name ="subjectCoef2" value="${faculty.subjectCoef2}">
            </label>
            <label for="subject3">
                <select name="subjectName3" id ="subjectName3" value="${faculty.subjectName3}">
                    <option value="HISTORY">Історія України</option>
                    <option value="MATH">Математика</option>
                    <option value="BIOLOGY">Біологія</option>
                    <option value="GEOGRAPHY">Географія</option>
                    <option value="PHYSICS">Фізика</option>
                    <option value="CHEMISTRY">Хімія</option>
                    <option value="ENGLISH">Англійська мова</option>
                    <option value="SPANISH">Іспанська мова</option>
                    <option value="GERMAN">Німецька мова</option>
                    <option value="FRENCH">Французька мова</option>
                </select>
                <input min="0" max="1" step="0.05" type="number" placeholder="коефіцієнт" required id="subjectCoef3" name ="subjectCoef3" value="${faculty.subjectCoef3}">
            </label>
            <label for="subject4">
                <select name="subjectName4" id ="subjectName4" value="${faculty.subjectName4}">
                    <option>Немає</option>
                    <option value="MATH">Математика</option>
                    <option value="HISTORY">Історія України</option>
                    <option value="BIOLOGY">Біологія</option>
                    <option value="GEOGRAPHY">Географія</option>
                    <option value="PHYSICS">Фізика</option>
                    <option value="CHEMISTRY">Хімія</option>
                    <option value="ENGLISH">Англійська мова</option>
                    <option value="SPANISH">Іспанська мова</option>
                    <option value="GERMAN">Німецька мова</option>
                    <option value="FRENCH">Французька мова</option>
                </select>
                <input min="0" max="1" step="0.05" type="number" placeholder="коефіцієнт" id="subjectCoef4" name ="subjectCoef4" value="${faculty.subjectCoef4}">
            </label>
            </div>
            </c:if>

            <c:if test="${!create}">
            <div class="znoBox">
            <br>
            <span style="width: 100%">ЗНО</span>

            <c:forEach var="subject" items="${subjects}" varStatus="сounter">
                <c:choose>
                    <c:when test="${сounter.count == 1}">
                        <label for="subject1">Українська мова
                            <input type="hidden" id="subjectName${сounter.count}" name ="subjectName${сounter.count}" value="${subject.subjectName}">
                            <input min="0" max="1" step="0.05" type="number" placeholder="коефіцієнт" id="subjectCoef${сounter.count}" name ="subjectCoef${сounter.count}" value="${subject.coefficient}">
                        </label>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${сounter.count == 2}">
                        <label for="subject2">
                            <select name="subjectName${сounter.count}" id ="subjectName${сounter.count}" value="${subject.subjectName}">
                                <option value="${subject.subjectName}">${subject.subjectName}</option>
                                <option value="MATH">Математика</option>
                                <option value="HISTORY">Історія України</option>
                            </select>
                            <input min="0" max="1" step="0.05" type="number" placeholder="коефіцієнт" id="subjectCoef${сounter.count}" name ="subjectCoef${сounter.count}" value="${subject.coefficient}">
                        </label>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${сounter.count > 2 && сounter.count < 5}">
                        <label for="subject${сounter.count}">
                            <select name="subjectName${сounter.count}" id ="subjectName${сounter.count}" value="${subject.subjectName}">
                                <option value="${subject.subjectName}">${subject.subjectName}</option>
                                <option value="MATH">Математика</option>
                                <option value="HISTORY">Історія України</option>
                                <option value="BIOLOGY">Біологія</option>
                                <option value="GEOGRAPHY">Географія</option>
                                <option value="PHYSICS">Фізика</option>
                                <option value="CHEMISTRY">Хімія</option>
                                <option value="ENGLISH">Англійська мова</option>
                                <option value="SPANISH">Іспанська мова</option>
                                <option value="GERMAN">Німецька мова</option>
                                <option value="FRENCH">Французька мова</option>
                            </select>
                            <input min="0" max="1" step="0.05" type="number" placeholder="коефіцієнт" id="subjectCoef${сounter.count}" name ="subjectCoef${сounter.count}" value="${subject.coefficient}">
                        </label>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${сounter.count > 4}">
                    </c:when>
                </c:choose>
            </c:forEach>
            </div>
            </c:if>

            <div style="float: left; width: 100%; height: 20px"></div>
            <input id="btn" type="submit" value="Відправити" style="float: left; clear: both">
        </div>
    </form>
    <script src="js/facultyCreate.js"></script>
</body>
</html>