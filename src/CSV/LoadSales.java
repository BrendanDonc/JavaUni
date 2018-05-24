package CSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Stock.*;
import Stock.StockException;

/**
 * Class that contains static methods used for 
 * 
 * @author Brendan Doncaster
 *
 */
public class LoadSales {
	
    /**
     * Takes the Stock created from the selected directory and subtracts 
     * the items from the store and adds its price to the store's capital
     * 
     * @param file String format of the file directory of a CSV formated sales log
     * @throws CSVFormatException when fields in the csv are incorrect or missing, or the file doesn't exist
     * @throws StockException when the string for addItemName is invalid or the stock is an invalid subtraction
     * @throws IOException default exception throws
     * @see IOException
     */
	public static void LoadSalesCSV(String file) throws StockException, CSVFormatException, IOException{
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
	
	/**
	 * Reads the selected file directory and turns its contents into a Stock
	 * 
	 * @param file String format of the file directory of a CSV formatted sales log
	 * @return Stock object version of the sales log
	 * @throws CSVFormatException when fields in the csv are incorrect or missing, or the file doesn't exist
	 * @throws StockException when the string for addItemName is invalid
	 * @throws IOException default exception throws
     * @see IOException
	 */
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
