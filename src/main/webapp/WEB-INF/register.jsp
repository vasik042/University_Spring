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
    <form action="/register" method ="post">
        <div style="background-color: white; width: 500px; height: 600px; margin: auto; margin-top: 20px; border-radius: 10px; border: 2px solid grey">
            
              <p style="margin-left: 130px; font-size: 28px">Реєстраційна форма</p>
               
            <label for="Name">Ім'я
               <br>
                <input class="inp1" type="text" required id="name" name ="name" value="${entrant.name}">
            </label>
            <label for="surname">Прізвище
               <br>
                <input class="inp1" type="text"  required id="surname" name ="surname" value="${entrant.surname}">
            </label>
            <label for="dateOfBirth">Дата народження
               <br>
                <input class="inp1" type="date" p required id="dateOfBirth" name ="dateOfBirth" value="${entrant.dateOfBirth}">
            </label>
            <label for="email">Електронна пошта
               <br>
                <input class="inp1" type="email" required id="email" name ="email" value="${entrant.email}">
            </label>
            <label for="password">Пароль
               <br>
                <input class="inp1" type="text" required id="password" name ="password" value="${entrant.password}">
            </label>

            <label for="schoolGPA">Середня оцінка в атестаті
               <br>
                <input class="inp1" type="number"  required id="schoolGPA" name ="schoolGPA" value="${entrant.schoolGPA}">
            </label>
            <div class="znoBox">
            <br>
            <span style="width: 100%">ЗНО</span>
            <label for="subject1">Українська мова
                <input type="number" style="margin-left: 25px" placeholder="Оцінка" required id="subjectGrade1" name ="subjectGrade1" value="${entrant.subjectGrade1}">
            </label>
            <label for="subject2">
                <select name="subjectName2" id ="subjectName2" value="${entrant.subjectName2}" style ="width: 155px">
                    <option value="MATH">Математика</option>
                    <option value="HISTORY">Історія України</option>
                </select>
                <input type="number" placeholder="Оцінка" required id="subjectGrade2" name ="subjectGrade2" value="${entrant.subjectGrade2}">
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
                <input type="number" placeholder="Оцінка" required id="subjectGrade3" name ="subjectGrade3" value="${entrant.subjectGrade3}">
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
                <input type="number" placeholder="Оцінка" id="subjectGrade4" name ="subjectGrade4" value="${entrant.subjectGrade4}">
            </label>
            </div>
            
            <div style="float: left; width: 100%; height: 20px"></div>
            <input id="btn" type="submit" value="Відправити" style="float: left; clear: both">
        </div>
    </form>
    <script src="js/register.js"></script>
</body>
</html>