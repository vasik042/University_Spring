<!DOCTYPE html>
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
    <form action="" method ="post">
        <div style="background-color: blue; width: 500px; height: 600px; margin: auto; margin-top: 20px; border-radius: 10px">
            <label for="Name">Ім'я
                <input type="text" placeholder="type here..." required id="name" name ="ame">
            </label>
            <label for="surname">Прізвище
                <input type="text" placeholder="type here..." required id="surname" name ="surname">
            </label>
            <label for="dateOfBirth">Дата народження
                <input type="date" placeholder="type here..." required id="dateOfBirth" name ="dateOfBirth">
            </label>
            <label for="email">Електронна пошта
                <input type="email" placeholder="type here..." required id="email" name ="email">
            </label>
            <label for="password">Пароль
                <input type="text" placeholder="type here..." required id="password" name ="password">
            </label>
            
            <label for="schoolGPA">Середня оцінка в атестаті
                <input type="number" placeholder="type here..." required id="schoolGPA" name ="schoolGPA">
            </label>
            <span>ЗНО</span>
            <label for="ZNO1">Українська мова:
                <input type="number" placeholder="type here..." required id="UKRAINIAN" name ="UKRAINIAN">
            </label>
            <label for="ZNO2">
                <select name="subject2" id ="subject2">
                    <option value="MATH">Математика</option>
                    <option value="HISTORY">Історія України</option>
                </select>
                <input type="number" placeholder="type here..." required id="subject2grade" name ="subject2grade">
            </label>
            <label for="ZNO3">
                <select name="subject3" id ="subject3">
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
                <input type="number" placeholder="type here..." required id="subject3grade" name ="subject3grade">
            </label>
            <label for="ZNO4">
                <select name="subject4" id ="subject4">
                    <option value="nothing">Немає</option>
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
                <input type="number" placeholder="type here..." id="subject4grade" name ="subject4grade">
            </label>
            
            <input id="btn" type="submit" value="Отправить" style="float: left; clear: both">
            
        </div>
    </form>
    <script src="js/register.js"></script>
</body>
</html>