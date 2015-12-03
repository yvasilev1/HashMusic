/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Playlist;
import Stores.PlayLists;
import Stores.Song;
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
@WebServlet(name = "PlayList", urlPatterns = {"/PlayList"})
public class PlayList extends HttpServlet {


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
        java.util.UUID userID = (java.util.UUID)session.getAttribute("userID");
        
        Playlist playlist = new Playlist();
        playlist.setCluster(cluster);
       
        java.util.LinkedList<PlayLists> playLists = new java.util.LinkedList();
        
        playLists = playlist.getPlayLists(userID);
        
        System.out.println("Size of playlists is..: " + playLists.size());
        
        session.setAttribute("playLists", playLists);
        
        RequestDispatcher rd = request.getRequestDispatcher("PlayLists.jsp");
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
        
        String playListName = request.getParameter("playlist");
        
        Playlist playlist = new Playlist();
        playlist.setCluster(cluster);
        
        HttpSession session = request.getSession();
        
        java.util.UUID userID = (java.util.UUID)session.getAttribute("userID");
        String[] songs = request.getParameterValues("song");
        
        
        java.util.LinkedList<Song> songList = (java.util.LinkedList<Song>)session.getAttribute("Songs");
        
        java.util.UUID songID;
        String title;
        String artist;
        String album;
        String genre;
        String duration;
        
        System.out.println("Song.. " + songList.get(0).getTitle());
        
        for(int i = 0; i < songList.size(); i++)
        {
            songID = songList.get(i).getSongID();
            title = songList.get(i).getTitle();
            artist = songList.get(i).getArtist();
            album = songList.get(i).getAlbum();
            genre = songList.get(i).getGenre();
            duration = songList.get(i).getDuration();
            
            playlist.createPlayList(userID, playListName, songID, title, artist, album, genre, duration);
        }
        
        
        
       // playlist.createPlayList(userID,playListName, songs);
        
       // RequestDispatcher rd = request.sgetRequestDispatcher("livefeed.jsp");
       // rd.forward(request, response);
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
