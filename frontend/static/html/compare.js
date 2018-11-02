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