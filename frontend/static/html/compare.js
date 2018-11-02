//window.onload =  getPictures();
   /* var response;
    var pictureNumber;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "http://localhost:8080/meetme/api/shootout",true)
    xmlhttp.send(null);
    response = xmlhttp.responseText;
    var shootout = JSON.parse(response);
    alert(shootout);*/
/*
    function getPictures() {
        alert('getpictures started')
        var id = "34";
        var xmlhttpr = new XMLHttpRequest();
        xmlhttpr.open("GET", "http://localhost:8080/meetme/api/photo/" + id);
        xmlhttpr.send(null);
        var response2 = xmlhttpr.responseText;
        var photo = JSON.parse(response2);
        var pictureNumber = (Object.keys(photo).length);
        alert (pictureNumber);
    }



//window.onload(getPictures());
   /* var xmlhttpr = new XMLHttpRequest();
    xmlhttpr.open("GET", "http://localhost:8080/meetme/api/photo")
    var response2 = xmlhttp.responseText;
    var photo = JSON.parse(response2);
pictureNumber = (Object.keys(photo).length);

var pic1 = photo[1].picture;
     for (var i = 0; i < pictureNumber; i++){

     }

}
getPictures();
function comparePicture(){
    var xmlhttp = new XMLHttpRequest();
}

function comparePicture2(){
    var xmlhttp = new XMLHttpRequest();*/

//funktion Searchbar
function searchShootout() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("shootout_input");
    filter = input.value.toUpperCase();
    ul = document.getElementById("shootoutlist");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

//Ausgabe der Liste aller Shootouts in der Sidebar

window.onload = function () {

    if (document.cookie === ""){
        window.location.replace("http://localhost:8080/index.html");
    }

    //starttheshow();
    createshootoutlist();
};


function createshootoutlist() {

                var xmlhttpsidebar = new XMLHttpRequest ();
                xmlhttpsidebar.open("GET", 'http://localhost:8080/meetme/api/shootout/', true);
                xmlhttpsidebar.setRequestHeader("Content-Type", "application/json"); //?
                xmlhttpsidebar.send(null);
                xmlhttpsidebar.onreadystatechange = function (){
                    if(xmlhttpsidebar.readyState == XMLHttpRequest.DONE){

                        var json = xmlhttpsidebar.responseText;
                        var shootoutData = JSON.parse(json);
                        alert(shootoutData[0].name);
                        var anzahlShootouts=(Object.keys(shootoutData).length);
                        var shootouttable = document.getElementById("shootouttable"); // Zukünftig getElementById("shootoutlist")
                        for (var i = 0; i < anzahlShootouts; i++){
                           var pid = shootoutData[i].person;
                           var shootoutid = shootoutData[i].id;
                            shootouttable.innerHTML +=
                                "<tr>" +
                                "<li>" + "<a href ='#' id='"+shootoutData[i].id+"' onclick='starttheshow("+pid+","+shootoutid+")' />" + shootoutData[i].name + "</a>"+ "</li>" +
                                "</tr>";

                        }
                    }
                }
            }

