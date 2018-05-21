package Stock;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The 'Stock' class is used to manipulate collections of items, and is used for representing store inventory, stock orders, sales logs, and truck cargo.
 * 
 * @author Mary Millar
 *
 */
public class Stock {
	
	//Declaring a 'holder' for the items in each stock.
	LinkedHashMap<Item, Integer> stockArray;

	/**
	 * A constructor that's used to seperate the different 'stocks' that exist.
	 */
	public Stock() {
		stockArray = new LinkedHashMap<Item, Integer>();
		
	}
	
	/**
	 * A method that empties out the contents of the current stock.
	 */
	public void clearStock() {
		this.stockArray = new LinkedHashMap<Item, Integer>();
	}
	
	/**
	 * A method that adds an item to the current stock.
	 * 
	 * This method takes into consideration if the item to be added already exists, and updates the quantity accordingly.
	 * 
	 * @param item The item that is being added.
	 * @param quantity The quantity of the item to be added.
	 */
	public void addItem(Item item, int quantity)  {
		
		if (stockArray.containsKey(item)) {
			int existingQuantity = stockArray.get(item);
			int newQuantity = existingQuantity + quantity;
			stockArray.put(item, newQuantity);
		}
		else {
			stockArray.put(item, quantity);
		}

	}
		
	/**
	 * A method that retrieves an array of all the items in the current stock.
	 * 
	 * This method takes into account that a stock may be empty.
	 * 
	 * @return An array of type Item representing all of the items in the current stock.
	 */
	public Item[] getItems() {
		Item[] stock = null;
		
		if (stockArray.size() > 0) {
			stock = new Item[stockArray.size()];
			int pos = 0;
			for (Entry<Item, Integer> entry : stockArray.entrySet()) {
				Item item = (Item) entry.getKey();
			    
			    stock[pos] = item;
			    pos++;    
			}
			return stock;
		}	
		else {
			return stock;
		}
	}
	
	/**
	 * A method that adds an 'outside' stock to the current stock.
	 * 
	 * @param stock The stock to be added to the current stock.
	 */
	public void addStock(Stock stock) {
		for (Entry<Item, Integer> entry : stock.stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    addItem(item, quantity);
		}
	}
	
	/**
	 * A method that returns the sum of manufacturing costs of all the items currently in the stock.
	 * 
	 * @return A double representing the total sum of the manufacturing costs in the current stock.
	 */
	public double sumCosts() {
		double result = 0;
		
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    result = result + (item.getCost() * quantity);   
		}
		
