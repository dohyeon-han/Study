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

@WebServlet("/dbSelect")
public class dbSelect extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
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
			String sql = "select * from book order by id";
			rst = stmt.executeQuery(sql);
			
			while(rst.next()) {
				int bookId = rst.getInt("id");
				String title = rst.getString("title");
				String author = rst.getString("author");
				
				out.println(bookId + "  " + title + "  " + author + "<br>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        if(rst!=null) rst.close();
        if(stmt!=null) stmt.close();
        if(con!=null) con.close();
        }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
