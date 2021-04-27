package com.serlvet.dto;

public class BookDTO {

	int bookId;
	String title;
	String author;
	
	public BookDTO(int bookId, String title, String author) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		
	}
	public int getBookId() {
		return bookId;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}	

}
