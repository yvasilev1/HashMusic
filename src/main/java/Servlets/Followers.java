/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Users;
import Stores.FollowerList;
import com.datastax.driver.core.Cluster;
import java.io.IOException;
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

/**
 *
 * @author Yulian
 */
@WebServlet(name = "Followers", urlPatterns = {"/Followers"})
public class Followers extends HttpServlet {

    Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

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

        HttpSession session = request.getSession();

        Users us = new Users();
        us.setCluster(cluster);
        UUID user = (UUID) session.getAttribute("userID");
        
        java.util.LinkedList<FollowerList> fl= us.getFollowersDetails();
              
        System.out.println(fl.get(0).getFollowerUName());
        System.out.println(fl.get(0).getFollowingUName());
        session.setAttribute("Followers", fl);
        
     
        RequestDispatcher rd = request.getRequestDispatcher("viewFollowers.jsp");
        rd.forward(request, response);

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

        HttpSession session = request.getSession(true);
        String user = request.getParameter("user");
        String user1 = request.getParameter("user1");
        
        UUID userUUID = (UUID) session.getAttribute("userID");
        UUID user1UUID = (UUID) session.getAttribute("userID");
        System.out.println(user);
        Users us = new Users();
        us.setCluster(cluster);
        Date dateFollowed = new Date();
        System.out.println(dateFollowed);
        //UUID user1 = UUID.fromString("0b0b1e70-99d5-11e5-9962-4c72b9763a34"); 
        us.addFollower(userUUID, user1UUID,user, user1, dateFollowed);

        response.sendRedirect("populateUserView");
    }

}
