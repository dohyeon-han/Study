package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbBook")
public class dbBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public dbBook() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String bookName = request.getParameter("book_name");
		String author = request.getParameter("author");
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost대신 ip주소가 들어갈수도
        String id = "c##scott";
        String pw = "oracle";
        
        Connection con = null;
        Statement stmt = null;
        
        try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url,id,pw);
			stmt = con.createStatement();
			//prepared statement가 더 안전
			String sql = "insert into book(id,name,autor)";
				sql+="values(book_seq.nextval,'"+bookName+"','"+author+"')";
			int result = stmt.executeUpdate(sql);
			
			if(result==1) {
				out.print("insert success");
			}
			else {
				out.print("insert fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
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
