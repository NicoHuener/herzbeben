
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


    //AGB Modal
    var modal = document.getElementById('AGBModal');

    // Get the button that opens the modal
    var btn = document.getElementById("AGBcheckbox");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal
    btn.onclick = function openAGB() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    function registerUser() {

        // Get the checkbox
        var checkBox = document.getElementById("AGBcheckbox");


        if(checkBox.checked){

            alert('register started'); //debug purpose only

            // initalizing variables with textfields from html signup form
            var email = document.getElementById("mail").value;
            var lastname = document.getElementById("lastname").value;
            var firstname = document.getElementById("firstname").value;
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var confpw = document.getElementById("confpassword").value;

            function createuser() {


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

                }
                else {
                    alert('signin failed');
                }
            }


            function checkdataindb(email, username) {
                var xhr = new XMLHttpRequest();
                var json;
                var anzahluser;

                function createcookie() {
                    var xmlhttpcookie = new XMLHttpRequest(); // new HttpRequest instance

                    xmlhttpcookie.open("GET", "http://localhost:8080/meetme/api/person/username/" + username, true);
                    xmlhttpcookie.setRequestHeader("Content-Type", "application/json");
                    //sent the new HttpRequest
                    xmlhttpcookie.send(null);
                    xmlhttpcookie.onreadystatechange = function () {
                        if (xmlhttpcookie.readyState == XMLHttpRequest.DONE) {
                            var json = xmlhttpcookie.responseText;
                            var userdata = JSON.parse(json);
                            document.cookie = "id=" + userdata.id;
                        }
                    }

                }

                xhr.open('GET', 'http://localhost:8080/meetme/api/person', true);
                xhr.send(null);

                xhr.onreadystatechange = function () {
                    if (xhr.readyState == XMLHttpRequest.DONE) {
                        json = xhr.responseText;

                        var userlist = JSON.parse(json);
                        anzahluser = (Object.keys(userlist).length)

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
                            createcookie();

                            window.location.replace("http://localhost:8080/compare.html");
                        }
                    }
                }

            }

            checkdataindb(email, username);
        }


        else {
            alert('AGBs not checked');
            //text.style.display = "block";
            var popup = document.getElementById("myPopup");
            popup.classList.toggle("show");
        }
    }




