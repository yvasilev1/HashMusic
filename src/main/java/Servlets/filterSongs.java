/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.HashTags;
import Models.NewSong;
import Stores.Song;
import Stores.SongLibrary;
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
@WebServlet(name = "filterSongs", urlPatterns = {"/filterSongs"})
public class filterSongs extends HttpServlet {

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
        
        String filterType = request.getParameter("type");
        String filterValue = request.getParameter("id");
        HttpSession session = request.getSession();
        java.util.LinkedList<Song> songs = new java.util.LinkedList();
        
        java.util.UUID userID = (java.util.UUID)session.getAttribute("userID");
        
        NewSong newSong = new NewSong();
        newSong.setCluster(cluster); 
        
        HashTags ht = new HashTags();
        ht.setCluster(cluster);
        
        if(filterType.equals("artist"))
        {
            songs = newSong.filterByArtist(filterValue, userID, ht);
        }
        else if(filterType.equals("album"))
        {
            songs = newSong.filterByAlbum(filterValue, userID, ht);
        }
        else if(filterType.equals("genre"))
        {
           songs = newSong.filterByGenre(filterValue, userID, ht);
        }
        
         session.setAttribute("Songs", songs);
        
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
