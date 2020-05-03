let email = false;
let password = false;


document.getElementById('email').onchange = function(){
    email = document.getElementById('email').value;
}

document.getElementById('password').onchange = function(){
    password = document.getElementById('password').value;
}

document.body.onmousemove = function(){
    
    if (email && password){
        document.getElementById('btn').removeAttribute("disabled");
        document.getElementById('btn').style.color = "black";
    }else{
        document.getElementById('btn').setAttribute("disabled", "true");
        document.getElementById('btn').style.color = "grey";
    }
}