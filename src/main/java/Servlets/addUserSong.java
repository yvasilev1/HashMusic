/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.NewSong;
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

/**
 *
 * @author Connor
 */
@WebServlet(name = "addUserSong", urlPatterns = {"/addUserSong"})
public class addUserSong extends HttpServlet {

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
       
        String songID = request.getParameter("id"); 
        java.util.UUID songUUID = java.util.UUID.fromString(songID);
        String artist = request.getParameter("artist");
        String album = request.getParameter("album");
        String genre = request.getParameter("genre");
        String duration = request.getParameter("duration");
        String title = request.getParameter("title");
      
        java.util.UUID userID = (java.util.UUID)session.getAttribute("userID");
        NewSong newSong = new NewSong();
        newSong.setCluster(cluster); 
        
        newSong.insertUserSong(userID, songUUID, artist, album, genre, duration, title);

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
