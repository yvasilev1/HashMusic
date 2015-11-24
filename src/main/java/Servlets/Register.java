/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Users;
import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.HashMusic.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.HashMusic.lib.Convertors;

/**
 *
 * @author Connor
 */
@WebServlet(name = "Register", urlPatterns = {"Register"})
public class Register extends HttpServlet {
   Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
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
       
        HttpSession session = request.getSession();
        Convertors convertor = new Convertors();
        java.util.UUID userId = convertor.getTimeUUID();

        
        String email = request.getParameter("email");
        String username = request.getParameter("uname");
        String password = request.getParameter("password");
        
        Users us = new Users();
        us.setCluster(cluster);
        if(us.registerUser(userId,email, username, password))
        {
            boolean loggedIn = true;
            session.setAttribute("userStatus", loggedIn);
        }
        else
        {
            
        }
        
       RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
       rd.forward(request, response); 

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
