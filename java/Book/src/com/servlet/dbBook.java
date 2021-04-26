package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbBook")
public class dbBook extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int bookId = Integer.parseInt(request.getParameter("id"));
		String bookName = request.getParameter("book_name");
		String author = request.getParameter("author");
				
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost대신 ip주소가 들어갈수도
        String id = "c##scott";
        String pw = "tiger";
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rst = null;
        
        try {
			Class.forName(driver);

			con = DriverManager.getConnection(url,id,pw);
			stmt = con.createStatement();
			//prepared statement가 더 안전
			String sql = "insert into book(id,title,author)";
				sql+="values("+ bookId + ",'"+bookName+"','"+author+"')";
			int result = stmt.executeUpdate(sql);

			if(result==1) {
				out.print("insert success<br>");
				
				String select = "select * from book order by id";
				rst = stmt.executeQuery(select);
				while(rst.next()) {
					bookId = rst.getInt("id");
					bookName = rst.getString("title");
					author = rst.getString("author");
					out.println(bookId + "  " + bookName+"  " + author + "<br>");
				}
			}
			else {
				out.print("insert fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
        	if(rst!=null) rst.close();
        	if(stmt!=null) stmt.close();
        	if(con!=null) con.close();
        }
        catch (Exception e2) {
        	e2.printStackTrace();
        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
