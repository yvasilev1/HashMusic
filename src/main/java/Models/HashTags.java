/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

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
    
      
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
    
}
