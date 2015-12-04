/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stores;

import java.util.Date;

/**
 *
 * @author Yulian
 */
public class PostDetails {
    public PostDetails(){}
    
    String postedByUName = null;
    Date datePosted = null;
    String content = null;
    java.util.UUID postedBy = null;
  
    
    
    public void setPostDetails(String postedByUName, Date datePosted,String content, java.util.UUID postedBy)
    {
        this.postedByUName = postedByUName;
        this.datePosted = datePosted;
        this.content = content;        
        this.postedBy = postedBy;
        
    }
    
    public java.util.UUID getPostedBy()
    {
        return postedBy;
    }
    
    public String getPostedByUName()
    {
        return postedByUName;
    }
    
    public Date getDatePosted()
    {
        return datePosted;
    }
      public String getPostContent()
    {
        return content;
    }
    
   
    
    
}
