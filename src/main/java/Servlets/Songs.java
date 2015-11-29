/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.NewSong;
import Stores.Song;
import com.datastax.driver.core.Cluster;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import uk.ac.dundee.computing.aec.HashMusic.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.HashMusic.lib.Convertors;

/**
 *
 * @author Connor
 */
@WebServlet(urlPatterns = {"/Songs"})
@MultipartConfig

public class Songs extends HttpServlet {


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
        
        NewSong newSong = new NewSong();
        newSong.setCluster(cluster);
        
        java.util.LinkedList<Song> songs = newSong.getSongs();
        
        session.setAttribute("Songs", songs);
        
       // System.out.println(songs.size());
        
        RequestDispatcher rd = request.getRequestDispatcher("viewSongs.jsp");
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
        
        HttpSession session = request.getSession();
        Convertors convertor = new Convertors();
        java.util.UUID songId = convertor.getTimeUUID();
        
       // String title = request.getParameter("title");
       // String artist = request.getParameter("artist");
       // String genre = request.getParameter("genre");
       // String album = request.getParameter("album");
       // String duration = request.getParameter("duration");
        
       //int durationInt = Integer.parseInt(duration);

        
        for(Part part : request.getParts())
        {
            
            InputStream song = request.getPart(part.getName()).getInputStream();
            
            int i = song.available();
            
              if(i>0)
                {
                    byte[] songBytes = new byte[i+1];
                    song.read(songBytes);
                    
                    NewSong newSong = new NewSong();
                    newSong.setCluster(cluster);
                    newSong.insertSong(songId, songBytes);
                    
                 }
           
        }
        
      
        
        
        
       
        
        
        
    
        
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
