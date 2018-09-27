package com.javaassignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineParser {
	
	public boolean validateArguments(String args[], BookSalesData bookSalesData){
		//2 required arguments 
		if(args.length < 2){
			return false;
		}
		//put argument list into an array
		List<String> argsList = new ArrayList<String>();
		argsList = Arrays.asList(args);
		
		for (int i = 0; i < argsList.size(); i++) {
			String temp = argsList.get(i);
			String[] split = temp.split("=");
			String firstSubString = split[0];
			String secondSubString = split[1];
			if(firstSubString.contentEquals("--books")){
				//save books path
				bookSalesData.inputMap.put("books", secondSubString);
			}
			else if(firstSubString.contentEquals("--sales")){
				//save sales path
				bookSalesData.inputMap.put("sales", secondSubString);
			}
			else if(firstSubString.contentEquals("--top_selling_books")){
				//save top selling books data
				bookSalesData.inputMap.put("topsellingbooks", secondSubString);
			}
			else if(firstSubString.contentEquals("--top_customers")){
				//save top customers data
				bookSalesData.inputMap.put("topcustomers", secondSubString);
			}
			else if(firstSubString.contentEquals("--sales_on_date")){
				//save top customers data
				bookSalesData.inputMap.put("salesondate", secondSubString);
			}
		}
		//should contain books and sales
		if(!bookSalesData.inputMap.containsKey("books") ||
				!bookSalesData.inputMap.containsKey("sales")){
			System.out.print("Mandatory Arguments missing. Please check arguments list.");
			return false;
		}
		return true;
	}
}
