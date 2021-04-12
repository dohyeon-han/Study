package com.getInitParameter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class gip
 */
@WebServlet("/gip")
public class gip extends HttpServlet {
	//jsp는 config, application   servlet은 getServletConfig, getServletContext
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = getServletConfig().getInitParameter("adminId");
		String pw = getServletConfig().getInitParameter("adminPw");
		
		PrintWriter out = response.getWriter();
		out.print("ID : " + id + ", ");
		out.print("PW : " + pw);
		out.print("\r\n");
		
		String imgDir = getServletContext().getInitParameter("imgDir");
		String tIP = getServletContext().getInitParameter("testServerIP");
		String rIP = getServletContext().getInitParameter("realServerIP");
		
		out.print("imgDir : " + imgDir + ", ");
		out.print("tIP : " + tIP);
		out.print("rIP : " + rIP);
		
		getServletContext().setAttribute("name", "HEE");//공유 변수
		String a = (String)getServletContext().getAttribute("name");
		out.print(a);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
