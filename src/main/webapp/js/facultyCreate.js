let name = false;
let places = false;
let description = false;

let subjectCoef1 = false;
let subjectName2 = "MATH";
let subjectCoef2 = false;
let subjectName3 = "HISTORY";
let subjectCoef3 = false;
let subjectName4 = false;
let subjectCoef3 = false;



document.getElementById('name').onchange = function(){
    name = document.getElementById('name').value;
}

document.getElementById('places').onchange = function(){
    surname = document.getElementById('places').value;
}

document.getElementById('description').onchange = function(){
    date = document.getElementById('description').value;
}

document.getElementById('subjectCoef1').onchange = function(){
    subjectCoef1 = document.getElementById('subjectCoef1').value;

    if (subjectCoef1 > 0.8 || subjectCoef1 < 0.05  || subjectCoef1%0.05 != 0){
        alert("Коефіціент повинен бути числом від 0.05 до 0.8");
        document.getElementById('subjectCoef1').value = false;
        subjectCoef1 = false;
    }
}

document.getElementById('subjectCoef2').onchange = function(){
    subjectCoef2 = document.getElementById('subjectCoef2').value;

    if (subjectCoef2 > 0.8 || subjectCoef2 < 0.05  || subjectCoef2%0.05 != 0){
        alert("Коефіціент повинен бути числом від 0.05 до 0.8");
        document.getElementById('subjectCoef2').value = false;
        subjectCoef2 = false;
    }
}

document.getElementById('subjectCoef3').onchange = function(){
    subjectCoef3 = document.getElementById('subjectCoef3').value;

    if (subjectCoef3 > 0.8 || subjectCoef3 < 0.05  || subjectCoef3%0.05 != 0){
        alert("Коефіціент повинен бути числом від 0.05 до 0.8");
        document.getElementById('subjectCoef3').value = false;
        subjectCoef3 = false;
    }
}

document.getElementById('subjectCoef4').onchange = function(){
    subjectCoef4 = document.getElementById('subjectCoef4').value;

    if (subjectCoef4 > 0.8 || subjectCoef4 < 0.05  || subjectCoef4%0.05 != 0){
        alert("Коефіціент повинен бути числом від 0.05 до 0.8");
        document.getElementById('subjectCoef4').value = false;
        subjectCoef4 = false;
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
    if (name && places && description && subjectCoef1 && subjectName2 && subjectCoef2 && subjectName3 && subjectCoef3){
        if(subjectName3 == null || subjectCoef3 == null){
            if((subjectCoef1 + subjectCoef2 + subjectCoef3) != 0.9){
                alert("Сумма коефіціентів повинна бути рівна 0.9!");
                document.getElementById('subjectCoef1').value = false;
                subjectCoef1 = false;
            }else{
                document.getElementById('btn').removeAttribute("disabled");
                document.getElementById('btn').style.color = "black";
            }
        }else{
            if((subjectCoef1 + subjectCoef2 + subjectCoef3 + subjectCoef4) != 0.9){
                alert("Сумма коефіціентів повинна бути рівна 0.9!");
                document.getElementById('subjectCoef1').value = false;
                subjectCoef1 = false;
            }else{
                document.getElementById('btn').removeAttribute("disabled");
                document.getElementById('btn').style.color = "black";
            }
        }
    }else{
        document.getElementById('btn').setAttribute("disabled", "true");
        document.getElementById('btn').style.color = "grey";
    }
}