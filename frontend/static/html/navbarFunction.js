function dropdownMenu() {
    document.getElementById("dropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function (event) {
  if (!event.target.matches('.dropdown-toggle')) {

    var dropdowns = document.getElementsByClassName("dropdown-menu");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

    function searchUser() {

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
                var shootouttable = document.getElementById("shootoutlist");
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