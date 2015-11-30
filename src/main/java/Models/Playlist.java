/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Stores.PlayLists;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import java.util.UUID;

/**
 *
 * @author Connor
 */
public class Playlist {
    Cluster cluster;
      
    public Playlist(){}
    
    public void createPlayList(java.util.UUID playListID, java.util.UUID userID, String playListName)
    {
        Session session = cluster.connect("HashMusic");

         Statement statement = QueryBuilder.insertInto("PlayList")
                .value("playlist_id", playListID)
                .value("user_id", userID)
                .value("playlist_name", playListName);
         session.execute(statement);
         session.close();
    }
    
    public java.util.LinkedList<PlayLists> getPlayLists(java.util.UUID userID)
    {
        java.util.LinkedList<PlayLists> playLists = new java.util.LinkedList();
        
        Session session = cluster.connect("HashMusic");
       
        Statement statement = QueryBuilder.select("playlist_id", "playlist_name")
                .from("HashMusic", "PlayList")
                .where(eq("user_id", userID));
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No playlists returned");
        } else {
            for (Row row : rs) {
                
                PlayLists playlists = new PlayLists();
                java.util.UUID playlistID = row.getUUID("playlist_id");
                String playlistName = row.getString("playlist_name");
                java.util.List<java.util.UUID> songs;
                
               // songs = row.getList("song_ids", java.util.UUID.class);
                
                playlists.setPlayList(playlistID, playlistName);
                playLists.add(playlists);
            }
        }
        
        return playLists;
        
    }
       
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}







