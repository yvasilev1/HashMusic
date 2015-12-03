/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Users;
import com.datastax.driver.core.Cluster;
import java.io.BufferedReader;
import java.io.FileReader;
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
import java.util.*;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author Yulian
 */
@WebServlet(name = "Login", urlPatterns = {
    "/Login",
   
    
})
@MultipartConfig
public class Login extends HttpServlet {

    Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Users us = new Users();
        us.setCluster(cluster);

        UUID isUserValid = us.isUserValid(username, password);
        System.out.println(isUserValid);
        if (isUserValid != null) {

            HttpSession session = request.getSession(true);
            boolean loggedIn = true;
            session.setAttribute("userStatus", loggedIn);
            session.setAttribute("userID", isUserValid);
            session.setAttribute("user", username);
            System.out.println("Success");
            response.sendRedirect("populateUserView");

        } else {
            System.out.println("Wrong Password");
            response.sendRedirect("/HashMusic/Login.jsp");
        }

    }

}
