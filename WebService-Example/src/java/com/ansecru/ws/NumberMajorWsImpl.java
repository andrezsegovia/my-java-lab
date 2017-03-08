/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ansecru.ws;

import javax.jws.WebService;

/**
 *
 * @author ansecru
 */
@WebService(endpointInterface = "com.ansecru.ws.NumberMajorWs", serviceName = "NumberMajorWs")
public class NumberMajorWsImpl implements NumberMajorWs{
    
    /**
     * <stong> Calcula el número mayor de dos número</strong>
     * 
     * <p> Compara el valor de dos números para determinar el mayor. Si son
     * iguales retorna el valor del primer número</p>
     * 
     * @param number1 primer número de tipo String
     * @param number2 segundo número de tipo String
     * @return String con el valor del número mayor
     */
    @Override
    public String calMajorNumberOfTwo(String number1, String number2){
        
        int num1 = Integer.parseInt(number1);
        int num2 = Integer.parseInt(number2);
        int majorNumber;
        
        if(num1>num2){
            majorNumber=num1;
        }else if(num1<num2){
            majorNumber=num2;
        }else{
            majorNumber=num1;
        }
        
        return String.valueOf(majorNumber);
    }
}
