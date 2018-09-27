package com.javaassignment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Booksales {
	
public static String GetSalesOnDate(ArrayList<Book> bookList, ArrayList<Sale> salesList, Date date){
    	
	String salesOnDate = "";
	int j = 0;
	int bookSaleByDate = 0;
	while (salesList.size() > j) {
		    Date orderDate = salesList.get(j).getSale_date();
		    if(date.equals(orderDate)){
		    	bookSaleByDate += salesList.get(j).getSale_item_count();
		    }
		    j++;
		}
	salesOnDate = "sales_on_date\t" + date.toString() + "\t" + bookSaleByDate;
	
 	return salesOnDate;
    }
	
    public static String GetTopSellingBooks(ArrayList<Book> bookList, ArrayList<Sale> salesList, int count){
    	
    	String topSellingBooks = "";
    	int j = 0;
    	HashMap<String, Integer> bookSale = new HashMap<String, Integer>();
    	while (salesList.size() > j) {
    		Map<String, Integer> bookOrderList = salesList.get(j).getBookList();
    		
    		for (Map.Entry<String, Integer> entry : bookOrderList.entrySet()) {
    		    String key = entry.getKey();
    		    int value = entry.getValue();
    		    if(bookSale.containsKey(key)){
    		    	int newValue = bookSale.get(key)+ value;
    		    	bookSale.put(key, newValue);
    		    }else{
    		    	bookSale.put(key, value);
    		    }
    		}
    		j++;
    	}

    	Map<String,Integer> topCount =
    			bookSale.entrySet().stream()
    		       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
    		       .limit(count)
    		       .collect(Collectors.toMap(
    		          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    	int k=0;
    	topSellingBooks = "top_selling_books\t";
    	List<String> topSellerList = new ArrayList<String>(topCount.keySet());
    	while(topSellerList.size() > k){
    		topSellingBooks += topSellerList.get(k) + "\t";
    		k++;
    	}
    	topSellingBooks += "\n";
     	return topSellingBooks;
    }
    
public static String GetTopCustomers(ArrayList<Book> bookList, ArrayList<Sale> salesList, int count){
    	
    	String topCustomers = "";
    	int j = 0;
    	HashMap<String, Integer> bookSaleByCustomer = new HashMap<String, Integer>();
    	while (salesList.size() > j) {
    		    String customer = salesList.get(j).getSale_email();
    		    int bookCount = salesList.get(j).getSale_item_count();
    		    if(bookSaleByCustomer.containsKey(customer)){
    		    	int newValue = bookSaleByCustomer.get(customer)+ bookCount;
    		    }else{
    		    	bookSaleByCustomer.put(customer, bookCount);
    		    }
    		    j++;
    		}
    		
    	Map<String,Integer> topCount =
    			bookSaleByCustomer.entrySet().stream()
    		       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
    		       .limit(count)
    		       .collect(Collectors.toMap(
    		          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    	int k=0;
    	topCustomers = "top_customers\t";
    	List<String> topBuyerList = new ArrayList<String>(topCount.keySet());
    	while(topBuyerList.size() > k){
    		topCustomers += topBuyerList.get(k) + "\t";
    		k++;
    	}
    	topCustomers += "\n";
     	return topCustomers;
    }
	
	public static void main(String args[]){  
	     //parse command line arguments
	     //verify presence of requires arguments through helper class.
	     CommandLineParser cli = new CommandLineParser();
	     BookSalesData bookSalesData = new BookSalesData();
	     if(!cli.validateArguments(args, bookSalesData)){
	    	 System.out.print("Invalid Argument List for BookSales application. Exiting Program.");
	    	 System.exit(-1);
	     }
	     //Valid argument list..now proceed
	     //parse books and sales csv files and add to books and sales maps
	     //String curDir = System.getProperty("user.dir");
	     CsvFileReader csvReader = new CsvFileReader();
	     ArrayList<Book> bookList = csvReader.readBooksCsvFile(bookSalesData.inputMap.get("books"));
	     ArrayList<Sale> salesList = csvReader.readSalesCsvFile(bookSalesData.inputMap.get("sales"));
	     
	     //check argument list and create output string
	     //create top selling books data string
	     String topSellingBooks = "";
	     String topCustomers = "";
	     String salesOnDate = "";
	     if(bookSalesData.inputMap.containsKey("topsellingbooks")){
	    	 //get list of top selling books
	    	 topSellingBooks = GetTopSellingBooks(bookList, salesList, Integer.parseInt(bookSalesData.inputMap.get("topsellingbooks")));
	    	 System.out.print(topSellingBooks);
	     //create top_customers data string
	     if(bookSalesData.inputMap.containsKey("topcustomers")){
	    	 topCustomers = GetTopCustomers(bookList, salesList, Integer.parseInt(bookSalesData.inputMap.get("topcustomers")));
	    	 System.out.print(topCustomers);
	     }
	     
	     //create total sales on date data string
	     if(bookSalesData.inputMap.containsKey("salesondate")){
	    	 try {
	    		 	String strDate = bookSalesData.inputMap.get("salesondate");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					date = dateFormat.parse(strDate);
				salesOnDate  = GetSalesOnDate(bookList, salesList, date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 System.out.print(salesOnDate);
	     }
	    	 
	    }  
	     
}

}
