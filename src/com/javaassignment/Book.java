package com.javaassignment;

public class Book {

	String book_id;
	String book_title;
	String book_author;
	String book_price;
	
	public Book(String book_id, String book_title, String book_author, String book_price) {
		this.book_id = book_id;
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_price = book_price;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getBook_price() {
		return book_price;
	}
	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}
}
