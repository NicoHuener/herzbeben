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
var points = 0;

function searchShootout() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("shootout_input");
    filter = input.value.toUpperCase();
    ul = document.getElementById("shootouttable");
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
    //hide the create shootout modal
    var modal1 = document.getElementById('myModal');
    modal1.style.display = "none";
    //hide the shootoutinfo modal
    var modal2 = document.getElementById('Infomodal');
    modal2.style.display = "none";


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
                       // alert(shootoutData[0].name);
                        var anzahlShootouts=(Object.keys(shootoutData).length);
                       // alert(anzahlShootouts);
                        var shootouttable = document.getElementById("shootouttable");
                        for (var i = 0; i < anzahlShootouts; i++){
                           var pid = shootoutData[i].person.id;
                           if (pid == undefined){
                               pid = shootoutData[i].person;
                           }
                          // alert(pid);
                           var shootoutid = shootoutData[i].id;
                           var category = shootoutData[i].category;
                        //  alert(category);
                           if (category == "US"){
                               category = "Usershootout";
                           }

                            shootouttable.innerHTML +=
                                "<tr>" +
                                "<li>" + "<a href ='#' id='"+shootoutData[i].id+"' onclick='starttheshow("+pid+","+shootoutid+")' />" + shootoutData[i].name + " - " + category.italics() + "</a>"+ "</li>" +
                                "</tr>";

                        }
                    }};
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

function createShootout(){
    var userID;
    userID = cookiewerteHolen();


    if (document.cookie) {
        var nameShootout=document.getElementById("nameShootout").value;
        var xmlhttp = new XMLHttpRequest();
        var m = document.getElementById('creatsocategory');
        var socategory  = m.options[m.selectedIndex].value;

        xmlhttp.open("POST","http://localhost:8080/meetme/api/shootout/"+nameShootout+"&"+userID+"&"+socategory);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        //sent the new HttpRequest
        xmlhttp.send();
        /*xmlhttp.send(JSON.stringify({
            "shootoutName": nameShootout,
            "userid": userID
        }));*/
        //modal.style.display="none";

    }

    else {
        window.location.replace("http://localhost:8080/index.html");
    }
    var modal = document.getElementById('myModal');
    modal.style.display="none";
    //createshootoutlist();
    window.location.replace("http://localhost:8080/compare.html");
    //nameShootout.innerHTML += ""; noch zeile schreiben, damit textbox leer wird

}
//--------------------------------------------------------
//alt="no picture uploaded"
var photos;
var shootoutID;

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

function starttheshow (personID, soID) {

    shootoutID = soID;
    var xmlsocat = new XMLHttpRequest();
    xmlsocat.open("GET", "http://localhost:8080/meetme/api/shootout/shootoutid/" + shootoutID);
    xmlsocat.send(null);

    xmlsocat.onreadystatechange = function () {
        if (xmlsocat.readyState == XMLHttpRequest.DONE) {
            var json = xmlsocat.responseText;
            var selectedshootout = JSON.parse(json);
            var shootoutcategory = selectedshootout[0].category;
            postpersonshootout(soID,shootoutcategory);

            //alert(shootoutcategory);
            if (shootoutcategory == "US") {
                //usershootout
                getuserpics(personID);
                //alert("getuserpics");
            }
            else {
                //shootout hat eine category
                getpicsfromcategory(shootoutcategory);
                //alert("getpicsfromshootout");

            }
        }
    };

}
function getpicsfromcategory(category) {
    //alert(category);
    var xmlhttpr = new XMLHttpRequest();
    xmlhttpr.open("GET", "http://localhost:8080/meetme/api/photo/photosfromcat/" + category);
    xmlhttpr.send(null);

    xmlhttpr.onreadystatechange = function () {
        if (xmlhttpr.readyState == XMLHttpRequest.DONE) {
            var json = xmlhttpr.responseText;
            photos = JSON.parse(json);
            photos = shuffle(photos);
            loadshootout();
        }
    };
}

function getuserpics(personID){
  //  alert(personID);
    var xmlhttpr = new XMLHttpRequest();
    xmlhttpr.open("GET", "http://localhost:8080/meetme/api/photo/" + personID);
    xmlhttpr.send(null);

    xmlhttpr.onreadystatechange = function () {
        if (xmlhttpr.readyState == XMLHttpRequest.DONE) {
            var json = xmlhttpr.responseText;
            photos = JSON.parse(json);
            photos = shuffle(photos);
            loadshootout();
        }
    };
}
            //var picamount = (Object.keys(photos).length);
            // alert (pictureNumber);
            // alert(photo[0].title);
