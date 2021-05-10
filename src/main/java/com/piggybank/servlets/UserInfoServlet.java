package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.piggybank.models.Users;
import com.piggybank.services.UserService;

/**
 * Servlet implementation class UserInfoServlet
 */
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		
		HttpSession ses = request.getSession(false);
		if(ses != null) {
			String username = (String) ses.getAttribute("username");
			Users user = UserService.findByUsername(username);
			pw.println("<p style='text-align: center;'>" + user + "</p>");	
			pw.println("<p style='text-align: center;'><a href= 'http://localhost:8080/rocp-project/UserMenu'>Click here to return to main menu</p>");
			
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
		
	}

/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
*/
}
