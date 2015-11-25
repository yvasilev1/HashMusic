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
       
       public void insertPost(String comment, String user, java.util.UUID userUUID, Date datePosted, String postedTo)
       {
           Date date = new Date();
           
           Session session = cluster.connect("HashMusic");

       //  Statement statement = QueryBuilder.insertInto("Posts")
       //         .value("post_id", postId)
       //         .value("user_id", userId)
       //         .value("content", content)
       //         .value("date_posted", datePosted)
       //         .value("receiver_id", receiverId);
       //  session.execute(statement);
       //  session.close();
       }
}


