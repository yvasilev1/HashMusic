/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.Date;


/**
 *
 * @author Connor
 */
public class Feed {
       Cluster cluster;
       
       public Feed(){}
       
       //Inserting posts
       public void insertPost(java.util.UUID postID, java.util.UUID postedTo, java.util.UUID postedBy, Date datePosted, String postContent)
       {
     
        Session session = cluster.connect("HashMusic");

         Statement statement = QueryBuilder.insertInto("Posts")
                .value("post_id", postID)
                .value("postedTo_id", postedTo)
                .value("postedBy_id", postedBy)
                .value("date_posted", datePosted)
                .value("content", postContent);
         session.execute(statement);
         session.close();
       }
       
          public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}


