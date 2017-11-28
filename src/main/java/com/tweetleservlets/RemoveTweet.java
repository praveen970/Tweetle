package com.tweetleservlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class RemoveTweet
 */
@WebServlet("/removetweets")
public class RemoveTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveTweet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Tweet"); 
		String message = request.getParameter("message");
	System.out.println(message);
	String m = message.substring(1);
		        PreparedQuery pq = ds.prepare(q); 
		        Key tweetKey = KeyFactory.createKey("Tweet", m);
		        Entity e=null;
				try {
					e = ds.get(tweetKey);
				} catch (EntityNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        System.out.println(e.getProperty("DateCreated"));
		        ds.delete(tweetKey);
		        
		   
		       //String lastName = (String) result.getProperty("lastName");   
		       //Long height = (Long) result.getProperty("Name");   
		       
		       
				Query q2 = new Query("Tweet"); 
				String author = request.getParameter("author");
			System.out.println(author);
				        PreparedQuery pq2 = ds.prepare(q2); 
				        ArrayList<String> arr = new ArrayList<String>();
				        
				        
				    for (Entity result : pq2.asIterable()) {   
				    	if(author.equals(result.getProperty("author"))) {
				    		
				    	String date = (String) result.getProperty("DateCreated");
				    	arr.add(date);
				       String tweet = (String) result.getProperty("message");  
				       arr.add(tweet);
				    	}
				    }
		    
		   //response.getWriter().println(arr.toString());
		    //response.getWriter().write(arr.toString());
				    response.getWriter().write(arr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
