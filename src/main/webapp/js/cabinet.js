function out(){
    var element = document.getElementsByClassName("infoHolder")[0];
    element.parentNode.removeChild(element);
}

function showEntrant(id, name, email, gpa){
    var newDiv = document.createElement("div");

    var subNames = document.getElementsByClassName(id+"subName");
    var subGrades = document.getElementsByClassName(id+"subGrade");

    var str1 = "<div class='infoHolder'><div class='info'>" +
                    "<input id='out' type='button' value='' onClick='out()' style='width: 20px;" +
                    "height: 20px; background-color: red; float: right'>" +
                    "<div style='float: right; width: 150px'>" +
                    "<img src='/getPhoto?id=" + id + "' class = 'studentPhoto'></img></div>"+
                    "<h1>"+ name +"</h1>" +
                    "<h5>" + "Email - " + email + "</h5>" +
                    "<h5>" + "Середня оцінка - " + gpa + "</h5>" +
                    "<table style='float: left'>" + "<tr><th style='width: 150px; border: 1px solid black;'>Здані предмети</th>" +
                    "<th style='width: 50px;'>Оцінки</th></tr>";

    for (let i = 0; i < subNames.length; i++) {
        str1 = str1 + "<tr><td>" + subNames[i].value + "</td><td>" + subGrades[i].value + "</td></tr>";
    }

    str1 = str1 + "</table></div></div>";

    newDiv.innerHTML = str1;

    menu = document.getElementsByClassName("menu")[0];
    document.body.insertBefore(newDiv, menu);
}