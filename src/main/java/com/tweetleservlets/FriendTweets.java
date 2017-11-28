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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class FriendTweets
 */
@WebServlet("/friendtweets")
public class FriendTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendTweets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Tweet").addSort("visited",Query.SortDirection.DESCENDING); 
		String author = request.getParameter("author");
	System.out.println("from friendtweets"+author);
		        PreparedQuery pq = ds.prepare(q); 
		        ArrayList<String> arr = new ArrayList<String>();
		        
		        
		    for (Entity result : pq.asIterable()) {   
		    	if(!author.equals(result.getProperty("author"))) {
		    		
		    	String date = (String) result.getProperty("DateCreated");
		    	arr.add(date);
		       String tweet = (String) result.getProperty("message");  
		       arr.add(tweet);
		       String aut = (String) result.getProperty("author");  
		       arr.add(aut);
		       Long visit = (Long) result.getProperty("visited");  
		       arr.add(visit.toString());
		    	}
		    }
		       //String lastName = (String) result.getProperty("lastName");   
		       //Long height = (Long) result.getProperty("Name");   
		       
		
		    
		   //response.getWriter().println(arr.toString());
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
