<%-- 
    Document   : index
    Created on : Mar 7, 2017, 10:50:42 PM
    Author     : ansecru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculadora de Números Mayores</title>
    </head>
    <body>
        <h1>Calcula el número mayor</h1>
        
        <form id ="form">
            <label>
                Número 1
                <input type="number" name="number1">
            </label>
            <label>
                Número 2
                <input type="number" name="number2">
            </label>
            <input type="submit" value="Calcular" size="10">
        </form>
        
        <p>
            <span></span>
        </p>
        
        <style>
            *{
                font-size: 24px;
            }
            
            h1{
                font-size: 34px;
            }
            
            label{
                display: block;
                margin: 10px;
            }
            
            input[type="number"]{
                width:100px;
                margin-left: 15px;
            }
            input[type="submit"]{
                margin: 10px;
                margin-top: 50px;
                padding: 10px;
                font-size: 18px;
                
            }
            
        </style>
        <script type="text/javascript" src="lib/jquery-3.1.1.js"></script>
        <script type="text/javascript" src="js/javascript.js"></script>
    </body>
</html>
