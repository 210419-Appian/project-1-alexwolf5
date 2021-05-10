package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.piggybank.daos.UsersDAO;
import com.piggybank.daos.UsersDAOImpl;
import com.piggybank.models.Users;
import com.piggybank.services.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override			
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//This method sends and receives small amounts of unencrypted data back and forth between the browser and server
		HttpSession session = request.getSession();
		request.getRequestDispatcher("Login.html").forward(request, response);
			//Selects the html file viewStudent as the content to be displayed on the browser
	}

	@Override							
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//This method sends and receives unlimited amounts of encrypted data back and forth between the browser and server
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService login = new UserService();
		
		int result = login.loggingIn(username, password);
		
		if (result == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request,  response);
			out.print("<p style = 'color:red; text-align:center;'>Invalid Username/Password</p>");
		} else {
			HttpSession ses = request.getSession();
			ses.setAttribute("username", username);
			Users myUser = UserService.findByUsername(username);
			ses.setAttribute("password", myUser.getPassword());
			ses.setAttribute("firstname", myUser.getFirstName());
			ses.setAttribute("lastname", myUser.getLastName());
			ses.setAttribute("email", myUser.getEmail());
			ses.setAttribute("role", myUser.getRole());
			ses.setAttribute("UserId", myUser.getUserId());
			
			request.getRequestDispatcher("/UserMenu").forward(request, response);
			//Forwards you so another servlet at the end
		}
		
	}
	
}
