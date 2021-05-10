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

public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		
		if(ses != null) {
			request.getRequestDispatcher("Transfer.html").forward(request, response);
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountService bal = new AccountService();
		Transactions tran = new Transactions();
		PrintWriter pw = response.getWriter();
		HttpSession ses = request.getSession(false);
		
		if(ses != null) {
			int acct1 = Integer.parseInt(request.getParameter("acctId"));
			int UserId = (int) ses.getAttribute("UserId");
			int checkId = bal.getAcctId(UserId);
			
			if (acct1 == checkId) {
				int acct2 = Integer.parseInt(request.getParameter("acctId2"));
				double transfer = Double.parseDouble(request.getParameter("tran"));
				double balance1 = bal.getBalance(acct1);
				double balance2 = bal.getBalance(acct2);
				tran.transfer(balance1, balance2, transfer, acct1, acct2);
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
