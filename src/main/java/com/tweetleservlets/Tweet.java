package com.tweetleservlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class Tweet
 */
@WebServlet("/Tweet")
public class Tweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tweet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Entity e;
    	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		String me = request.getParameter("message");
		Query q = new Query("Tweet"); 
		boolean isPresent = false;
        PreparedQuery pq = ds.prepare(q); 
        for (Entity result : pq.asIterable()) {   
       String message = (String) result.getProperty("message");  
       if(message.equals(me)) {
    	  
    	   isPresent = true;
    	    try {
				e = ds.get(KeyFactory.createKey("Tweet", me));
				Long v = (Long)e.getProperty("visited");
				Long visits = v+1;
				System.out.println(e);
				System.out.println(v);
				System.out.println(visits);
				e.setProperty("visited", visits);
				ds.put(e);				
			} catch (EntityNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	   break;
       }
        }
       if(isPresent == false) {
    	   response.sendRedirect(request.getContextPath()+"/datastore?message="+me);
   		
       		}else {
       			
       		
        
		response.getWriter().print("<p>Tweet is : "+me+"</p>");
		response.getWriter().print("<a href=\"friends.html\">GO Back</a>");
       		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
