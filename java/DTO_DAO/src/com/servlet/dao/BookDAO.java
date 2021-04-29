package com.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.serlvet.dto.BookDTO;

//servlet -> DAO -> DB -> DAO -> DTO -> DAO -> servlet
public class BookDAO {
	
	DataSource dataSource;
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
//    String id = "c##scott";
//    String pw = "tiger";
    
	
    public BookDAO() {//DB연결
		try {
			//Class.forName(driver);
			InitialContext context = new InitialContext();//connection pool 사용
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle18c");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public ArrayList<BookDTO> select(){//DB에 쿼리 실행 후 DTO로 
    	
    	ArrayList<BookDTO> list = new ArrayList<BookDTO>();
    	
    	Connection con=null;
    	PreparedStatement pstmt = null;
    	ResultSet rst = null;
    	
    	try {
    		//con = DriverManager.getConnection(url,id,pw);
    		con = dataSource.getConnection();
    		String sql = "select * from book";
    		pstmt = con.prepareStatement(sql);
    		rst = pstmt.executeQuery();
		
			while(rst.next()) {
				int bookId = rst.getInt("id");
				String title = rst.getString("title");
				String author = rst.getString("author");
				
				//DTO로 형식에 맞게 바꿔줌
				BookDTO bDTO = new BookDTO(bookId, title, author);
				list.add(bDTO);
			}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	try {
			if(rst!=null) rst.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return list;
    }
}
