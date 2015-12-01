/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stores;

import java.util.*;

/**
 *
 * @author Yulian
 */
public class SongLibrary {

    public SongLibrary() {
    }

    java.util.HashSet<String> artist = new java.util.HashSet<>();
    java.util.HashSet<String> albums = new java.util.HashSet<>();
    java.util.HashSet<String> genres = new java.util.HashSet<>();

    public void setArtists(java.util.HashSet<String> artist) {
        this.artist = artist;
    }

    public void setAlbums(java.util.HashSet<String> albums) {
        this.albums = albums;
    }

    public void setGenres(java.util.HashSet<String> genres) {
        this.genres = genres;
    }

    public java.util.HashSet<String> getArtists() {
        return artist;
    }

    public java.util.HashSet<String> getAlbums() {
        return albums;
    }

    public java.util.HashSet<String> getGenres() {
        return genres;
    }

}
