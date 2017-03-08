/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anscru.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author ansecru
 */

@WebService(serviceName = "NumeroMayorWs")
public class NumeroMayorWs {
    @WebMethod
    public String calculateNumberMajor(String number1, String number2){
        int num1 = Integer.parseInt(number1);
        int num2 = Integer.parseInt(number2);
        int numberMajor;
        
        if(num1>num2){
            numberMajor=num1;
        }else{
            numberMajor=num2;
        }
        
        return String.valueOf(numberMajor);
    }
}
