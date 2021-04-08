package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		PrintWriter out = response.getWriter();
//		out.print("");
//		out.print("<div>Hello Servlet</div>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@PreDestroy
	public void preD() {
		System.out.println("--Pre--");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("--init--");
	}

	@Override
	public void destroy() {
		System.out.println("--destroy--");
	}
	
	@PostConstruct
	public void postC() {
		System.out.println("--Post--");

	}
	
	
}
