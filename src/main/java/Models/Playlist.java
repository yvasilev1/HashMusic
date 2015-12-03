/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Stores.PlayLists;
import Stores.Song;
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
    
    public void createPlayList(java.util.UUID userID, String playListName, java.util.UUID songID, String title, String artist, String album, String genre, String duration)
    {
        
         Session session = cluster.connect("HashMusic");
   
         Statement statement = QueryBuilder.insertInto("PlayList")
                .value("user_id", userID)
                .value("playlist_name", playListName)
                .value("song_id", songID)
                .value("artist", artist)
                .value("title", title)
                .value("album", album)
                .value("genre", genre)
                .value("duration", duration);
         session.execute(statement);
         session.close();
    }
    
    public java.util.HashSet<String> getPlayLists(java.util.UUID userID)
    {

        java.util.HashSet<String> playLists = new java.util.HashSet();
        
        Session session = cluster.connect("HashMusic");
       
        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "PlayList")
                .where(eq("user_id", userID));
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No playlists returned");
        } else {
            for (Row row : rs) {
                String playlistName = row.getString("playlist_name");
                playLists.add(playlistName);
            }
        }
        return playLists;
    }
    
    public java.util.LinkedList<Song> getSongs(java.util.UUID userID, String playlist, HashTags ht)
    {
        java.util.LinkedList<Song> songs = new java.util.LinkedList();
        
           Session session = cluster.connect("HashMusic");
         Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "PlayList")
                .where(eq("user_id", userID))
                .and(eq("playlist_name", playlist));
          ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return null;
        } else {
            for (Row row : rs) {
            //    System.out.println("Searched" + artistSearched);
               
                String hashtag = ht.getHashTag(userID, row.getUUID("song_id"));
                Song song = new Song();
                song.setSongDetails(row.getString("title"), row.getString("album"), row.getString("genre"), row.getString("artist"), row.getString("duration"), row.getUUID("song_id"), hashtag);   
                songs.add(song);
            //}
                    
            }
        }
        
        return songs;
    }
       
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}







