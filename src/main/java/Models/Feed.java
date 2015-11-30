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
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Connor
 */
public class Feed {

    Cluster cluster;

    public Feed() {
    }

    //Inserting posts
    public void insertPost(java.util.UUID postID, java.util.UUID postedTo, java.util.UUID postedBy, Date datePosted, String postContent) {

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

    public java.util.LinkedList<String> getComments() {
        java.util.LinkedList<String> comments = new java.util.LinkedList<>();
        Session session = cluster.connect("yvinstagrim");

        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "posts");
           
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No users returned");
            return null;
        } else {
            for (Row row : rs) {

                comments.add(row.getString("content"));
            }
        }
        return comments;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
