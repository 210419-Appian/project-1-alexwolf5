package com.piggybank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		if(ses != null) {
			
			
			
			
		} else {
			pw.print("Please log in.");
			response.setStatus(400);
		}
	}

}
