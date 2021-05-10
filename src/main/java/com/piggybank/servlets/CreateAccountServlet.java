package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.piggybank.daos.AccountsDAO;
import com.piggybank.daos.AccountsDAOImpl;
import com.piggybank.models.Accounts;
import com.piggybank.services.AccountService;

/**
 * Servlet implementation class CreateAccountServlet
 */
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CreateAccount.html").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		
		if(ses != null) {
			String type = request.getParameter("type");
			int userId = (int) ses.getAttribute("UserId");
			AccountService create = new AccountService();
			create.createAccount(type, userId);
			request.getRequestDispatcher("/UserMenu").forward(request, response);
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
	}

}
