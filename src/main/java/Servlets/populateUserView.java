/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Feed;
import Models.NewSong;
import Stores.Song;
import Stores.SongLibrary;
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

/**
 *
 * @author Connor
 */
@WebServlet(name = "populateUserView", urlPatterns = {"/populateUserView"})
public class populateUserView extends HttpServlet {
      Cluster cluster = null;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
       
        Feed feed = new Feed();
        feed.setCluster(cluster);
       
        java.util.LinkedList<String> comments = feed.getComments();  
        java.util.LinkedList<String> usernames= feed.getPostedByUname();
        java.util.LinkedList<Date> dates= feed.getDatePosted();
       
        session.setAttribute("NewsFeed", comments);
        session.setAttribute("Users", usernames);
        session.setAttribute("Dates", dates);
        
        NewSong newSong = new NewSong();
        newSong.setCluster(cluster); 
        
        java.util.UUID userID = (java.util.UUID)session.getAttribute("userID");
       
        SongLibrary songLibrary = newSong.getUserSongCategories(userID);
        
        java.util.LinkedList<Song> songs = newSong.getUserSongs(userID);

        session.setAttribute("Songs", songs);
        
        //System.out.println("Song ID is.. " + songs.get(0).getSongID());
        //System.out.println("Size is: " + songs.size());
        
        String listType = "user";

        session.setAttribute("SongLibrary", songLibrary);
        session.setAttribute("listType", listType);
       
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
