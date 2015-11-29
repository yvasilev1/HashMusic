package uk.ac.dundee.computing.aec.HashMusic.lib;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.*;

public final class Keyspaces {

    public Keyspaces() {

    }

    public static void SetUpKeySpaces(Cluster c) {
        try {
            String createkeyspace = "create keyspace if not exists HashMusic WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";

            String CreateSongList = "CREATE TABLE if not exists HashMusic.SongList ("
                    + "song_id uuid,"
                    + "title text, "
                    + "duration timestamp, "
                    + "artist text, "
                    + "album text, "
                    + "genre text, "
                    + "song blob, "
                    + " PRIMARY KEY (song_id)"
                    + ")";

            String CreateUserList = "CREATE TABLE if not exists HashMusic.Users (\n"
                    + "userid uuid, "
                    + "username text, "
                    + "password text, "
                    + "email text, "
                    + "PRIMARY KEY (userid)"
                    + ")";

            String CreatePlayList = "CREATE TABLE if not exists HashMusic.PlayList (\n"
                    + "playList uuid, "
                    + "song_id uuid, "
                    + "username text, "
                    + "PRIMARY KEY (playList)"
                    + ")";
            
            String CreatePostsTable = "CREATE TABLE if not exists HashMusic.Posts (\n"
                   + "post_id uuid, "
                   + "postedTo_id uuid, "
                   + "postedBy_id uuid, "
                   + "date_posted timestamp, "
                   + "content text, "
                   + "PRIMARY KEY (post_id, date_posted)\n"
                   + ") WITH CLUSTERING ORDER BY (date_posted desc);";

            String CreateHashList = "CREATE TABLE if not exists HashMusic.HashList (\n"
                    + "user_id uuid, "
                    + "hash text, "
                    + "song_id uuid, "
                    + "playList_id uuid, "
                    + "PRIMARY KEY (user_id, hash)"
                    + ")";
         String CreateFollowerList = "CREATE TABLE if not exists HashMusic.followers (\n"
                    + " followerUser_ID uuid, "
                    + " followingUser_ID uuid, "
                    + "date_followed timestamp,"
                    + "PRIMARY KEY (followerUser_ID, followingUser_ID, date_followed)\n"
                    + ") WITH CLUSTERING ORDER BY (followingUser_ID asc, date_followed desc);";

            String CreateSecondaryIndex = "CREATE INDEX user ON HashMusic.PlayList (username);";

            Session session = c.connect();

            try {
                PreparedStatement statement = session
                        .prepare(createkeyspace);
                BoundStatement boundStatement = new BoundStatement(
                        statement);
                ResultSet rs = session
                        .execute(boundStatement);
                System.out.println("created HashMusic ");
            } catch (Exception et) {
                System.out.println("Can't create HashMusic " + et);
            }
            
             try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateSecondaryIndex);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create secondary index for PlayList table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateSongList);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create SongList table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateUserList);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Users table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreatePlayList);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create PlayList table " + et);
            }

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateHashList);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create HashList table " + et);
            }
            
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreatePostsTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create PostsTable table " + et);
            }
              try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateFollowerList);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create CreateFollowerList table " + et);
            }

        } catch (Exception et) {

        }
    }
}
