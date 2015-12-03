/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Connor
 */
public class HashTags {
    Cluster cluster;
    
    public HashTags(){}
    
    public void insertHash(String hashTag, java.util.UUID userID, java.util.UUID songID)
    {
        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.insertInto("HashList")
                .value("user_id", userID)
                .value("hashTag", hashTag)
                .value("song_id", songID);
        session.execute(statement);
        session.close();
    }
    
    public java.util.UUID getHashSongID(String hashTag, java.util.UUID userID)
    {
         java.util.UUID songID = null;
        
         Session session = cluster.connect("HashMusic");
         
         Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "HashList")
                .where(eq("user_id", userID));
          ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No Hashes returned");
            return null;
        } else {
            for (Row row : rs) 
            {
                if(row.getString("hashTag").equals(hashTag))
                {
                   songID = row.getUUID("song_id");  
                }
           }
        }
        return songID;
    
    }
    
     public String getHashTag(java.util.UUID userID, java.util.UUID songID)
    {
        String hashtag = null;
        
        Session session = cluster.connect("HashMusic");
         
         Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "HashList")
                .where(eq("user_id", userID));
          ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No Hashes returned");
            return null;
        } else {
            for (Row row : rs) 
            {
                if(row.getUUID("song_id").equals(songID))
                {
                   hashtag = row.getString("hashTag");    
                }
            }
        
        return hashtag;
    }
    }
    
      
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
    
}
