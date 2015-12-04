/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stores;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Yulian
 */
public class FollowerList {

    public FollowerList() {
    }

    UUID followerUserID = null;
    UUID followingUserID = null;
    String followerUName = null;
    String followingUname = null;
    Date date_followed = null;

    public void setFollowerDetails(UUID followerUserID, UUID followingUserID, String followerUName, String followingUname, Date date_followed) {

        this.followerUserID = followerUserID;
        this.followingUserID = followingUserID;
        this.followerUName = followerUName;
        this.followingUname = followingUname;
        this.date_followed = date_followed;

    }

    public UUID getFollowerUserID() {
        return followerUserID;
    }

    public UUID getFollowingUserID() {
        return followingUserID;
    }

    public String getFollowerUName() {
        return followerUName;
    }

    public String getFollowingUName() {
        return followingUname;
    }

    public Date getDateFollowed() {
        return date_followed;
    }

}
