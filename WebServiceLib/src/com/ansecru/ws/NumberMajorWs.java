/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ansecru.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Interface del web service
 * 
 * Está inteface contiene los métodos que soporta el web service. Por medio de
 * está interface se crean las instancias del web services en cada cliente.
 * 
 * @author ansecru
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface NumberMajorWs {
    
    @WebMethod
    public String calMajorNumberOfTwo(String number1, String number2);
    
}
