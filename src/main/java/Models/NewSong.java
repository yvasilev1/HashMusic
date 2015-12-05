/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Stores.Song;
import Stores.SongLibrary;
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
    
    public void insertSong(java.util.UUID songID, byte[] songBytes,String title,String artist,String genre,String album, String duration)
    {
        ByteBuffer buffer = ByteBuffer.wrap(songBytes);
        
        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.insertInto("SongList")
                .value("song_id", songID)
                .value("song", buffer)
                .value("title", title)
                .value("artist", artist)
                .value("genre", genre)
                .value("album", album)
                .value("duration", duration);
        session.execute(statement);
        session.close();
    }
    
    public void insertUserSong(java.util.UUID userID, java.util.UUID songID, String artist, String album, String genre, String duration, String title)
    {
        Session session = cluster.connect("HashMusic");
        
        Statement statement = QueryBuilder.insertInto("UserSongs")
                .value("user_id", userID)
                .value("song_id", songID)
                .value("album", album)
                .value("genre", genre)
                .value("artist", artist)
                .value("duration", duration)
                .value("title", title);
        session.execute(statement);
        session.close();
    }
    
    public java.util.LinkedList<Song> getSongs(String songName)
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
                String duration = row.getString("duration");
                java.util.UUID songID = row.getUUID("song_id");
                
                if(title.contains(songName))
                {
                     song.setSongDetails(title, album, genre, artist, duration, songID, null);
                     songs.add(song);
                }
                
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
   
                songBytes = row.getBytes("song");
            }
        }
      
        
        return songBytes;
    }
    
    public SongLibrary getUserSongCategories (java.util.UUID userID)
    {
        SongLibrary songLibrary = new SongLibrary();
        Session session = cluster.connect("HashMusic");
         
        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "UserSongs")
                .where(eq("user_id", userID));
          ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return null;
        } else {
            for (Row row : rs) {
           
                
                java.util.UUID songID = row.getUUID("song_id");
                songLibrary = getSongsInfo(songLibrary, songID);
            }
        }
        
        
        return songLibrary;
    }
    
    public java.util.LinkedList<Song> getUserSongs (java.util.UUID userID, HashTags ht)
    {
        java.util.LinkedList<Song> songs = new java.util.LinkedList();
        
         Session session = cluster.connect("HashMusic");
         
         Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "UserSongs")
                .where(eq("user_id", userID));
          ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return null;
        } else {
            for (Row row : rs) {
                Song song = new Song();
                java.util.UUID songID = row.getUUID("song_id");
              
                String title = row.getString("title");
                String artist = row.getString("artist");
                String genre = row.getString("genre");
                String album = row.getString("album");
                String duration = row.getString("duration");
                
                String hashtag = ht.getHashTag(userID, songID);
         
                song.setSongDetails(title, album, genre, artist, duration, songID, hashtag);
                songs.add(song);
                
            }
        }
        
        return songs;
    }
   
  
    
    public SongLibrary getSongsInfo (SongLibrary songLibrary, java.util.UUID songID)
    {
      
        Session session = cluster.connect("HashMusic");
      
        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "SongList")
                .where(eq("song_id",songID));
                
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return null;
        } else {
            for (Row row : rs) {
               java.util.HashSet<String>artist = new java.util.HashSet();
               artist = songLibrary.getArtists();
               artist.add(row.getString("artist"));
               songLibrary.setArtists(artist);
               
               System.out.println("Artist is: " + row.getString("artist"));
              
               
               java.util.HashSet<String>albums = new java.util.HashSet();
               albums = songLibrary.getAlbums();
               albums.add(row.getString("album"));
               songLibrary.setAlbums(albums);
           
               System.out.println("Album is: " + row.getString("album"));
                
               java.util.HashSet<String>genres = new java.util.HashSet();
               genres = songLibrary.getGenres();
               genres.add(row.getString("genre"));
               songLibrary.setGenres(genres);
               
             
            }
        }
      
        
        return songLibrary;
    }
    
    public java.util.LinkedList<Song> filterByArtist (String artistSearched, java.util.UUID userID, HashTags ht)
    {
         java.util.LinkedList<Song> songs = new java.util.LinkedList();
    
         Session session = cluster.connect("HashMusic");
         Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "UserSongs")
                .where(eq("user_id", userID));
         
          ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return null;
        } else {
            for (Row row : rs) {
                System.out.println("Searched" + artistSearched);
                System.out.println("Row: " + row.getString("artist"));
            if(row.getString("artist").equals(artistSearched))
            {
                Song song = new Song();
                
                String hashtag = ht.getHashTag(userID, row.getUUID("song_id"));
         
                song.setSongDetails(row.getString("title"), row.getString("album"), row.getString("genre"), row.getString("artist"), row.getString("duration"), row.getUUID("song_id"), hashtag);
                songs.add(song);
            }
                    
            }
        }
        
        return songs;
    }
    
    
    
      
    public java.util.LinkedList<Song> filterByGenre (String genreSearched, java.util.UUID userID, HashTags ht)
    {
         java.util.LinkedList<Song> songs = new java.util.LinkedList();
    
         Session session = cluster.connect("HashMusic");
         Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "UserSongs")
                .where(eq("user_id", userID));
         
          ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return null;
        } else {
            for (Row row : rs) {
             //   System.out.println("Searched" + artistSearched);
                System.out.println("Row: " + row.getString("genre"));
            if(row.getString("genre").equals(genreSearched))
            {
                Song song = new Song();
                
                String hashtag = ht.getHashTag(userID, row.getUUID("song_id"));
         
                song.setSongDetails(row.getString("title"), row.getString("album"), row.getString("genre"), row.getString("artist"), row.getString("duration"), row.getUUID("song_id"), hashtag);
                songs.add(song);
            }
                    
            }
        }
        
        return songs;
    }
   
        
    public java.util.LinkedList<Song> filterByAlbum (String album, java.util.UUID userID, HashTags ht)
    {
         java.util.LinkedList<Song> songs = new java.util.LinkedList();
    
         Session session = cluster.connect("HashMusic");
         Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "UserSongs")
                .where(eq("user_id", userID));
         
          ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No songs returned");
            return null;
        } else {
            for (Row row : rs) {
             //   System.out.println("Searched" + artistSearched);
                System.out.println("Row: " + row.getString("genre"));
            if(row.getString("album").equals(album))
            {
                Song song = new Song();
             
                String hashtag = ht.getHashTag(userID, row.getUUID("song_id"));
                
                song.setSongDetails(row.getString("title"), row.getString("album"), row.getString("genre"), row.getString("artist"), row.getString("duration"), row.getUUID("song_id"), hashtag);
                songs.add(song);
            }
                    
            }
        }
        
        return songs;
    }

   
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
    
}
