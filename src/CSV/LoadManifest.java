package CSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Delivery.*;
import Stock.*;

/**
 * Class that contains static methods used for loading a manifest from a file and adding it to the store
 * 
 * @author Brendan Doncaster
 *
 */
public class LoadManifest {

    /**
     * Takes the manifest created from the selected directory and adds 
     * the items to the store and subtracts its cost from the store's capital
     * 
     * @param file String format of the file directory of a CSV formatted manifest
     * @throws DeliveryException
     * @throws CSVFormatException
     * @throws IOException
     * @throws StockException
     */
	public static void LoadManifestCSV(String file) throws DeliveryException, CSVFormatException, IOException, StockException  {
		Manifest manifest;
		Stock stock;
		
		//Create the manifest from the csv
		try {
			manifest = ReadManifestCSV(file);
		} catch (CSVFormatException | StockException | DeliveryException | IOException e1) {
			throw e1;
		}
		
		//lower the capital by the manifest cost
		try {
			Store.getInstance().lowerCapital(manifest.sumManifestCost());
		} catch (DeliveryException e) {
			throw e;
		}
		
		//add all the items from the trucks to the store
		for(Truck truck: manifest.returnManifest()) {
			stock = truck.getCargo();
			Store.getInstance().getInventory().addStock(stock);
		}
		
	}
	
	
	/**
	 * Reads the selected file directory and turns its contents into a Manifest
	 * 
	 * @param file String format of the file directory of a CSV formatted manifest
	 * @return Manifest object version of the csv file
	 * @throws DeliveryException when truckType of a truck is unrecognised
	 * @throws CSVFormatException when fields in the csv are incorrect or missing, or the file doesn't exist
	 * @throws IOException default exception throws
	 * @throws StockException when addItemName string is invalid
	 * @see IOException
	 */
	public static Manifest ReadManifestCSV(String file) throws DeliveryException, CSVFormatException, IOException, StockException {
		Manifest importManifest = new Manifest();
		
		if(new File(file).isFile()) {
			
	        BufferedReader br = null;
	        String line = "";
	        String csvSplitBy = ",";
	        String name;
	        String truckType;
	        int quantity;
	        int truck = -1;
	        int currentLine = 0;

	        try {

	            br = new BufferedReader(new FileReader(file));
	            while ((line = br.readLine()) != null) {
	                currentLine++;
	            	name = null;
	            	truckType = null;
	            	quantity = -1;
	            	String[] item = line.split(csvSplitBy);
	            	if(item.length == 1) {
	            		
	            		truckType = item[0];
	            		try {
							importManifest.importTruck(truckType);
						} catch (DeliveryException e) {
							throw new DeliveryException("Error on line " + currentLine + ": " + e.getMessage());
						}
	            		truck++;
	            	}
	            	
	            	else if(item.length == 2) {
	            		
	            		if(truck == -1) {
	            			throw new CSVFormatException("Tried to load items in manifest before loading a truck");
	            		}
	            		name = item[0];
	            		try {
	                		quantity = Integer.parseInt(item[1]);
	                	}
	                	catch (NumberFormatException e){
	                		throw new CSVFormatException("Sale for item " + name +  " on line " + currentLine + " has an invalid amount");
	                	}

						try {
                            importManifest.returnManifest()[truck].addItemName(name, quantity);
                        } catch (DeliveryException | StockException e) {
							throw new StockException("Error on line " + currentLine + ": " + e.getMessage());
						}
	            	}
	            	
	            	else {
	            		throw new CSVFormatException("Manifest line " + currentLine + " does not have correct amount of fields(1 or 2)");
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
		
		
		return importManifest;
	}

}
