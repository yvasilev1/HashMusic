/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stores;

import static com.datastax.driver.core.DataType.blob;

/**
 *
 * @author Connor
 */
public class Song {
    
    public Song(){}
    
    String title = null;
    String album = null;
    String genre = null;
    String artist = null;
    int duration = 0;
    java.util.UUID songID = null;
    
    
    public void setSongDetails(String title, String album, String genre, String artist, int duration, java.util.UUID songID)
    {
        this.title = title;
        this.album = album;
        this.genre = genre;
        this.artist = artist;
        this.duration = duration;
        this.songID = songID;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getAlbum()
    {
        return album;
    }
    
    public String getGenre()
    {
        return genre;
    }
    
    public String getArtist()
    {
        return artist;
    }
    
    public int getDuration()
    {
        return duration;
    }
    
    public java.util.UUID getSongID()
    {
        return songID;
    }
    
    
    
}
