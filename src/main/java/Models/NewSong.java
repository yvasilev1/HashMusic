/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Stores.Song;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 *
 * @author Connor
 */
public class NewSong {
    Cluster cluster;
    
    public NewSong(){}
    
    public void insertSong(java.util.UUID songID, byte[] songBytes)
    {
        ByteBuffer buffer = ByteBuffer.wrap(songBytes);
        
        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.insertInto("SongList")
                .value("song_id", songID)
                .value("song", buffer);
        session.execute(statement);
        session.close();
    }
    
    public java.util.LinkedList<Song> getSongs()
    {
        java.util.LinkedList<Song> songs = new java.util.LinkedList<Song>();
        
        Session session = cluster.connect("HashMusic");
        
        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "SongList");
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return songs;
        } else {
            for (Row row : rs) {
                
                Song song = new Song();
                
                String title = row.getString("title");
                String artist = row.getString("artist");
                String genre = row.getString("genre");
                String album = row.getString("album");
                int duration = row.getInt("duration");
                java.util.UUID songID = row.getUUID("song_id");
                
                song.setSongDetails(title, artist, genre, album, duration, songID);
                
                songs.add(song);
                
                System.out.println("Title is.. " + title);
            
            }
        }
        return songs;
    }
    
    public ByteBuffer getSong (java.util.UUID songID)
    {
        ByteBuffer songBytes = null;
        
            Session session = cluster.connect("HashMusic");
       
        Statement statement = QueryBuilder.select()
                .column("song")
                .from("HashMusic", "SongList")
                .where(eq("song_id", songID));
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return songBytes;
        } else {
            for (Row row : rs) {
                
                Song song = new Song();
                
                songBytes = row.getBytes("song");
            }
        }
      
        
        return songBytes;
    }
    
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
    
}
