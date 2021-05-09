package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.piggybank.daos.UsersDAO;
import com.piggybank.daos.UsersDAOImpl;

public class LoginServlet extends HttpServlet {
	public @interface WebServlet {

	}

	private static final long serialVersionUID = 1L;
/*
	@Override			
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//This method sends and receives small amounts of unencrypted data back and forth between the browser and server
		
		request.getRequestDispatcher("Login.html").forward(request, response);
			//Selects the html file viewStudent as the content to be displayed on the browser
	}
*/
	@Override							
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//This method sends and receives unlimited amounts of encrypted data back and forth between the browser and server
		RequestDispatcher rd = null;
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
			//Sets the 'age' variable by using the request operator to get the parameter "age" defined in the html file
		
		String password = request.getParameter("password");
			//Sets the 'name' variable by using the request operator to get the parameter "name" defined in the html file
		
		UsersDAO login = new UsersDAOImpl();
		
		int result = login.loggingIn(username, password);
		
		if (result == 0) {
			rd = request.getRequestDispatcher("Login.html");
			rd.include(request,  response);
			out.print("<p style = 'color:red; text-align:center;'>Invalid Username/Password</p>");
		} else {
			request.getRequestDispatcher("UserMenu.html").forward(request, response);
			//Forwards you so another servlet at the end
		}
		
	}
	
}
