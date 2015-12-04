/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Users;
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
        //UUID user1 = UUID.fromString("0b0b1e70-99d5-11e5-9962-4c72b9763a34"); 
        java.util.LinkedList<UUID> UUIDOfFollowing = us.getUserForFollower(user);
        java.util.LinkedList<UUID> followersUUID = us.getFollowerForUser(user);
        
        java.util.LinkedList<String> uNamesOfFollowing = new java.util.LinkedList<>();
        java.util.LinkedList<String> uNamesofFollowers = new java.util.LinkedList<>();
        
        for(int i = 0;i < UUIDOfFollowing.size(); i++){
     
          uNamesOfFollowing = us.getUnameFromUUID(UUIDOfFollowing.get(i));
           System.out.println(uNamesOfFollowing);
           
        }
        for(int i = 0;i < followersUUID.size(); i++){
     
          uNamesofFollowers = us.getUnameFromUUID(followersUUID.get(i));
           System.out.println(uNamesofFollowers);
         
        }
     
          session.setAttribute("Following", uNamesOfFollowing);
          session.setAttribute("Followers", uNamesofFollowers);
        
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
        UUID user = (UUID) session.getAttribute("userID");
        UUID user1 = (UUID) session.getAttribute("userID");
        System.out.println(user);
        Users us = new Users();
        us.setCluster(cluster);
        Date dateFollowed = new Date();
        System.out.println(dateFollowed);
        //UUID user1 = UUID.fromString("0b0b1e70-99d5-11e5-9962-4c72b9763a34"); 
        us.addFollower(user, user1, dateFollowed);

        response.sendRedirect("populateUserView");
    }

}
