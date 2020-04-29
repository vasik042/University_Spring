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
        }
        span{
            float: left;
            clear: both;
        }
    </style>
</head>
<body>
    <form action="/register" method ="post">
        <div style="background-color: blue; width: 500px; height: 600px; margin: auto; margin-top: 20px; border-radius: 10px">
            <label for="Name">Ім'я
                <input type="text" placeholder="type here..." required id="name" name ="name" value="${entrant.name}">
            </label>
            <label for="surname">Прізвище
                <input type="text" placeholder="type here..." required id="surname" name ="surname" value="${entrant.surname}">
            </label>
            <label for="dateOfBirth">Дата народження
                <input type="date" placeholder="type here..." required id="dateOfBirth" name ="dateOfBirth" value="${entrant.dateOfBirth}">
            </label>
            <label for="email">Електронна пошта
                <input type="email" placeholder="type here..." required id="email" name ="email">
            </label>
            <label for="password">Пароль
                <input type="text" placeholder="type here..." required id="password" name ="password">
            </label>

            <label for="schoolGPA">Середня оцінка в атестаті
                <input type="number" placeholder="type here..." required id="schoolGPA" name ="schoolGPA" value="${entrant.schoolGPA}">
            </label>
            <span>ЗНО</span>
            <label for="subject1">Українська мова:
                <input type="number" placeholder="type here..." required id="subjectGrade1" name ="subjectGrade1" value="${entrant.subjectGrade1}">
            </label>
            <label for="subject2">
                <select name="subjectName2" id ="subjectName2" value="${entrant.subjectName2}">
                    <option value="MATH">Математика</option>
                    <option value="HISTORY">Історія України</option>
                </select>
                <input type="number" placeholder="type here..." required id="subjectGrade2" name ="subjectGrade2" value="${entrant.subjectGrade2}">
            </label>
            <label for="subject3">
                <select name="subjectName3" id ="subjectName3" value="${entrant.subjectName3}">
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
                <input type="number" placeholder="type here..." required id="subjectGrade3" name ="subjectGrade3" value="${entrant.subjectGrade3}">
            </label>
            <label for="subject4">
                <select name="subjectName4" id ="subjectName4" value="${entrant.subjectName4}">
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
                <input type="number" placeholder="type here..." id="subjectGrade4" name ="subjectGrade4" value="${entrant.subjectGrade4}">
            </label>
            
            <input id="btn" type="submit" value="Отправить" style="float: left; clear: both">
            
        </div>
    </form>
    <script src="js/register.js"></script>
</body>
</html>