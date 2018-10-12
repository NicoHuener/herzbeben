
function createvalidpw (pw) {
    var bcrypt = require('bcryptjs')

    var salt = bcrypt.genSaltSync();
    var vaildpw = bcrypt.hashSync(pw, salt);
    alert("Password: " && vaildpw);
    return vaildpw;
}

function showCreateAccount(){

    var x = document.getElementById("signup");

    var z = document.getElementById("container");

    var a = document.getElementById("buttonCreate");

    var i = document.getElementById("buttonLogin");

    var h = document.getElementById("help");

    var r = document.getElementById("helpRe");


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

    /*Close Help Button*/
    if (h.style.display === "block"){

        h.style.display ="none";
    }
    else{
        h.style.display = "none";
    }

    /* Show HelpRe Button*/
    if(r.style.display === "none"){

          r.style.display = "block";

    }
    else{
          r.style.display = "block";
    }
}


function showLogin() {

    var y = document.getElementById("container");
    var u = document.getElementById("signup");
    var b = document.getElementById("buttonLogin");
    var j = document.getElementById("buttonCreate");
    var h = document.getElementById("help");
    var r = document.getElementById ("helpRe");



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

    /*Show Help Button*/
    if (h.style.display === "none"){

        h.style.display = "block";
    }
    else {
        h.style.display = "block";
    }

    /*Close HelpRe Button*/
    if (r.style.display === "block"){

         r.style.display = "none";
    }
    else {
         r.style.display = "none";
    }

}

function checkPassword(confpw,pw){
    if(confpw === pw){
    return true;
    }
    else{
        return false;
        alert("Passwords don't match");
    }


}