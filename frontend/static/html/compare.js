function getPictures(){
     var response;
     var pictureNumber;
     var xmlhttp = new XMLHttpRequest();
     xmlhttp.open("GET", "http://localhost:8080/meetme/api/shootout")
     response = xmlhttp.responseText;
     pictureNumber = response.length;
     alert(response);

     for (var i = 0; i < pictureNumber; i++){
     var pic1 = JSONGetElement(response;);
     }

}
getPictures();
function comparePicture(){
    var xmlhttp = new XMLHttpRequest();
}

function comparePicture2(){
    var xmlhttp = new XMLHttpRequest();
}