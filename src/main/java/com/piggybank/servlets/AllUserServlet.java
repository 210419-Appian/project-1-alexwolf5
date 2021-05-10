package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.piggybank.services.UserService;
import com.piggybank.utilities.ConnectionUtility;

public class AllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		UserService user = new UserService();
		
		if(ses != null) {
			int userId = (int) ses.getAttribute("UserId");
			String checkRole = user.getRole(userId);
			
			if (checkRole.equals("User")) {
				pw.println("Sorry, you can not access this.");
				pw.print("<p style='text-align: center;'><a href='http://localhost:8080/rocp-project/UserMenu'>Return to Main Menu</a></p>");
			} else {
				pw.println(user.everyUser());
				pw.print("<p style='text-align: center;'><a href='http://localhost:8080/rocp-project/UserMenu'>Return to Main Menu</a></p>");	
				}
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
	}

}
