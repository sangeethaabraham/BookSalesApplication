package com.javaassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CsvFileReader {
	
	private static final String COMMA_DELIMITER = ",";

	public static ArrayList<Book> readBooksCsvFile(String fileName){
		BufferedReader fileReader = null;
		
		try{
			//Create books array
			ArrayList<Book> booksList = new ArrayList<Book>();
			String line = "";
			
			//create file reader
			fileReader = new BufferedReader(new FileReader(fileName));
			
			//read file line by line
			while ((line = fileReader.readLine()) != null) {
				//get all available tokens in line
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {
					//create book object
					Book book = new Book(tokens[0], tokens[1], tokens[2], tokens[3]);
					booksList.add(book);
				}
			}
			return booksList;
		}
		catch(Exception e){
			System.out.print("Error reading books.list");
		}
		finally{
			try{
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
			}
		}
		return null;
	}
	
	public static ArrayList<Sale> readSalesCsvFile(String fileName){
		BufferedReader fileReader = null;
		
		try{
			//Create books array
			ArrayList<Sale> salesList = new ArrayList<Sale>();
			String line = "";
			
			//create file reader
			fileReader = new BufferedReader(new FileReader(fileName));
			//read file line by line
			while ((line = fileReader.readLine()) != null) {
				int tokenCount = 0;
				//get all available tokens in line
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {
					//create book object
					Sale sale = new Sale( );
					String strDate = tokens[tokenCount++];
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					date = dateFormat.parse(strDate);
					sale.setSale_date(date);
					sale.setSale_email(tokens[tokenCount++]);
					sale.setSale_payment_method(tokens[tokenCount++]);
					int bookCount = Integer.parseInt(tokens[tokenCount++]);
					sale.setSale_item_count(bookCount);
					HashMap<String,Integer> bookList = new HashMap<String,Integer>();
					for(int i=0; i<bookCount; i++){
						String bookDetails = tokens[tokenCount++];
						String[] split = bookDetails.split(";");
						String firstSubString = split[0];
						String secondSubString = split[1];
						bookList.put(firstSubString, Integer.parseInt(secondSubString));
					}
					sale.setBookList(bookList);
					salesList.add(sale);
				}
			}
			return salesList;
		}
		catch(Exception e){
			System.out.print("Error reading books.list");
		}
		finally{
			try{
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
			}
		}
		return null;
	}
}
