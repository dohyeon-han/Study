package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dbModify
 */
@WebServlet("/dbModify")
public class dbModify extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int bookId = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("book_name");
		String author = request.getParameter("author");
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost대신 ip주소가 들어갈수도
        String id = "c##scott";
        String pw = "tiger";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rst = null;
        try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url,id,pw);
			String sql = "update book set title=?, author=? where id=?";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setInt(3, bookId);
			
			if(pstmt.executeUpdate()==1) {
				out.print("success<br>");
				String sql2 = "select * from book";
				stmt=con.createStatement();
				rst = stmt.executeQuery(sql2);
				while(rst.next()) {
					bookId =rst.getInt("id");
					title = rst.getString("bookName");
					author = rst.getString("author");
					out.println(bookId + "  " +title+"  " + author + "<br>");
				}
			}else {
				out.print("Update fail");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	if(rst!=null) rst.close();
        	if(stmt!=null) stmt.close();
        	if(pstmt!=null) pstmt.close();
        	if(con!=null) con.close();
        }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
