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
                    + "duration text, "
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

           

            String CreatePostsTable = "CREATE TABLE if not exists HashMusic.Posts ("
                    + "post_id uuid,"
                    + "postedTo_id uuid,"
                    + "postedBy_id uuid,"
                    + "postedBy_uName text,"
                    + "date_posted timestamp,"
                    + "content text,"
                    + "PRIMARY KEY (postedTo_id, date_posted)"
                    + ") WITH CLUSTERING ORDER BY (date_posted desc);";
              

            String CreateHashList = "CREATE TABLE if not exists HashMusic.HashList (\n"
                    + "user_id uuid, "
                    + "hashTag text, "
                    + "song_id uuid, "
                    + "PRIMARY KEY (user_id, song_id)"
                    + ")";

            String CreateHashTags = "CREATE TABLE if not exists HashMusic.HashTags (\n"
                    + "hash_id uuid, "
                    + "hash_value text, "
                    + "hash_message text, "
                    + "user_id uuid, "
                    + "PRIMARY KEY (hash_id)"
                    + ")";

            String CreateFollowerList = "CREATE TABLE if not exists HashMusic.followers (\n"
                    + " followerUser_ID uuid,"
                    + " follower_UName text, "
                    + " followingUser_ID uuid,"
                    + "following_UName text, "
                    + "date_followed timestamp,"
                    + "PRIMARY KEY (followerUser_ID, followingUser_ID, date_followed)\n"
                    + ") WITH CLUSTERING ORDER BY (followingUser_ID desc, date_followed desc);";
            
            String CreateUserSongs = "CREATE TABLE if not exists HashMusic.UserSongs (\n"
                    + "user_id uuid, "
                    + "title text, "
                    + "duration text, "
                    + "artist text, "
                    + "album text, "
                    + "genre text, "
                    + "song_id uuid, "
                    + "PRIMARY KEY (user_id, song_id)"
                    + ")";
            
             String CreatePlayList = "CREATE TABLE if not exists HashMusic.PlayList (\n"
                    + "user_id uuid, "
                    + "playlist_name text, "
                    + "song_id uuid, "
                    + "title text, "
                    + "duration text, "
                    + "artist text, "
                    + "album text, "
                    + "genre text, "
                    + "PRIMARY KEY ((user_id, playlist_name), song_id)"
                    + ")";
            
            String CreateSecondaryIndex = "CREATE INDEX user_id ON HashMusic.PlayList (user_id);";

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
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateHashTags);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create CreateHashTags table " + et);
            }
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateUserSongs);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create CreateHashTags table " + et);
            }

        } catch (Exception et) {

        }
    }
}
