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
  
    
    
    public void setPostDetails(String postedByUName, Date datePosted,String content)
    {
        this.postedByUName = postedByUName;
        this.datePosted = datePosted;
        this.content = content;        
        
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