function cookiewerteHolen () {
    var Wert = "";
    if (document.cookie) {
        var Wertstart = document.cookie.indexOf("=") + 1;
        var Wertende = document.cookie.indexOf(";");
        if (Wertende == -1) {
            Wertende = document.cookie.length;
        }
        Wert = document.cookie.substring(Wertstart, Wertende);
    }
    return Wert;
}
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("create_shootout");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
};
//var die beim klicken auf Btn "Create Shootout
var closeCS = document.getElementById("createShootout")[0];
closeCS.onclick= function(){
    modal.style.display="none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

function createShootout(){
    var userID;
    userID = cookiewerteHolen();


    if (document.cookie) {
        var nameShootout=document.getElementById("nameShootout").value;
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.open("POST","http://localhost:8080/meetme/api/shootout/"+nameShootout+"&"+userID);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        //sent the new HttpRequest
        xmlhttp.send(JSON.stringify({
            "shootoutName": nameShootout,
            "userid": userID
        }));
        //modal.style.display="none";

    }

    else {
        window.location.replace("http://localhost:8080/index.html");
    }
    modal.style.display="none";
    //nameShootout.innerHTML += ""; noch zeile schreiben, damit textbox leer wird

}
//popupInfo Modal Fenster
var modal2 = document.getElementById("pInfo");

var btn2 =document.getElementById("create_shootout");

var span2 = document.getElementsByClassName("close2")[0];

btn2.onclick = function() {
    modal2.style.display = "block";
};
function info() {
    modal2.style.display = "block";
}

span2.onclick = function() {
    modal2.style.display = "none";
};
window.onclick = function(event) {
    if (event.target == modal) {
        modal2.style.display = "none";
    }
};

//--------------------------------------------------------
//alt="no picture uploaded"
var photos;
function starttheshow (personID, shootoutID){
    // Fisher-Yates shuffle algorithm
    function shuffle(array) {
        var currentIndex = array.length, temporaryValue, randomIndex;

        // While there remain elements to shuffle...
        while (0 !== currentIndex) {

            // Pick a remaining element...
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;

            // And swap it with the current element.
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
        }
        return array;
    }

    /*var userID;
    userID = cookiewerteHolen();
    if (userID === "") {
        alert('undefined User!');
    }*/

    var xmlhttpr = new XMLHttpRequest();
    xmlhttpr.open("GET", "http://localhost:8080/meetme/api/photo/" + personID);
    xmlhttpr.send(null);

    xmlhttpr.onreadystatechange = function () {
        if (xmlhttpr.readyState == XMLHttpRequest.DONE) {
            var json = xmlhttpr.responseText;
            photos = JSON.parse(json);
            photos = shuffle(photos);
            //var picamount = (Object.keys(photos).length);
            // alert (pictureNumber);
            // alert(photo[0].title);

            // ------ load fist pic for image1 ---------------------
            document.getElementById("image1").src = photos[0].picture;
            document.getElementById("image1").title = photos[0].id;
            photos.shift();
            // ------ load fist pic for image2 --------------------
            var lastpic = (Object.keys(photos).length) - 1;
            document.getElementById("image2").src = photos[lastpic].picture;
            document.getElementById("image2").title = photos[lastpic].id;
            photos.pop();
        }
    };
    // changepic1(); //set first Pic on page
    // changepic2(); //set first Pic on page
}

var clicks = 0;

function changepic1() { //bild 2 geclickt
    clicks++;
    alert (Object.keys(photos).length);
    var arraylength = (Object.keys(photos).length);
    if (arraylength !== 0) {
        document.getElementById("image1").src = photos[0].picture;
        document.getElementById("image1").title = photos[0].id;
        photos.shift();
        var pic2ID = document.getElementById("image2").title;

        alert('photos left: ' + (Object.keys(photos).length + 1) + ' Anzahl clicks für pic2: ' + clicks);
        var xmlhttppoints = new XMLHttpRequest();
        /* xmlhttppoints.open("PUT", "http://localhost:8080/meetme/api/rank/points/" + clicks +"&"+ shootoutID + "&" + pic2ID);
         xmlhttppoints.setRequestHeader("Content-Type", "application/json");
         //sent the new HttpRequest
         xmlhttppoints.send();*/
    }
    else { //Keine Bilder mehr in Array!
        info();
        alert('photos left: ' + (Object.keys(photos).length) + ' Anzahl clicks für pic2: ' + clicks);
        document.getElementById("image1").onclick = '';
        document.getElementById("image2").onclick = '';
        alert('no pics left!');
        //gewinner ist bild 2

        var picID = document.getElementById("image2").title;
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("PUT", "http://localhost:8080/meetme/api/photo/wins/" + picID);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        //sent the new HttpRequest
        xmlhttp.send();

    }
}

function changepic2() { //bild 1 geklickt
    clicks++;
    var arraylength = (Object.keys(photos).length);
    if (arraylength !== 0) {
        var lastpic = (Object.keys(photos).length) - 1;
        document.getElementById("image2").src = photos[lastpic].picture;
        document.getElementById("image2").title = photos[lastpic].id;
        photos.pop();
        alert('photos left: ' + (Object.keys(photos).length + 1) + ' Anzahl clicks für pic1: ' + clicks);
    }
    else {  //Keine Bilder mehr in Array!
        info();
        alert('photos left: ' + (Object.keys(photos).length) + ' Anzahl clicks für pic1: ' + clicks);
        document.getElementById("image1").onclick = '';
        document.getElementById("image2").onclick = '';
        alert('no pics left!');
        //gewinner ist bild 1


        var photoid = document.getElementById("image1").title;
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("PUT", "http://localhost:8080/meetme/api/photo/wins/" + photoid);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        //sent the new HttpRequest
        xmlhttp.send();

    }
}




//---------------------------------------------------












