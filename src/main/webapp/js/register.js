let name = false;
let surname = false;
let email = false;
let password = false;
let date = false;
let schoolGPA = false;
let zno1grade = false;
let zno2 = "MATH";
let zno2grade = false;
let zno3 = "HISTORY";
let zno3grade = false;
let zno4 = false;
let zno4grade = false;



document.getElementById('name').onchange = function(){
    name = document.getElementById('name').value;
    console.log(name);
}

document.getElementById('surname').onchange = function(){
    surname = document.getElementById('surname').value;
    console.log(surname);
}

document.getElementById('dateOfBirth').onchange = function(){
    date = document.getElementById('dateOfBirth').value;
    console.log(date);
}

document.getElementById('email').onchange = function(){
    email = document.getElementById('email').value;
    console.log(email);
}

document.getElementById('password').onchange = function(){
    password = document.getElementById('password').value;
        console.log(password);
}

document.getElementById('schoolGPA').onchange = function(){
    schoolGPA = document.getElementById('schoolGPA').value;
    
    if (schoolGPA > 12 || schoolGPA < 1){
        alert("Середня оцінка не може бути менше 1 або більше 12!");
        schoolGPA = false;
    }
        console.log(schoolGPA);
}

document.getElementById('UKRAINIAN').onchange = function(){
    zno1grade = document.getElementById('UKRAINIAN').value;
    
    if (zno1grade > 200 || zno1grade < 100  || zno1grade%1 != 0){
        alert("Оцінка повинна бути цілим числом від 100 до 200!");
        zno1grade = false;
    }
        console.log(zno1grade);
}

document.getElementById('subject2grade').onchange = function(){
    zno2grade = document.getElementById('subject2grade').value;
    
    if (zno2grade > 200 || zno2grade < 100  || zno2grade%1 != 0){
        alert("Оцінка повинна бути цілим числом від 100 до 200!");
        zno2grade = false;
    }
        console.log(zno2grade);
}

document.getElementById('subject3grade').onchange = function(){
    zno3grade = document.getElementById('subject3grade').value;
    
    if (zno3grade > 200 || zno3grade < 100  || zno3grade%1 != 0){
        alert("Оцінка повинна бути цілим числом від 100 до 200!");
        zno3grade = false;
    }
        console.log(zno3grade);
}

document.getElementById('subject4grade').onchange = function(){
    zno4grade = document.getElementById('subject4grade').value;
    
    if (zno4grade > 200 || zno4grade < 100  || zno4grade%1 != 0){
        alert("Оцінка повинна бути цілим числом від 100 до 200!");
        zno4grade = false;
    }
}

document.getElementById('subject2').onchange = function(){
    subject2 = document.getElementById('subject2').value;
    
    if (subject2 == subject3 || subject2 == subject4){
        alert("Не можна вибирати однакові предмети");
        subject2 = false;
    }
        console.log(subject2);
}

document.getElementById('subject3').onchange = function(){
    subject3 = document.getElementById('subject3').value;
    
    if (subject2 == subject3 || subject3 == subject4){
        alert("Не можна вибирати однакові предмети");
        subject3 = false;
    }
    console.log(subject3);
}

document.getElementById('subject4').onchange = function(){
    subject4 = document.getElementById('subject4').value;
    
    if (subject4 == subject3 || subject2 == subject4){
        alert("Не можна вибирати однакові предмети");
        subject4 = false;
    }
    console.log(subject4);
}



document.body.onmousemove = function(){
    
    if (name && surname && email && password && date && schoolGPA && zno1grade && zno2 && zno2grade && zno3 && zno3grade){
        document.getElementById('btn').removeAttribute("disabled");
        document.getElementById('btn').style.backgroundColor = "forestgreen";
    }else{
        document.getElementById('btn').setAttribute("disabled", "true");
        document.getElementById('btn').style.backgroundColor = "lightgreen";
    }
}