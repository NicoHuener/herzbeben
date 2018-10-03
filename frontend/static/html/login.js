function showCreateAccount(){

    var x = document.getElementById("signup");

    var z = document.getElementById("container");

    var a = document.getElementById("buttonCreate");

    var i = document.getElementById("buttonLogin");


    /*Open Form*/
    if(x.style.display === "block"){

        x.style.display = "none";

    }

    else{
        x.style.display = "block";
    }


    /*Close Login*/
    if(z.style.display === "block"){

        z.style.display = "none"
    }

    /*Close Create Button*/
    if(a.style.display === "block"){

        a.style.display = "none";

    }
    else{
        a.style.display = "none";
    }

    /*Show Login Button*/
    if(i.style.display === "none"){

        i.style.display = "block";

    }
    else{
        i.style.display = "block";
    }

}


function showLogin() {

    var y = document.getElementById("container");
    var u = document.getElementById("signup");
    var b = document.getElementById("buttonLogin");
    var j = document.getElementById("buttonCreate");


    /*Open Login Form*/
    if (y.style.display === "block") {

        y.style.display = "none";


    }
    else {
        y.style.display = "block";
    }

    /*Close Create Form*/
    if (u.style.display === "block") {
        u.style.display = "none"
    }


    /*Close Login Button*/
    if (b.style.display === "block") {

        b.style.display = "none";

    }
    else {
        b.style.display = "none";
    }


    /*Show Create Button*/
    if (j.style.display === "none") {

        j.style.display = "block";

    }
    else {
        j.style.display = "block";
    }

}

function checkPassword(){
    if("password-signup" === "confirm-password"){

    }
    else{
        alert("Passwords don't match");
    }
}