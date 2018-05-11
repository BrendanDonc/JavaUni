package Stock;

import java.util.ArrayList;


public class Stock {
	
	ArrayList<Item> stockArray;

	public Stock() {
		stockArray = new ArrayList<Item>();
		
	}
	
	public void addItem(Item item, int quantity) {
		
	}
		
	public Item[] getItems() {		
		Item[] stock = new Item[stockArray.size()];
		
		for (int i = 0; i < stockArray.size(); i++) {
			stock[i] = stockArray.get(i);
		}
		
		return stock;
	}
	
	

}