function loadshootout () {
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
    // changepic1(); //set first Pic on page
    // changepic2(); //set first Pic on page




function updateclicks(picID,points) {
    var xmlhttpclick = new XMLHttpRequest();
    xmlhttpclick.open("PUT", "http://localhost:8080/meetme/api/photo/clicks/"+ points +"&"+ picID);
    xmlhttpclick.setRequestHeader("Content-Type", "application/json");
    //sent the new HttpRequest
    xmlhttpclick.send();
}

function updatewins(picID) {
    var xmlhttpwins = new XMLHttpRequest();
    xmlhttpwins.open("PUT", "http://localhost:8080/meetme/api/photo/wins/" + picID);
    xmlhttpwins.setRequestHeader("Content-Type", "application/json");
    //sent the new HttpRequest
    xmlhttpwins.send();
}
function postpersonshootout(shootoutID,shootoutcategory) {
    var userID = cookiewerteHolen();
    var datum = new Date();
    var heute = datum.getDate()+ "." + (datum.getMonth()+ 1)+"." + datum.getFullYear();
    console.log(heute +" "+ userID +" "+ shootoutcategory +" "+ shootoutID);
    var xmlhttpps = new XMLHttpRequest();
    xmlhttpps.open("POST", "http://localhost:8080/meetme/api/personshootout/"+userID+"&"+shootoutID+"&"+heute+"&"+shootoutcategory);
    xmlhttpps.setRequestHeader("Content-Type", "application/json");
    //sent the new HttpRequest
    xmlhttpps.send();
}
function updatepoints(picID,picpoints) {
    var xmlhttppoints = new XMLHttpRequest();
    xmlhttppoints.open("PUT", "http://localhost:8080/meetme/api/rank/points/" + picpoints +"&"+ shootoutID + "&" + picID);
    xmlhttppoints.setRequestHeader("Content-Type", "application/json");
    //sent the new HttpRequest
    xmlhttppoints.send();
}


function changepic1() { //bild 2 geclickt
    points++;
    console.log(points);
    var arraylength = (Object.keys(photos).length);
    if (arraylength !== 0) {
        document.getElementById("image1").src = photos[0].picture;
        document.getElementById("image1").title = photos[0].id;
        photos.shift();
        var pic2ID = document.getElementById("image2").title;

       // alert('photos left: ' + (Object.keys(photos).length + 1) + ' Anzahl points für pic2: ' + points);
       updatepoints(pic2ID,points);
        updateclicks(pic2ID,points);
    }
    else { //Keine Bilder mehr in Array!

       // alert('photos left: ' + (Object.keys(photos).length) + ' Anzahl points für pic2: ' + points);
        document.getElementById("image1").onclick = '';
        document.getElementById("image2").onclick = '';
      //  alert('no pics left!');

        //gewinner ist bild 2 --> Update wins
        var lastpic2ID = document.getElementById("image2").title;

        updatepoints(lastpic2ID,points);
        updatewins(lastpic2ID);
        updateclicks(lastpic2ID,points);
        showinfomodal();

    }
}



function changepic2() { //bild 1 geklickt
    points++;
    console.log(points);
    var arraylength = (Object.keys(photos).length);
    if (arraylength !== 0) {
        var lastpic = (Object.keys(photos).length) - 1;
        document.getElementById("image2").src = photos[lastpic].picture;
        document.getElementById("image2").title = photos[lastpic].id;
        photos.pop();
     //   alert('photos left: ' + (Object.keys(photos).length + 1) + ' Anzahl points für pic1: ' + points);

        var pic1ID = document.getElementById("image1").title;
        updatepoints(pic1ID,points);
        updateclicks(pic1ID,points);

    }
    else {  //Keine Bilder mehr in Array!
      //  alert('photos left: ' + (Object.keys(photos).length) + ' Anzahl points für pic1: ' + points);
        document.getElementById("image1").onclick = '';
        document.getElementById("image2").onclick = '';
      //  alert('no pics left!');
        //gewinner ist bild 1


        var lastpic1ID = document.getElementById("image1").title;
        updatepoints(lastpic1ID,points);
        updatewins(lastpic1ID);
        updateclicks(lastpic1ID,points);
        saveuserswin(lastpic1ID);
        showinfomodal();
    }
}
//-------------------------------------------------------------------
function saveuserswin(winpicID) {

    function updatehit(treffer,keinTreffer) {
        var personId = cookiewerteHolen();
        var xmlhttpupdate = new XMLHttpRequest();
        xmlhttpupdate.open("Get", "http://localhost:8080/meetme/api/person/"+personId+"&"+treffer+"&"+keinTreffer);
        xmlhttpupdate.setRequestHeader("Content-Type", "application/json");
        //sent the new HttpRequest
        xmlhttpupdate.send();
    }

    var sid = shootoutID;
   // alert('aktuelle shootoutid' + sid);
        var xmlhttpsowinner = new XMLHttpRequest();
    xmlhttpsowinner.open("Get", "http://localhost:8080/meetme/api/rank/winner/"+sid);
    xmlhttpsowinner.setRequestHeader("Content-Type", "application/json");
    //sent the new HttpRequest
    xmlhttpsowinner.send();
    xmlhttpsowinner.onreadystatechange = function () {
        if (xmlhttpsowinner.readyState == XMLHttpRequest.DONE) {
            var json = xmlhttpsowinner.responseText;
            var solist = JSON.parse(json);
            var sowinner = solist[0].photo.id;
            //alert(sowinner);
            if (sowinner == winpicID){
                //hit
                console.log('hit');
                updatehit(1,0);
            }
            else{
                //nohit
                console.log('nohit');
                updatehit(0,1);
            }

        }
    };
}
//-------------------------------------------------------------------
//Funktionen create Shootout Popup Fenster
function openModalcS() {
    var modal = document.getElementById('myModal');
    modal.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
function closemodcS() {
    var modal = document.getElementById('myModal');
    modal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    var modal = document.getElementById('myModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
};
//---------------------------------------------------
//Funktionen für popupInfo Modal Fenster
function showinfomodal() {
modalbody.innerHTML +=
"<br><br><a style='font-size: 20px;margin-left: 170px; color: #E9AD9C ' href=\"ShootoutRanking.html\?sid="+shootoutID+"\" >Show Picture ranking</a>";
    var modal2 = document.getElementById("Infomodal");
    modal2.style.display = "block";

}

function closeinfomodal() {
    var modal2 = document.getElementById("Infomodal");
    modal2.style.display = "none";
}
window.onclick = function(event) {
    var modal2 = document.getElementById("Infomodal");
    if (event.target == modal2) {
        modal2.style.display = "none";
    }
};









