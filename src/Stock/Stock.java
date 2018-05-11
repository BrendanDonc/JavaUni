package Stock;


import java.util.HashMap;


public class Stock {
	
	HashMap<Object, Integer> stockArray;

	public Stock() {
		stockArray = new HashMap<Object, Integer>();
		
	}
	
	public void addItem(Item item, int quantity) {
		//will need to override equals() and hashCode() functions for hashmap w/ custom object to work...
		
		System.out.println("Adding "+ quantity + " of " + item + "...");
		stockArray.put(item, quantity);
		System.out.println("The new stock size is " + stockArray.size());
	}
		
	public Object[] getItems() {
		Object[] stock = null;
		
		if (stockArray.size() > 0) {
			stock = stockArray.keySet().toArray();

			return stock;
			
		}
		
		else {
			return stock;
		}
	}
	
	public void addStock(Stock stock) {
		
		Object[] stockConverted = null;
		stockConverted = stock.stockArray.keySet().toArray();
		
		System.out.println(stockConverted);
		stockArray.putAll(stock.stockArray);
	}
	
	

}
