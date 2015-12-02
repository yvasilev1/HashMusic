/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Feed;
import Models.Users;
import com.datastax.driver.core.Cluster;
import static java.io.FileDescriptor.out;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.*;
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
@WebServlet(name = "PostWall", urlPatterns = {"/PostWall"})
public class PostWall extends HttpServlet {

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

        String comment = request.getParameter("postContent");
        //   out.println(comment);

        Date datePosted = new Date();
        //Need to get postedTo ID. Set as usersID for sake of it working. Will change it so it takes the ID of the user being viewed.
        HttpSession session = request.getSession(true);

        Convertors convertor = new Convertors();
        java.util.UUID postID = convertor.getTimeUUID();

        java.util.UUID postedBy = (java.util.UUID) session.getAttribute("userID");
        java.util.UUID postedTo = (java.util.UUID) session.getAttribute("userID");
        String postedByUname = (String)session.getAttribute("user");
                
        Feed feed = new Feed();
        feed.setCluster(cluster);

        if (!comment.equals("")) {
            feed.insertPost(postID, postedTo, postedBy,postedByUname, datePosted, comment);
        }

        java.util.LinkedList<String> comments = feed.getComments();  
        java.util.LinkedList<String> usernames= feed.getPostedByUname();
        java.util.LinkedList<Date> dates= feed.getDatePosted();
       
        session.setAttribute("Comments", comments);
        session.setAttribute("Users", usernames);
        session.setAttribute("Dates", dates);
   
            System.out.println(comments);
        System.out.println(usernames);
        System.out.println(dates);
        
        RequestDispatcher rd = request.getRequestDispatcher("livefeed.jsp");
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
