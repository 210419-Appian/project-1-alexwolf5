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

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Register.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService reg = new UserService();
		int usernameAv = reg.checkUsernameAvailability(username);

		if (usernameAv == 1){
			reg.register(username, password, firstname, lastname, email);
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
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("Register.html");
			rd.include(request,  response);
			out.print("<p style = 'color:red; text-align:center;'>Sorry, that username is already taken</p>");
		}
	}

}
