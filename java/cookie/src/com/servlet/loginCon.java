package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginCon
 */
@WebServlet("/loginCon")
public class loginCon extends HttpServlet {
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");
		
		out.print(id);
		out.print(pw);
		out.print("<br>");
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		
		for(Cookie c : cookies) {
			System.out.println(c.getName()+"  "+ c.getValue());
			
			if(c.getName().equals("AAA")) {//한 번 로그인을 함
				cookie = c;
			}
		}
		if(cookie==null) {
			System.out.println("cookie is null");
			cookie = new Cookie("AAA",id);//name,value
		}
		response.addCookie(cookie);
		cookie.setMaxAge(60*60);//1시간
		
		response.sendRedirect("loginOk.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
