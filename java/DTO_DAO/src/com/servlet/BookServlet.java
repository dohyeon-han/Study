package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.serlvet.dto.BookDTO;
import com.servlet.dao.BookDAO;

//같은 파일에 썼던 기존의 쿼리문을 다른 파일로 옮겨 역할을 구분
//DAO DB에 접근해 쿼리문 수행
//DTO 쿼리 실행 결과의 컬럼을 하나의 자료형으로 묶음
@WebServlet("/BS")
public class BookServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		BookDAO bDAO = new BookDAO();
		ArrayList<BookDTO> list = bDAO.select();//쿼리문 실행결과를 list로
		
		for (int i = 0; i < list.size(); i++) {
			BookDTO bDTO = list.get(i);
			out.print("Book ID : " + bDTO.getBookId() + ", ");
			out.print("Title : " + bDTO.getTitle() + ", ");
			out.print("Author : " + bDTO.getAuthor() + "<br>");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
