package Stock;


import java.util.HashMap;


public class Stock {
	
	HashMap<Object, Integer> stockArray;

	public Stock() {
		stockArray = new HashMap<Object, Integer>();
		
	}
	
	public void addItem(Item item, int quantity) {
		stockArray.put(item, quantity);
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
		stockArray.putAll(stock.stockArray);
	}
	
	

}
