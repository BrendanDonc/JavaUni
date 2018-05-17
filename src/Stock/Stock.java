package Stock;


import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




public class Stock {
	
	LinkedHashMap<Item, Integer> stockArray;

	public Stock() {
		stockArray = new LinkedHashMap<Item, Integer>();
		
	}
	
	public void clearStock() {
		this.stockArray = new LinkedHashMap<Item, Integer>();
	}
	
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
	
	public void addStock(Stock stock) {
		for (Entry<Item, Integer> entry : stock.stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    addItem(item, quantity);
		}
	}
	
	public double sumCosts() {
		double result = 0;
		
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    result = result + (item.getCost() * quantity);   
		}
		
		return result;
	}
	
	public double sumPrices() {
		double result = 0;
		
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    result = result + (item.getPrice() * quantity);   
		}
		
		return result;
	}

	public Item[] getListCold() {
		Map<Item, Double> unsortedColds = new HashMap<Item, Double>();
		
		//find the needed size of the array
		for (Item item : stockArray.keySet()) {		    
		    if (item.getTemp() != null) {
		    	unsortedColds.put(item, item.getTemp());
		    }
		}
		
		List<Map.Entry<Item, Double>> listColds 
			= new LinkedList<Map.Entry<Item, Double>>(unsortedColds.entrySet());
		
		Collections.sort(listColds, new Comparator<Map.Entry<Item, Double>>() {
			public int compare(Map.Entry<Item, Double> o1,
								  Map.Entry<Item, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
				
			}
		});
		
		Map<Item, Double> sortedColds = new LinkedHashMap<Item, Double>();
		for (Map.Entry<Item, Double> entry : listColds) {
			sortedColds.put(entry.getKey(), entry.getValue());
		}

		Item[] coldItems = new Item[sortedColds.size()];
		
		int position = 0;
		for(Entry<Item, Double> entry:sortedColds.entrySet()) {
			coldItems[position] = entry.getKey();
			position++;
		}
		
		return coldItems;
	}

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

	public Double getColdestTemp() {
		Item[] coldItems = null;
		
		coldItems = this.getListCold();
		
		return coldItems[0].getTemp();
	}

	public String getManifestPrintStyle() {
		String result = "";
		
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    result = result + item.getName() + "," + quantity + "\n";
		}
		
		return result;
		
	}

	public int getAmount(Item item) {
		return stockArray.get(item);
	}

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

	public void addItemName(String string, int quantity) throws StockException {
		//need to check if item is in store
		
		Item stringInput = null;
		for (Item item:Store.getInstance().getInventory().getItems()) {
			if (item.getName().equals(string)) {
				stringInput = item;
			}
		}
		if (stringInput == null) {
			throw new StockException();
		}
		this.addItem(stringInput, quantity);
	}

	public void subtractStock(Stock stock) throws StockException {
		for (Entry<Item, Integer> entry : stock.stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    try {
				subtractItem(item, quantity);
			} 
		    catch (StockException subtractError) {
		    		//fail("Failed to subtract item from stock: " + stock);
		    		throw subtractError;
		    }
		}
	}

	public int sumAmount() {
		int result = 0;
		
		int pos = 1;
		
		for (Entry<Item, Integer> entry : stockArray.entrySet()) {
		    Integer quantity = entry.getValue();
		    result = result + quantity;  
		    pos++;
		}

		return result;
	}

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

	public boolean validSubtractItem(Item item, int quantity) {
		boolean success = true;
			
		if (stockArray.containsKey(item)) {
			int origAmount = stockArray.get(item);
			
			if (origAmount >= quantity) {
				
			}
			else {
				success = false;
			}
		}
		else {
			success = false;
		}
		
		return success;

	}

	public boolean validSubtractStock(Stock stock) {
		boolean success = true;
		
		if (stock.stockArray.entrySet() != null) {
			for (Entry<Item, Integer> entry : stock.stockArray.entrySet()) {
				Item item = (Item) entry.getKey();
			    Integer quantity = entry.getValue();
			    	
			    if (this.validSubtractItem(item, quantity) == false) {
			    		success = false;
			    }
			}
		}
		else {
			success = false;
		}
		
		
		return success;
	}
}
