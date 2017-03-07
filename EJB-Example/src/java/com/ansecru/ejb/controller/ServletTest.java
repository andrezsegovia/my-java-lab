/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ansecru.ejb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ansecru
 */
import com.ansecru.ejb.model.entity.Users;
import com.ansecru.ejb.model.session.SessionBean;
import com.ansecru.ejb.model.session.SessionBeanRemote;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class ServletTest extends HttpServlet {

    @EJB
    private SessionBeanRemote session;
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Into the doGet Metho");
        
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder json = new StringBuilder();
        
        
        List<Users> usersList = session.getUsers();
        
        for(Users user: usersList){
             json.append(mapper.writeValueAsString(user));
        }

        response.setContentType("application/json");
         try(PrintWriter out = response.getWriter()){
             out.print(json.toString());
         }
         
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.print("Hello form doPost");
        response.setContentType("application/json");
        try(PrintWriter out = response.getWriter()){
            out.print("{\"message\":\"Hello World\"}");
        }
        
    }
}
