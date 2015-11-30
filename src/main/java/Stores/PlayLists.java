/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stores;

/**
 *
 * @author Connor
 */
public class PlayLists {
    
    public PlayLists(){}
    
    java.util.UUID playListID = null;
    String playListName = null;
    
    public void setPlayList(java.util.UUID playListID, String playListName)
    {
        this.playListID = playListID;
        this.playListName = playListName;
    }
    
    public java.util.UUID getPlayListID()
    {
        return playListID;
    }
    
    public String getPlayListName()
    {
        return playListName;
    }
}
