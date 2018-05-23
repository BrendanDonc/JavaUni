package CSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Stock.*;
import Stock.StockException;

public class LoadSales {
	
	public static void LoadSales(String file) throws StockException, CSVFormatException, IOException{
		Stock sale;

		try {
			sale = ReadSaleCSV(file);
		} catch (CSVFormatException | StockException | IOException e1) {
			throw e1;
		}

		try {
			if(Store.getInstance().getInventory().validSubtractStock(sale)) {
				try {
					Store.getInstance().getInventory().subtractStock(sale);
				} catch (StockException e) {
					throw e;
				}
				Store.getInstance().raiseCapital(sale.sumPrices());
			}
		} catch (StockException e) {
			throw e;
		}
		

	}
	
	public static Stock ReadSaleCSV(String file) throws CSVFormatException, StockException, IOException {
		String csvFile = file;
		Stock sale = new Stock();
		
		if(new File(csvFile).isFile()) {
			
	        BufferedReader br = null;
	        String line = "";
	        String csvSplitBy = ",";
	        String name;
	        int quantity;
	        int currentLine = 0;

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {
	                currentLine++;
	            	name = null;
	            	quantity = -1;
	            	String[] item = line.split(csvSplitBy);
	            	if(item.length == 2) {
	            		name = item[0];
	            		try {
	                		quantity = Integer.parseInt(item[1]);
	                	}
	                	catch (NumberFormatException e){
	                		throw new CSVFormatException("Sale for item " + name +  " on line " + currentLine + " has an invalid amount");
	                	}
	            		sale.addItemName(name, quantity);
	            	}
	            	else {
	            		throw new CSVFormatException("Sale on line " + currentLine + " does not have correct amount of fields(2)");
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
		return sale;
	}
	
}
