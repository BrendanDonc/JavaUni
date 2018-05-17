package CSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Stock.*;
import Stock.StockException;

public class InitializeItems {
	
	public static void InitializeItems(String file) throws CSVFormatException, StockException, IOException {
		Stock inventory = new Stock();

		try {
			inventory.addStock(ReadItemCSV(file));
		} catch (CSVFormatException | StockException | IOException e) {
			throw e;
		}

		Store.getInstance().setInventory(inventory);
	}
	
	
	
	public static Stock ReadItemCSV(String file) throws CSVFormatException, StockException, IOException {
		String csvFile = file;
		Stock inventory = new Stock();
		
		if(new File(csvFile).isFile()) {
			
	        BufferedReader br = null;
	        String line = "";
	        String csvSplitBy = ",";
	        String name;
	        int cost;
	        int price;
	        int rePoint;
	        int reAmount;
	        Double temp;

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {
	            	name = null;
	            	cost = -1;
	            	price = -1;
	            	rePoint = -1;
	            	reAmount = -1;
	            	temp = null;
	            	

	                // use comma as separator
	                String[] item = line.split(csvSplitBy);
	                if (item.length > 6 || item.length < 5) {
	                	throw new CSVFormatException("Item property does not have correct amount of fields(either 5 or 6)");
	                	}
	                else {
	                	name = item[0];
	                	
	                	try {
	                		cost = Integer.parseInt(item[1]);
	                	}
	                	catch (NumberFormatException e){
	                		throw new CSVFormatException("Item with name " + name +  " has an invalid cost");
	                	}
	                	
	                	try {
	                		price = Integer.parseInt(item[2]);
	                	}
	                	catch (NumberFormatException e){
	                		throw new CSVFormatException("Item with name " + name +  " has an invalid price");
	                	}
	                	
	                	try {
	                		rePoint = Integer.parseInt(item[3]);
	                	}
	                	catch (NumberFormatException e){
	                		throw new CSVFormatException("Item with name " + name +  " has an invalid reorder point");
	                	}
	                	
	                	try {
	                		reAmount = Integer.parseInt(item[4]);
	                	}
	                	catch (NumberFormatException e){
	                		throw new CSVFormatException("Item with name " + name +  " has an invalid reorder amount");
	                	}
	                	
	                	if (item.length == 6) {
	                		try {
	                			temp = Double.valueOf(item[5]);
	                		}
	                		catch (NumberFormatException e){
		                		throw new CSVFormatException("Item with name " + name +  " has an invalid temperature");
		                	}
	                		
	                		
	                		try {
								inventory.addItem(new Item(name, cost, price, rePoint, reAmount, temp), 0);
							} catch (StockException e) {
								throw e;
							}
	                	}
	                	
	                	else {
	                		 try {
								inventory.addItem(new Item(name, cost, price, rePoint, reAmount), 0);
							} catch (StockException e) {
								throw e;
							}
	                	}
	                	
	                }
	                
	                

	            }

	        } catch (FileNotFoundException e) {
	            throw new CSVFormatException("File does not exist");
	        } catch (IOException e) {
	            throw e;
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    throw e;
	                }
	            }
	        }
		}
		else {
			throw new CSVFormatException("File does not exist");
		}
		return inventory;
	}

}