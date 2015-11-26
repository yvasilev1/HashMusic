/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.util.UUID;

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
                    String storedPassword = row.getString("password");
                    if (password.equals(storedPassword)) {
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

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
