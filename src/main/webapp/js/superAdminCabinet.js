function out(){
    var element = document.getElementsByClassName("infoHolder")[0];
    element.parentNode.removeChild(element);
}

function showFaculty(id, name, places, description){
    var newDiv = document.createElement("div");

    var subNames = document.getElementsByClassName(id+"Name");
    var subCoefs = document.getElementsByClassName(id+"Coef");

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

    if(subNames.length == 4){
    str1 = str1 + "<tr><td>" + subNames[3].value + "</td>" +
                        "<td>" + subCoefs[3].value + "</td></tr></div>" +
                        "<h3>" + "Описання" + "</h3>" +
                        "<p>" + description + "</p>"
                        "</div></div>";
    }else{
    str1 = str1 + "</div>" +
                        "<h3>" + "Описання" + "</h3>" +
                        "<p>" + description + "</p>"
                        "</div></div>";
    }

    newDiv.innerHTML = str1;

    menu = document.getElementsByClassName("menu")[0];
    document.body.insertBefore(newDiv, menu);
}

function showEntrant(id, name, email, gpa){
    var newDiv = document.createElement("div");

    var subNames = document.getElementsByClassName(id+"subName");
    var subGrades = document.getElementsByClassName(id+"subGrade");

    var appNames = document.getElementsByClassName(id+"appName");
    var appGrades = document.getElementsByClassName(id+"appGrade");
    var priority = document.getElementsByClassName(id+"priority");
    var facId = document.getElementsByClassName(id+"facId");

    var str1 = "<div class='infoHolder'><div class='info'>" +
                    "<input id='out' type='button' value='' onClick='out()' style='width: 20px;" +
                    "height: 20px; background-color: red; float: right'>" +
                    "<div style='float: right; width: 150px'>" +
                    "<img src='/getPhoto?id=" + id + "' class = 'studentPhoto'></img></div>"+
                    "<h1>"+ name +"</h1>" +
                    "<h5>" + "Email - " + email + "</h5>" +
                    "<h5>" + "Середня оцінка - " + gpa + "</h5>" +
                    "<table style='float: left'>" + "<tr><th style='width: 150px;'>Здані предмети</th>" +
                    "<th style='width: 50px;'>Оцінки</th></tr>";

    for (let i = 0; i < subNames.length; i++) {
        str1 = str1 + "<tr><td>" + subNames[i].value + "</td><td>" + subGrades[i].value + "</td></tr>";
    }

    str1 = str1 + "</table><table style='float: right'>" + "<tr><th style='width: 50px;'>Пріорітет</th><th style='width: 150px;'>Заявка на:</th>" +
                              "<th style='width: 50px;'>Оцінка</th></tr>";

    for (let i = 0; i < appNames.length; i++) {
        str1 = str1 + "<tr><td>" + priority[i].value + "</td><td><a href='/faculty?id=" + facId[i].value + "'>" + appNames[i].value + "</a></td><td>" + appGrades[i].value + "</td></tr>";
    }

    str1 = str1 + "</table></div></div>";

    newDiv.innerHTML = str1;

    menu = document.getElementsByClassName("menu")[0];
    document.body.insertBefore(newDiv, menu);
}