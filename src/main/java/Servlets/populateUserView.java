/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Feed;
import Models.HashTags;
import Models.NewSong;
import Models.Playlist;
import Models.Users;
import Stores.PostDetails;
import Stores.Song;
import Stores.SongLibrary;
import com.datastax.driver.core.Cluster;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
        String profileOf = request.getParameter("profileOf");
       
        Feed feed = new Feed();
        feed.setCluster(cluster);
        
               
        NewSong newSong = new NewSong();
        newSong.setCluster(cluster); 
        
        Playlist playlist = new Playlist();
        playlist.setCluster(cluster);
        
        HashTags ht = new HashTags();
        ht.setCluster(cluster);
        
        java.util.LinkedList<PostDetails> ps = null;
        
        System.out.println("Here is: " + session.getAttribute("user"));
        java.util.UUID selectedUser = (java.util.UUID)session.getAttribute("userID");
        ps = feed.getPostDetails(selectedUser);
        java.util.LinkedList<Song> songs = newSong.getUserSongs(selectedUser, ht);
        SongLibrary songLibrary = newSong.getUserSongCategories(selectedUser);
        java.util.HashSet<String> playLists = playlist.getPlayLists(selectedUser);
        
        if(profileOf!=null)
        {
            Users users = new Users();
            users.setCluster(cluster);
            selectedUser = users.userID(profileOf);
            System.out.println("SearchedProfileis...: " + selectedUser);
            ps = feed.getPostDetails(selectedUser);
            
        }

        String listType = "user";
     
        session.setAttribute("selectedUser", selectedUser);
        session.setAttribute("SongLibrary", songLibrary);
        session.setAttribute("playlists", playLists);
        session.setAttribute("listType", listType);
        session.setAttribute("Songs", songs);
        session.setAttribute("NewsFeed", ps);
        
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
