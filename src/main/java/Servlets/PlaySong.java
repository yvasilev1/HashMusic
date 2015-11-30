/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.NewSong;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.utils.Bytes;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.net.Proxy.Type.HTTP;
import java.nio.ByteBuffer;
import static java.util.UUID.fromString;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.tools.ShellFunctions.input;
import uk.ac.dundee.computing.aec.HashMusic.lib.CassandraHosts;

/**
 *
 * @author Connor
 */
@WebServlet(name = "PlaySong", urlPatterns = {"/PlaySong"})
public class PlaySong extends HttpServlet {
    
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
    
        HttpSession session = request.getSession(true);
       
        java.util.UUID songID = (java.util.UUID) fromString(request.getParameter("id"));
        
        NewSong newSong = new NewSong();
        newSong.setCluster(cluster);
        
        ByteBuffer song = newSong.getSong(songID);
        
        byte[] songBytes = Bytes.getArray(song);
      
        outputSong(songBytes, response);
        
       // long length = songBytes.length;
       // String test = new String(songBytes);
       // System.out.println("Test... " + test);
       // System.out.println("Length of byteArray is.. " + length);
        //System.out.println("Song id is.. " + songID);
    }
    
    private void outputSong(byte[] songBytes, HttpServletResponse response) throws ServletException, IOException
    {  
          OutputStream songOut = response.getOutputStream();
          
          songOut.write(songBytes);
          songOut.close();
          songOut.flush();
        
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
