
/*function createvalidpw (pw) {
    var bcrypt = require('bcryptjs')

    var salt = bcrypt.genSaltSync();
    var vaildpw = bcrypt.hashSync(pw, salt);
    alert("Password: " && vaildpw);
    return vaildpw;
}*/

//Snackbar
function myFunction() {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar");

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 4000);
}

window.onload = function () {
    if (document.cookie) {
        window.location.replace("http://localhost:8080/compare.html");
    }
};


function checkNotEmpty(usernamel,passwordl) {
    if (usernamel === "" || usernamel === null) {
        alert('Please type in a Username!');
    }
    else if (passwordl === "" || passwordl === null) {
        alert('Please type in a Password!');

    }
    else if (document.cookie) {
        window.location.replace("http://localhost:8080/compare.html");
    }
    else {
        var xmlhttplog = new XMLHttpRequest(); // new HttpRequest instance
        xmlhttplog.open("GET", "http://localhost:8080/meetme/api/person/username/" + usernamel, true);
        xmlhttplog.setRequestHeader("Content-Type", "application/json");
        //sent the new HttpRequest
        xmlhttplog.send(null);
        alert('request für user gesendet');
        xmlhttplog.onreadystatechange = function () {
            if (xmlhttplog.readyState == XMLHttpRequest.DONE) {
                var json = xmlhttplog.responseText;
                var userdata = JSON.parse(json);
                //alert (userdata);
                var dbpassword = (userdata.password);
                var dbusername = (userdata.username);
                alert('db passwort = ' + dbpassword + '  db username = ' + dbusername);
                if (dbpassword === passwordl && dbusername === usernamel) {
                    if (usernamel == "admin"){
                        document.cookie = "id=" + userdata.id;
                        window.location.replace("http://localhost:8080/page.html");
                    }
                    else{
                    document.cookie = "id=" + userdata.id;
                    window.location.replace("http://localhost:8080/compare.html");
                    }
                }
                else {
                    alert ('wrong username or password!');
                }
            }
        }
    }
}
function signinUser(){
    //alert('signin started'); //debug purpose only

    //initalizing variables with textfields from html signin form

    var usernamel = document.getElementById("usernamelogin").value;
    var passwordl = document.getElementById("passwordlogin").value;
    //alert('username = ' + usernamel + 'passwort = ' + passwordl);
    //creates alert if no username or password is typed in

    checkNotEmpty(usernamel,passwordl);
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

    /* Show HelpRe Button
    if(r.style.display === "none"){

          r.style.display = "block";

    }
    else{
          r.style.display = "block";
    }*/
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

    /*Close HelpRe Button
    if (r.style.display === "block"){

         r.style.display = "none";
    }
    else {
         r.style.display = "none";
    }*/

}

function checkPassword(confpw,pw){
    if(confpw === pw){
    return true;
    }
    else{
        alert("Passwords don't match");
        return false;

    }
}

function checkRequired() {
     if(!this.div.checkbox.checked)
    {
        alert('You must agree to the terms first.');
        return false;
    }
}


    //AGB Modal --------------------------------------------------
    // When the user clicks on the button, open the modal
    function openAGB() {
        var modal = document.getElementById('AGBModal');
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    function closeAGB() {
        var modal = document.getElementById('AGBModal');
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        var modal = document.getElementById('AGBModal');
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
//-----------------------------------------------------------------------
    function registerUser() {

        // Get the checkbox
        var checkBox = document.getElementById("AGBcheckbox");


        if(checkBox.checked){

            //alert('register started'); //debug purpose only

            // initalizing variables with textfields from html signup form
            var email = document.getElementById("mail").value;
            var lastname = document.getElementById("lastname").value;
            var firstname = document.getElementById("firstname").value;
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var confpw = document.getElementById("confpassword").value;

            checkdataindb(email, username);

            function createuser() {
               // alert("createuserstarted");
                // Check if ConfirmationPW = PW
                if (checkPassword(confpw, password) === true) {
                    //create hashed PW
                    //var valpw = createvalidpw(password);

                    var xmlhttp = new XMLHttpRequest(); // new HttpRequest instance

                    xmlhttp.open("POST", "http://localhost:8080/meetme/api/person");
                    xmlhttp.setRequestHeader("Content-Type", "application/json");
                    //sent the new HttpRequest
                    xmlhttp.send(JSON.stringify({
                        "email": email,
                        "password": password,
                        "name": lastname,
                        "firstName": firstname,
                        "username": username
                    }));
                   /* xmlhttp.onreadystatechange = function () {
                        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                            setTimeout (createcookie() , 2000 );
                        }
                    };
                    /*else {
                    alert('signin failed');}*/
                }
            }

            /*function createcookie() {
                //alert("createcookiestarted");
                var xmlhttpcookie = new XMLHttpRequest(); // new HttpRequest instance

                xmlhttpcookie.open("GET", "http://localhost:8080/meetme/api/person/username/" + username, true);
                xmlhttpcookie.setRequestHeader("Content-Type", "application/json");
                //sent the new HttpRequest
                xmlhttpcookie.send(null);
                xmlhttpcookie.onreadystatechange = function () {
                    if (xmlhttpcookie.readyState == XMLHttpRequest.DONE) {
                        var json = xmlhttpcookie.responseText;
                        var userdata = JSON.parse(json);
                        //alert(userdata.id);
                        document.cookie = "id=" + userdata.id;
                    }
                }

            }*/

            function checkdataindb(email, username) {
              // alert("checkdataindbstarted");
                var xhr = new XMLHttpRequest();
                var json;
                var anzahluser;
                xhr.open('GET', 'http://localhost:8080/meetme/api/person', true);
                xhr.send(null);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == XMLHttpRequest.DONE) {
                        json = xhr.responseText;
                        var userlist = JSON.parse(json);
                        anzahluser = (Object.keys(userlist).length);

                        var useravailable = true;
                        for (var i = 0; i < anzahluser; i++) {
                            if (userlist[i].email === email) {
                                alert('There is already an account with this Email: ' + email);
                                //window.location.replace("http://localhost:8080/index.html");
                                return useravailable = false;
                            }
                            else if (userlist[i].username === username) {
                                alert('Username ' + username + ' is already taken!');
                                //window.location.replace("http://localhost:8080/index.html");
                                return useravailable = false;
                            }
                        }
                        if (useravailable === true) {
                            createuser();
                           /* window.location.replace("http://localhost:8080/index.html");*/
                        myFunction();



                           // window.location.replace("http://localhost:8080/compare.html");
                        }
                    }
                }

            }
        }


        else {
            alert('AGBs not checked');
            //text.style.display = "block";
            var popup = document.getElementById("myPopup");
            popup.classList.toggle("show");

        }
    }






