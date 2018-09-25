function showCreateAccount(){
    
    var x = document.getElementById("signup");
    
    var z = document.getElementById("container");
    
    if(x.style.display === "block"){
        
        x.style.display = "none";  
        
    }
    
   else{
        x.style.display = "block";
    }
    
    if(z.style.display === "block"){
        z.style.display = "none"
    }
   
    
}


function showLogin(){
    
    var y = document.getElementById("container");
    var u = document.getElementById("signup");
    
    if(y.style.display === "block"){
        
        y.style.display = "none";
        
        
    }
    else{
        y.style.display = "block";
    }
    
    if(u.style.display === "block"){
        u.style.display = "none"
    }
    
}