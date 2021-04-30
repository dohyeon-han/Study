package com.from;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/f")
public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		String pw = request.getParameter("password");
		String[] hobby = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		
		System.out.println("name : "+name);
		System.out.println("pw : "+pw);
		System.out.println("hobby : "+Arrays.toString(hobby));
		System.out.println("gender : "+gender);
		
		Enumeration<String> para = request.getParameterNames();
		while(para.hasMoreElements()) {
			System.out.println(para.nextElement());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
