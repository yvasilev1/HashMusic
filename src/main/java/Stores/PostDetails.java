/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stores;

import java.util.Date;

/**
 *
 * @author Connor
 */
public class PostDetails {
        public PostDetails(){}
        
        private java.util.UUID postedByUUID;
        private String postedBy;
        private Date datePosted;
        private String postContents;
        
        public void setPostDetails(java.util.UUID postedByUUID, String postedBy, Date datePosted, String postContents)
        {
            this.postedByUUID = postedByUUID;
            this.postedBy = postedBy;
            this.datePosted = datePosted;
            this.postContents = postContents;
        }
        
        public java.util.UUID getPostedByID()
        {
            return postedByUUID;
        }
        
        public String getPostedBy()
        {
            return postedBy;
        }
        
        public Date getDatePosted()
        {
            return datePosted;
        }
        
        public String getPostContents()
        {
            return postContents;
        }
        
}
