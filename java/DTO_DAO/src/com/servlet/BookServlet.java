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

//���� ���Ͽ� ��� ������ �������� �ٸ� ���Ϸ� �Ű� ������ ����
//DAO DB�� ������ ������ ����
//DTO ���� ���� ����� �÷��� �ϳ��� �ڷ������� ����
@WebServlet("/BS")
public class BookServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		BookDAO bDAO = new BookDAO();
		ArrayList<BookDTO> list = bDAO.select();//������ �������� list��
		
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
