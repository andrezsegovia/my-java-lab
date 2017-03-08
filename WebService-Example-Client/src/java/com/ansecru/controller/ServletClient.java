/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ansecru.controller;

import com.ansecru.ws.NumberMajorWs;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import org.json.simple.JSONObject;

/**
 *
 * @author ansecru
 */
public class ServletClient extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     * 
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String num1 = request.getParameter("number1");
        String num2 = request.getParameter("number2");
        
        NumberMajorWs numberMajorWs = this.getWebService();
        String numberMajor = numberMajorWs.calMajorNumberOfTwo(num1, num2);
        JSONObject json = new JSONObject();
        json.put("numberMajor", numberMajor);

        response.setContentType("application/json");
        try(PrintWriter out = response.getWriter()){
            out.print(json.toString());
        }
        
    }
    
    /**
     * Crea una instancia del servicio web. 
     * 
     * Por medio de la interface com.ansecru.ws.NumberMajorWs que es el endpoit
     * del servicio web se crea una instancia del servicio web. 
     * 
     * @return Instancia del servicio web
     */         
    public NumberMajorWs getWebService(){
        
        NumberMajorWs numberMajorWs = null;
        Service service;
        
        try {
            
            URL url = new URL("http://localhost:8080/WebService-Example/NumberMajorWs?wsdl");
            QName qname = new QName("http://ws.ansecru.com/","NumberMajorWs");
            service = Service.create(qname).create(url,qname);
            numberMajorWs = service.getPort(NumberMajorWs.class);
            
        } catch (MalformedURLException ex) {
            
            ex.printStackTrace();
            
        } catch (WebServiceException wsex){
            
            wsex.printStackTrace();
        }
        
        return numberMajorWs;
    }       

}
