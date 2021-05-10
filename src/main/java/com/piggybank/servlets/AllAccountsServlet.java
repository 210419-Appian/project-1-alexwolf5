package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.piggybank.services.AccountService;
import com.piggybank.services.UserService;

/**
 * Servlet implementation class AllAccountsServlet
 */
public class AllAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		UserService user = new UserService();
		AccountService acct = new AccountService();
		
		if(ses != null) {
			int userId = (int) ses.getAttribute("UserId");
			String checkRole = user.getRole(userId);
			
			if (checkRole.equals("User")) {
				pw.println("Sorry, you can not access this.");
				response.setStatus(401);
				//pw.print("<p style='text-align: center;'><a href='http://localhost:8080/rocp-project/UserMenu'>Return to Main Menu</a></p>");
			} else {
				pw.println(acct.everyAcct());
				response.setStatus(401);
				//pw.print("<p style='text-align: center;'><a href='http://localhost:8080/rocp-project/UserMenu'>Return to Main Menu</a></p>");	
				}
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
	}

}
