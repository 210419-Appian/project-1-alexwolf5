package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.piggybank.services.UserService;

/**
 * Servlet implementation class ChangeInfoServlet
 */
public class ChangeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		
		if(ses != null) {
			request.getRequestDispatcher("ChangeInfo.html").forward(request, response);
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		UserService user = new UserService();
		
		if(ses != null) {
			int UserId = Integer.parseInt(request.getParameter("userId"));
			int checkId = (int) ses.getAttribute("UserId");
			String checkRole = (String) ses.getAttribute("role");
			
			if (checkRole == "Admin" || UserId == checkId) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String email = request.getParameter("email");
				
				user.newDetails(UserId, username, password, firstname, lastname, email, checkRole);
				request.getRequestDispatcher("/UserMenu").forward(request, response);	
			} else {
				request.getRequestDispatcher("/UserMenu").forward(request, response);
			}
			
			
			
			
			
			
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
	}

}