		return result;
	}
	
	/**
	 * A method that returns the sum of the sale prices of all the items currently in the stock.
	 * 
	 * @return A double representing the total sum of the sale prices in the current stock.
	 */
	public double sumPrices() {
		double result = 0;
		
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    result = result + (item.getPrice() * quantity);   
		}
		
		return result;
	}

	/**
	 * A method that returns a sorted list of all the items in the current stock that must be temperature-controlled.
	 * 
	 * @return An array of type 'Item' representing all the 'cold' items in the current stock.
	 */
	public Item[] getListCold() {
		Map<Item, Double> unsortedColds = new HashMap<Item, Double>();
		
		//puts all the items in current stock into a new hashmap
		for (Item item : stockArray.keySet()) {		    
		    if (item.getTemp() != null) {
		    	unsortedColds.put(item, item.getTemp());
		    }
		}
		
		List<Map.Entry<Item, Double>> listColds 
			= new LinkedList<Map.Entry<Item, Double>>(unsortedColds.entrySet());
		
		//verifies and sorts each of the unsorted items
		Collections.sort(listColds, new Comparator<Map.Entry<Item, Double>>() {
			public int compare(Map.Entry<Item, Double> o1,
								  Map.Entry<Item, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
				
			}
		});
		
		//puts the now sorted items into a linkedhashmap
		Map<Item, Double> sortedColds = new LinkedHashMap<Item, Double>();
		for (Map.Entry<Item, Double> entry : listColds) {
			sortedColds.put(entry.getKey(), entry.getValue());
		}

		Item[] coldItems = new Item[sortedColds.size()];
		
		//adds the sorted items to a new array
		int position = 0;
		for(Entry<Item, Double> entry:sortedColds.entrySet()) {
			coldItems[position] = entry.getKey();
			position++;
		}
		
		//returns the new sorted array
		return coldItems;
	}

	/**
	 * A method that returns an unsorted list of all the non-temperature-controlled items in the current stock.
	 * 
	 * @return An array of type 'Item' representing all the 'ordinary' items in the current stock.
	 */
	public Item[] getListOrdinary() {
		Item[] ordinaryItems = null;
		int arraySize = 0;
		
		//find the needed size of the array
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    
		    if (item.getTemp() == null) {
		    		arraySize++;
		    }
		}
		ordinaryItems = new Item[arraySize];
		int position = 0;
		
		//add items to the array
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();  
		    
		    if (item.tempRequired() == false) {
		    		ordinaryItems[position] = item;
		    		position++;
		    }
		}
		
		return ordinaryItems;
				
	}

	/**
	 * A method that retrieves the lowest temperature of any item in the current stock. 
	 * 
	 * This is used to ensure any items do not spoil in the supply chain proccess.
	 * 
	 * @return A double representing the 'coldest' temperature-controlled item.
	 */
	public Double getColdestTemp() {
		Item[] coldItems = null;
		
		coldItems = this.getListCold();
		
		return coldItems[0].getTemp();
	}

	/**
	 * This method formats the information in the current stock into a 'manifest' style (ie. [item],[quantity]).
	 * 
	 * @return A string representing a list that contains items in the current stock and their quantity.
	 */
	public String getManifestPrintStyle() {
		String result = "";
		
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    result = result + item.getName() + "," + quantity + "\n";
		}
		
		return result;
		
	}

	/**
	 * This method retrieves the quantity of the specified item in the current stock.
	 * 
	 * @param item The name of the item.
	 * 
	 * @return An integer representing the amount of an item that is being held in the current stock.
	 */
	public int getAmount(Item item) {
		return stockArray.get(item);
	}

	/**
	 * This method subtracts the specified item and quantity from the current stock.
	 * 
	 * @param item The name of the item.
	 * @param subtractAmount The quantity being subtracted.
	 * 
	 * @throws StockException The exception thrown if either the item name or quantity is invalid.
	 */
	public void subtractItem(Item item, int subtractAmount) throws StockException {
		StockException badItemAmount = new StockException();
		StockException badItem = new StockException();
		
		if (stockArray.containsKey(item)) {
			int origAmount = stockArray.get(item);
			
			if (origAmount >= subtractAmount) {
				int newAmount = origAmount - subtractAmount;
				
				stockArray.put(item, newAmount);
				
			}
			else {
				throw badItemAmount;
			}
		}
		else {
			throw badItem;
		}
		
	}

	/**
	 * This method adds the quantity of an item (specified by its name) into the store.
	 * 
	 * @param string The name of the item.
	 * @param quantity The quantity being added.
	 * 
	 * @throws StockException The exception thrown if the item is not found in the store.
	 */
	public void addItemName(String string, int quantity) throws StockException {
		Item stringInput = null;
		for (Item item:Store.getInstance().getInventory().getItems()) {
			if (item.getName().equals(string)) {
				stringInput = item;
			}
		}
		if (stringInput == null) {
			throw new StockException("Item name not found in store");
		}
		this.addItem(stringInput, quantity);
	}

	/**
	 * This method subtracts a whole 'outside' stock from the current stock.
	 * 
	 * @param stock The 'outside' stock
	 * 
	 * @throws StockException The exception thrown if there is not enough of an item to subtract.
	 */
	public void subtractStock(Stock stock) throws StockException {
		for (Entry<Item, Integer> entry : stock.stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    try {
				subtractItem(item, quantity);
			} 
		    catch (StockException subtractError) {
		    		throw subtractError;
		    }
		}
	}

	/**
	 * This method retrieves the total amount of items (ie. all their quantities) in the current stock.
	 * 
	 * @return An integer representing the total amount of items in the current stock.
	 */
	public int sumAmount() {
		int result = 0;
		
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
		    Integer quantity = entry.getValue();
		    result = result + quantity;  
		}

		return result;
	}

	/**
	 * This method retrieves a list of all the items that need to be reordered.
	 * 
	 * @return An array of type 'Item' that represents all the items to be reordered.
	 */
	public Item[] needsReorder() {
		Item[] reOrdItems = null;
		int arraySize = 0;
		
		
		//find the needed size of the array
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
			Integer quantity = entry.getValue();
		    
		    if (item.getRePoint() > quantity) {
		    		arraySize++;
		    }
		}
		
		reOrdItems = new Item[arraySize];
		int position = 0;
		
		//adds the to-be-reordered items into the array
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    if (item.getRePoint() > quantity) {
		    		reOrdItems[position] = item;
		    		position++;
		    }
		}
		return reOrdItems;
	}

	/**
	 * This method determines if the removal of an item from a stock will be successful or not.
	 * 
	 * @param item The name of the item.
	 * @param quantity The quantity to be removed.
	 * 
	 * @return A boolean representing if the removal of an item from a stock will be successful (true) or not (false).
	 * 
	 * @throws StockException The exception thrown if there was not enough of an item to be subtracted, or if the store did not contain the item.
	 */
	public boolean validSubtractItem(Item item, int quantity) throws StockException {
		boolean success = true;
			
		if (stockArray.containsKey(item)) {
			int origAmount = stockArray.get(item);
			
			if (origAmount >= quantity) {
				
			}
			else {
				success = false;
				StockException badItemAmount = new StockException("There was not enough of item '" + item.getName() + "' to sell." );
				throw badItemAmount;
			}
		}
		else {
			success = false;
			StockException badItem = new StockException("Store did not contain item '" + item.getName() + "'.");
			throw badItem;
		}
		
		return success;

	}

	/**
	 * This method determines if the removal of an 'outside' stock from the current stock will be successful or not.
	 * 
	 * @param stock The name of the 'outside' stock.
	 * 
	 * @return A boolean representing if the removal of the 'outside' stock will be successful (true) or not (false).
	 * 
	 * @throws StockException The exception thrown if there was not enough of an item to be subtracted, or if the store did not contain the item.
	 */
	public boolean validSubtractStock(Stock stock) throws StockException {
		boolean success = true;
		
		if (stock.stockArray.entrySet() != null) {
			for (Entry<Item, Integer> entry : stock.stockArray.entrySet()) {
				Item item = (Item) entry.getKey();
			    Integer quantity = entry.getValue();
			    	
			    try {
					if (this.validSubtractItem(item, quantity) == false) {
							success = false;
					}
				} catch (StockException e) {
					throw e;
				}
			}
		}
		else {
			success = false;
		}
		return success;
	}
}
