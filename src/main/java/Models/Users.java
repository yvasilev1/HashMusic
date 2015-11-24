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
    
    public Users(){}
    
    Cluster cluster;
    

   
 public boolean registerUser(UUID userId,String email, String username, String password)
 {
     //java.util.UUID userId = dsadasdad;
     
     Session session = cluster.connect("HashMusic");
     
     
     
     Statement statement = QueryBuilder.insertInto("Users")
               .value("userid", userId)
               .value("username", username)
               .value("password", password);
     session.execute(statement);
     session.close();
     
     
     
     
     return false;
 }
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }        
            
}
