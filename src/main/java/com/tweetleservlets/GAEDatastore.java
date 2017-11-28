package com.tweetleservlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class GAEDatastore
 */
@WebServlet("/datastore")
public class GAEDatastore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GAEDatastore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		String me = request.getParameter("message");
		String aut = request.getParameter("author");
		
		
    	   Entity tweet = new Entity("Tweet",me);
   		tweet.setProperty("message",me);
   		tweet.setProperty("visited", 1);
   		tweet.setProperty("author", aut);
   		tweet.setProperty("DateCreated", new Date().toString());
   		ds.put(tweet);
   		response.getWriter().print("<p>Tweet is : "+me+"</p>");
   		response.getWriter().print("<a href=\"index.html\">GO Back</a>");
   		//response.sendRedirect(request.getContextPath()+"/index.html");
       		
        
       //String lastName = (String) result.getProperty("lastName");   
       //Long height = (Long) result.getProperty("Name");   
       
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
