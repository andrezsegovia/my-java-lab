$(document).ready(function(){
    
    $( "#form" ).submit(function(){
        
        $.get( "ServletClient", 
        {   "number1":$("input[name=number1]").val(),
            "number2": $("input[name=number2]").val()
        },
        function(data) {
            $("span").empty();
            $("span").append("El número mayor es: "+data.numberMajor);
        });
        
        return false;
        
    });
});


