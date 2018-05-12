package Stock;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;



public class Stock {
	
	LinkedHashMap<Object, Integer> stockArray;

	public Stock() {
		stockArray = new LinkedHashMap<Object, Integer>();
		
	}
	
	public void addItem(Item item, int quantity) {
		
		StockException badItemType = new StockException();
		
		
		
		if (stockArray.containsKey(item)) {
			int existingQuantity = stockArray.get(item);
			System.out.println("The item " + item.getName() + " already exists. Adding " + quantity + " to it...");
			int newQuantity = existingQuantity + quantity;
			stockArray.put(item, newQuantity);
			System.out.println("The item " + item.getName() + " now has " + getAmount(item) + " things");
		}
		else {
			stockArray.put(item, quantity);
		}
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
	
	public double sumCosts() {
		double result = 0;
		
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		   // System.out.println("The cost of " + item.getName() + " is $" + item.getCost());
		   // System.out.println("There are " + quantity + " of it.");
		    
		    
		    result = result + (item.getCost() * quantity);   
		}
		
		System.out.println("The result for sumCosts() is " + result);
		
		return result;
	}
	
	public double sumPrices() {
		double result = 0;
		
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		  //  System.out.println("The price of " + item.getName() + " is $" + item.getPrice());
		  //  System.out.println("There are " + quantity + " of it.");
		    
		    
		    result = result + (item.getPrice() * quantity);   
		}
		
		System.out.println("The result for sumPrices() is " + result);
		return result;
	}

	public Object[] getListCold() {
		Object[] coldItems = null;
		int arraySize = 0;
		
		//find the needed size of the array
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    
		    if (item.getTemp() != null) {
		    		arraySize++;
		    }
		}
		coldItems = new Object[arraySize];
		int position = 0;
		
		//add items to the array
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();  
		    
		    if (item.tempRequired() == true) {
		    		//System.out.println(item.getName() + " is cold. Adding to list...");
		    		coldItems[position] = item;
		    		position++;
		    }
		    
		    else {
		    		//System.out.println(item.getName() + " is not cold.");
		    }
		}
		
		Arrays.sort(coldItems);
		
		return coldItems;
	}

	public Item[] getListOrdinary() {
		Item[] ordinaryItems = null;
		int arraySize = 0;
		
		//find the needed size of the array
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    
		    if (item.getTemp() != null) {
		    		arraySize++;
		    }
		}
		ordinaryItems = new Item[arraySize];
		int position = 0;
		
		//add items to the array
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();  
		    
		    if (item.tempRequired() == false) {
		    		//System.out.println(item.getName() + " is not cold. Adding to list...");
		    		ordinaryItems[position] = item;
		    		position++;
		    }
		    
		    else {
		    		//System.out.println(item.getName() + " is cold.");
		    }
		}
		
		return ordinaryItems;
				
	}

	public Double getColdestTemp() {
		Item[] coldItems = null;
		int arraySize = 0;
		
		//find the needed size of the array
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    
		    if (item.getTemp() != null) {
		    		arraySize++;
		    }
		}
		coldItems = new Item[arraySize];
		int position = 0;
		
		//add items to the array
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();  
		    
		    if (item.tempRequired() == true) {
		    		//System.out.println(item.getName() + " is cold. Adding to list...");
		    		coldItems[position] = item;
		    		position++;
		    }
		    
		    else {
		    		//System.out.println(item.getName() + " is not cold.");
		    }
		}
		
		Arrays.sort(coldItems);
		
		return coldItems[0].getTemp();
	}

	public String getManifestPrintStyle() {
		String result = "";
		
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    result = result + item.getName() + "," + quantity + "\n";
		}
		
		return result;
		
	}

	public int getAmount(Item item) {
		return stockArray.get(item);
	}

	public int subtractItem(Item item, int subtractAmount) {
		//to-do: add exception
		int origAmount = stockArray.get(item);
		
		stockArray.put(item, subtractAmount);
	
		int newAmount = origAmount - subtractAmount;
		
		//System.out.println("The original amount is " + origAmount + ", minus " + subtractAmount + " equals " + newAmount);
		
		return newAmount;
		
	}

	public void addItemName(String string, int quantity) {
		//to-do: add exception
		stockArray.put(string, quantity);
	}

	public void subtractStock(Stock stock) {
		//to-do: add exception in subtractItem
		for (Entry<Object, Integer> entry : stock.stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    subtractItem(item, quantity);
		}
	}

	public int sumAmount() {
		int result = 0;
		
		int pos = 1;
		
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
		    Integer quantity = entry.getValue();
		    //System.out.println("The item in position " + pos + " has " + quantity + " things");
		    result = result + quantity;  
		    pos++;
		}
		
		//System.out.println("There are " + result + " total things");
		return result;
	}

	public Item[] needsReorder() {
		Item[] reOrdItems = null;
		int arraySize = 0;
		
		
		//find the needed size of the array
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
			Integer quantity = entry.getValue();
		    
		    if (item.getRePoint() < quantity) {
		    		arraySize++;
		    }
		}
		
		reOrdItems = new Item[arraySize];
		int position = 0;
		
		for (Entry<Object, Integer> entry : stockArray.entrySet()) {
			Item item = (Item) entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    System.out.println("There is " + quantity + " of " + item.getName());
		    
		    if (item.getRePoint() < quantity) {
		    		System.out.println(item.getName() + " needs reordering");
		    		reOrdItems[position] = item;
		    		position++;
		    }
		    else {
		    		System.out.println(item.getName() + " is fine");
		    }
		}
		
		System.out.println(reOrdItems);
		
		return reOrdItems;
	}

	
	
	
	
	

}
