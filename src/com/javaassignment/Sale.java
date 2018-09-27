package com.javaassignment;

import java.util.Date;
import java.util.HashMap;

public class Sale {
	public Sale(Date sale_date, String sale_email, String sale_payment_method, int sale_item_count, HashMap<String,Integer> bookList) {
		super();
		this.sale_date = sale_date;
		this.sale_email = sale_email;
		this.sale_payment_method = sale_payment_method;
		this.sale_item_count = sale_item_count;
		this.bookList = bookList;
	}

	public Sale() {
		// TODO Auto-generated constructor stub
	}

	Date 	sale_date;
	String sale_email;
	String sale_payment_method;
	int sale_item_count;
	HashMap<String,Integer> bookList;
	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}

	public String getSale_email() {
		return sale_email;
	}

	public void setSale_email(String sale_email) {
		this.sale_email = sale_email;
	}

	public String getSale_payment_method() {
		return sale_payment_method;
	}

	public void setSale_payment_method(String sale_payment_method) {
		this.sale_payment_method = sale_payment_method;
	}

	public int getSale_item_count() {
		return sale_item_count;
	}

	public void setSale_item_count(int sale_item_count) {
		this.sale_item_count = sale_item_count;
	}

	public HashMap<String, Integer> getBookList() {
		return bookList;
	}

	public void setBookList(HashMap<String, Integer> bookList) {
		this.bookList = bookList;
	} 

}
