/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Feed;
import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
        
        PrintWriter out = response.getWriter();
        String comment = request.getParameter("postContent");
     //   out.println(comment);
        
        Date datePosted = new Date();
        //Need to get postedTo ID. Set as usersID for sake of it working. Will change it so it takes the ID of the user being viewed.
        HttpSession session = request.getSession(true);
        
        Convertors convertor = new Convertors();
        java.util.UUID postID = convertor.getTimeUUID();
        
        java.util.UUID postedBy = (java.util.UUID)session.getAttribute("userID");
        java.util.UUID postedTo = (java.util.UUID)session.getAttribute("userID");
        
        Feed feed = new Feed();
        feed.setCluster(cluster);
        
        if(!comment.equals(""))
        {
             feed.insertPost(postID, postedTo, postedBy, datePosted, comment);
        }
     
         java.util.LinkedList<String> comments = feed.getComments();
        String printComment="";
          for (int i = 0; i < comments.size(); i++) {
              printComment=printComment+"<br>"+comments.get(i);
            //out.println("<br>: " + comments.get(i));
        }
          out.println(printComment);
      
        out.close();
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
