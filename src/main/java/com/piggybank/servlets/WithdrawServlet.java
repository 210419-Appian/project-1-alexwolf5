package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.piggybank.services.AccountService;
import com.piggybank.services.Transactions;
import com.piggybank.services.UserService;

/**
 * Servlet implementation class WithdrawServlet
 */
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		
		if(ses != null) {
			request.getRequestDispatcher("Withdraw.html").forward(request, response);
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountService bal = new AccountService();
		Transactions with = new Transactions();
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		
		if(ses != null) {
			int acctId = Integer.parseInt(request.getParameter("acctId"));
			double withdraw = Double.parseDouble(request.getParameter("with"));
			int UserId = (int) ses.getAttribute("UserId");
			int checkId = bal.getAcctId(UserId);
			
			if (acctId == checkId) {
				double balance = bal.getBalance(acctId);
				double check = with.withdraw(balance, withdraw, acctId);
			
				if (check == 0) {
					RequestDispatcher rd = request.getRequestDispatcher("Withdraw.html");
					rd.include(request,  response);
					pw.print("<p style = 'color:red; text-align:center;'>Insufficient Funds</p>");
				} else {
					request.getRequestDispatcher("/UserMenu").forward(request, response);
				} 
			} else {
				request.getRequestDispatcher("/UserMenu").forward(request, response);
			}
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
	}

}
