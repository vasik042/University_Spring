<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
    <form action="/register" method ="post" enctype="multipart/form-data">
        <div style="background-color: white; width: 500px; height: 640px; margin: auto; margin-top: 20px; border-radius: 10px; border: 2px solid grey">
            
            <p style="margin-left: 130px; font-size: 28px"><spring:message code="register.form" /></p>
            <label for="Photo"><spring:message code="register.photo" />
                <br>
                <input id="photo" type="file" name="photo" value="">
            </label>
            <label for="Name"><spring:message code="register.name" />
               <br>
                <input class="inp1" type="text" required id="name" name ="name" value="${entrant.name}">
            </label>
            <label for="surname"><spring:message code="register.surname" />
               <br>
                <input class="inp1" type="text"  required id="surname" name ="surname" value="${entrant.surname}">
            </label>
            <label for="dateOfBirth"><spring:message code="register.dateOfBirth" />
               <br>
                <input class="inp1" type="date" p required id="dateOfBirth" name ="dateOfBirth" value="${entrant.dateOfBirth}">
            </label>
            <label for="email"><spring:message code="register.email" />
               <br>
                <input class="inp1" type="email" required id="email" name ="email" value="${entrant.email}">
            </label>
            <label for="password"><spring:message code="register.password" />
               <br>
                <input class="inp1" type="text" required id="password" name ="password" value="${entrant.password}">
            </label>

            <label for="schoolGPA"><spring:message code="register.schoolGPA" />
               <br>
                <input min="1" max="12" step="0.1" class="inp1" type="number"  required id="schoolGPA" name ="schoolGPA" value="${entrant.schoolGPA}">
            </label>
            <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            <div class="znoBox">
            <br>
            <span style="width: 100%"><spring:message code="register.ZNO" /></span>
            <label for="subject1"><spring:message code="subject.UKRAINIAN" />
                <input min="100" max="200" step="1" type="number" style="margin-left: 25px" placeholder="100" required id="subjectGrade1" name ="subjectGrade1" value="${entrant.subjectGrade1}">
            </label>
            <label for="subject2">
                <select name="subjectName2" id ="subjectName2" value="${entrant.subjectName2}" style ="width: 155px">
                    <option value="MATH"><spring:message code="subject.MATH" /></option>
                    <option value="HISTORY"><spring:message code="subject.HISTORY" /></option>
                </select>
                <input min="100" max="200" step="1"  type="number" placeholder="100" required id="subjectGrade2" name ="subjectGrade2" value="${entrant.subjectGrade2}">
            </label>
            <label for="subject3">
                <select name="subjectName3" id ="subjectName3" value="${entrant.subjectName3}">
                    <option value="HISTORY"><spring:message code="subject.HISTORY" /></option>
                    <option value="MATH"><spring:message code="subject.MATH" /></option>
                    <option value="BIOLOGY"><spring:message code="subject.BIOLOGY" /></option>
                    <option value="GEOGRAPHY"><spring:message code="subject.GEOGRAPHY" /></option>
                    <option value="PHYSICS"><spring:message code="subject.PHYSICS" /></option>
                    <option value="CHEMISTRY"><spring:message code="subject.CHEMISTRY" /></option>
                    <option value="ENGLISH"><spring:message code="subject.ENGLISH" /></option>
                    <option value="SPANISH"><spring:message code="subject.SPANISH" /></option>
                    <option value="GERMAN"><spring:message code="subject.GERMAN" /></option>
                    <option value="FRENCH"><spring:message code="subject.FRENCH" /></option>
                </select>
                <input min="100" max="200" step="1" type="number" placeholder="100" required id="subjectGrade3" name ="subjectGrade3" value="${entrant.subjectGrade3}">
            </label>
            <label for="subject4">
                <select name="subjectName4" id ="subjectName4" value="${entrant.subjectName4}">
                    <option><spring:message code="subject.nothing" />Немає</option>
                    <option value="MATH"><spring:message code="subject.MATH" /></option>
                    <option value="HISTORY"><spring:message code="subject.HISTORY" /></option>
                    <option value="BIOLOGY"><spring:message code="subject.BIOLOGY" /></option>
                    <option value="GEOGRAPHY"><spring:message code="subject.GEOGRAPHY" /></option>
                    <option value="PHYSICS"><spring:message code="subject.PHYSICS" /></option>
                    <option value="CHEMISTRY"><spring:message code="subject.CHEMISTRY" /></option>
                    <option value="ENGLISH"><spring:message code="subject.ENGLISH" /></option>
                    <option value="SPANISH"><spring:message code="subject.SPANISH" /></option>
                    <option value="GERMAN"><spring:message code="subject.GERMAN" /></option>
                    <option value="FRENCH"><spring:message code="subject.FRENCH" /></option>
                </select>
                <input min="100" max="200" step="1" type="number" placeholder="100" id="subjectGrade4" name ="subjectGrade4" value="${entrant.subjectGrade4}">
            </label>
            </div>
            
            <div style="float: left; width: 100%; height: 20px"></div>
            <input id="btn" type="submit" value="Submit" style="float: left; clear: both">
        </div>
    </form>
    <script src="js/register.js"></script>
</body>
</html>