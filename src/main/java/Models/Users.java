/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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

    public void addFollower(UUID user, UUID user1, Date dateFollowed) {

        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.insertInto("followers")
                .value("followerUser_ID", user)
                .value("followingUser_ID", user1)
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

    public java.util.LinkedList<UUID> getUserForFollower(UUID user) {
        java.util.LinkedList<UUID> usersForFollowers = new java.util.LinkedList<>();
        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.select("followingUser_ID")
                .from("HashMusic", "followers")
                .allowFiltering()
                .where(eq("followerUser_ID", user));
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No followers returned");
            return null;
        } else {
            for (Row row : rs) {

                usersForFollowers.add(row.getUUID("followingUser_ID"));
            }
        }

        return usersForFollowers;
    }

    public java.util.LinkedList<UUID> getFollowerForUser(UUID user1) {
        java.util.LinkedList<UUID> followerForUser = new java.util.LinkedList<>();
        Session session = cluster.connect("HashMusic");

        Statement statement = QueryBuilder.select("followerUser_ID")
                .from("HashMusic", "followers")
                .allowFiltering()
                .where(eq("followingUser_ID", user1));
        ResultSet rs = session.execute(statement);
        if (rs.isExhausted()) {
            System.out.println("No followers returned");
            return null;
        } else {
            for (Row row : rs) {

                followerForUser.add(row.getUUID("followerUser_ID"));
            }
        }

        return followerForUser;
    }

    public java.util.LinkedList<String> getUnameFromUUID(UUID userID) {
        java.util.LinkedList<String> usernames = new java.util.LinkedList<>();
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

                usernames.add(row.getString("username"));
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
                if (user.equals(userName)) {
                    seachedUName.add(row.getString("username"));
                    System.out.println("User found");
                } else {
                    System.out.println("User not found");
                }

            }
        }

        return seachedUName;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
