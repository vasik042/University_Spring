let name = false;
let surname = false;
let email = false;
let password = false;
let date = false;
let schoolGPA = false;
let subjectGrade1 = false;
let subjectName2 = "MATH";
let subjectGrade2 = false;
let subjectName3 = "HISTORY";
let subjectGrade3 = false;
let subjectName4 = false;
let subjectGrade4 = false;



document.getElementById('name').onchange = function(){
    name = document.getElementById('name').value;
}

document.getElementById('surname').onchange = function(){
    surname = document.getElementById('surname').value;
}

document.getElementById('dateOfBirth').onchange = function(){
    date = document.getElementById('dateOfBirth').value;
}

document.getElementById('email').onchange = function(){
    email = document.getElementById('email').value;
}

document.getElementById('password').onchange = function(){
    password = document.getElementById('password').value;
}

document.getElementById('schoolGPA').onchange = function(){
    schoolGPA = document.getElementById('schoolGPA').value;
    
    if (schoolGPA > 12 || schoolGPA < 1){
        alert("Середня оцінка не може бути менше 1 або більше 12!");
        document.getElementById('schoolGPA').value = false;
        schoolGPA = false;
    }
}

document.getElementById('subjectGrade1').onchange = function(){
    subjectGrade1 = document.getElementById('subjectGrade1').value;
    
    if (subjectGrade1 > 200 || subjectGrade1 < 100  || subjectGrade1%1 != 0){
        alert("Оцінка повинна бути цілим числом від 100 до 200!");
        document.getElementById('subjectGrade1').value = false;
        subjectGrade1 = false;
    }
}

document.getElementById('subjectGrade2').onchange = function(){
    subjectGrade2 = document.getElementById('subjectGrade2').value;
    
    if (subjectGrade2 > 200 || subjectGrade2 < 100  || subjectGrade2%1 != 0){
        alert("Оцінка повинна бути цілим числом від 100 до 200!");
        document.getElementById('subjectGrade2').value = false;
        subjectGrade2 = false;
    }
}

document.getElementById('subjectGrade3').onchange = function(){
    subjectGrade3 = document.getElementById('subjectGrade3').value;
    
    if (subjectGrade3 > 200 || subjectGrade3 < 100  || subjectGrade3%1 != 0){
        alert("Оцінка повинна бути цілим числом від 100 до 200!");
        document.getElementById('subjectGrade3').value = false;
        subjectGrade3 = false;
    }
}

document.getElementById('subjectGrade4').onchange = function(){
    subjectGrade4 = document.getElementById('subjectGrade4').value;
    
    if (subjectGrade4 > 200 || subjectGrade4 < 100  || subjectGrade4%1 != 0){
        alert("Оцінка повинна бути цілим числом від 100 до 200!");
        document.getElementById('subjectGrade4').value = false;
        subjectGrade4 = false;
    }
}

document.getElementById('subjectName2').onchange = function(){
    subjectName2 = document.getElementById('subjectName2').value;
    
    if (subjectName2 == subjectName3 || subjectName2 == subjectName4){
        alert("Не можна вибирати однакові предмети");
        document.getElementById('subjectName2').value = false;
        subjectName2 = false;
    }
}

document.getElementById('subjectName3').onchange = function(){
    subjectName3 = document.getElementById('subjectName3').value;
    
    if (subjectName2 == subjectName3 || subjectName3 == subjectName4){
        alert("Не можна вибирати однакові предмети");
        document.getElementById('subjectName3').value = false
        subjectName3 = false;
    }
}

document.getElementById('subjectName4').onchange = function(){
    subjectName4 = document.getElementById('subjectName4').value;
    
    if (subjectName4 == subjectName3 || subjectName2 == subjectName4){
        alert("Не можна вибирати однакові предмети");
        document.getElementById('subjectName4').value = false
        subjectName4 = false;
    }
}



document.body.onmousemove = function(){
    
    if (name && surname && email && password && date && schoolGPA && subjectGrade1 && subjectName2 && subjectGrade2 && subjectName3 && subjectGrade3){
        document.getElementById('btn').removeAttribute("disabled");
        document.getElementById('btn').style.color = "black";
    }else{
        document.getElementById('btn').setAttribute("disabled", "true");
        document.getElementById('btn').style.color = "grey";
    }
}