/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Stores.FollowerList;
import Stores.Song;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import java.util.*;

/**
 *
 * @author Connor
 */
public class Users {

    Cluster cluster;

    public Users() {
    }

    public boolean registerUser(UUID userId, String email, String username, String password) {

        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.insertInto("Users")
                .value("userid", userId)
                .value("username", username)
                .value("password", password)
                .value("email", email);
        session.execute(statement);
        session.close();
        return false;
    }
    
    public java.util.UUID userID(String username)
    {
        UUID userID = null;
        
        Session session = cluster.connect("HashMusic");
         
        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "users");
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No users returned");
            return null;
        } else {
            for (Row row : rs) {
              
                if (row.getString("username").equals(username))
                {
                    userID = row.getUUID("userid");
                }
            }
        }
    
        return userID;
    }

    public UUID isUserValid(String userName, String password) {

        Session session = cluster.connect("HashMusic");
        UUID storedUUID = null;
        String storedPass = null;
        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "users");
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No users returned");
            return null;
        } else {
            for (Row row : rs) {
                String username = row.getString("username");

                if (username.equals(userName)) {
                    storedPass = row.getString("password");
                    if (password.equals(storedPass)) {
                        storedUUID = row.getUUID("userid");
                        if (storedUUID != null) {
                            return storedUUID;
                        } else {
                            System.out.println("Wrong password!");
                            return null;

                        }

                    }

                }
            }
        }
        return storedUUID;
    }

    public void addFollower(UUID userUUID, UUID user1UUID, String user, String user1, Date dateFollowed) {

        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.insertInto("followers")
                .value("followerUser_ID", userUUID)
                .value("followingUser_ID", user1UUID)
                .value("follower_UName", user)
                .value("following_UName", user1)
                .value("date_followed", dateFollowed);
        session.execute(statement);
        session.close();

    }

    public boolean doesUserFollow(UUID user, UUID user1) {
        if (user1.equals(user)) {
            return true;
        }
        java.util.LinkedList<UUID> follows = new java.util.LinkedList<>();
        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.select("followingUser_ID")
                .from("HashMusic", "followers")
                .where(eq("followerUser_ID", user));
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No followers returned");
            return false;
        } else {
            for (Row row : rs) {

                follows.add(row.getUUID("followingUser_ID"));
            }
            for (int i = 0; i < follows.size(); i++) {
                System.out.println("looping");
                if (user1.compareTo(follows.get(i)) == 0) {
                    return true;
                }

            }
            return false;
        }

    }

    
    public java.util.LinkedList<String> getUnameFromUUID(java.util.LinkedList<UUID> userID) {
        java.util.LinkedList<String> usernames = new java.util.LinkedList<>();
        String username = "";
        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.select("username")
                .from("HashMusic", "users")
                .where(eq("userid", userID));
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No users returned");
            return null;
        } else {
            for (Row row : rs) {

            }
        }

        return usernames;
    }

    public java.util.LinkedList<String> getSearchedUname(String user) {
        java.util.LinkedList<String> seachedUName = new java.util.LinkedList<>();
        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "users");
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No users returned");
            return null;
        } else {
            for (Row row : rs) {
                String userName = row.getString("username");
                if (userName.toUpperCase().contains(user.toUpperCase()) || (user.toUpperCase().contains(userName.toUpperCase()))) {
                    seachedUName.add(userName);
                }

            }
        }

        return seachedUName;
    }

    public java.util.LinkedList<FollowerList> getFollowersDetails() {
        java.util.LinkedList<FollowerList> details = new java.util.LinkedList<>();

        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.select()
                .all()
                .from("HashMusic", "followers");
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No users returned");
            return null;
        } else {
            for (Row row : rs) {
                FollowerList fl = new FollowerList();
                UUID followingUserID = row.getUUID("followingUser_ID");
                UUID followerUserID = row.getUUID("followerUser_ID");
                String followerUName = row.getString("follower_UName");
                String followingUname = row.getString("following_UName");
                Date date_followed = row.getDate("date_followed");
                
                fl.setFollowerDetails(followerUserID, followingUserID, followerUName, followingUname, date_followed);
                details.add(fl);
            }
        }
        return details;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
