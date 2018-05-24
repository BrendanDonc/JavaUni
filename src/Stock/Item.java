package Stock;

import java.util.Objects;

/**
 * The 'Item' class is used for storing information about the various items that populate the inventory management application.
 * 
 * @author Mary Millar
 *
 */
public class Item implements Comparable<Item>{
	
	//Initialise variables
	String name;
	double cost;
	double price;
	int rePoint;
	int reAmount;
	Double temp;
	
	/**
	 * A constructor that's used to declare an item that does not require temperature restricted storage.
	 * 
	 * @param itemName The name of the item.
	 * @param itemCost The manufacturing cost of the item.
	 * @param itemPrice The sale price of the item.
	 * @param itemReorderPoint The amount current stock of an item must fall under for more of the item to be reordered.
	 * @param itemReorderAmount The amount that is reordered once an item falls under the reorder point.
	 * 
	 * @throws StockException The exception thrown if: the item cost is less than zero,
	 * 												  the item price is less than zero,
	 * 												  the item reorder point is less than zero, or 
	 * 												  the item reorder amount is less than zero.
	 */
	public Item(String itemName, double itemCost, double itemPrice, int itemReorderPoint, int itemReorderAmount) throws StockException {
		if (itemCost <= 0 || itemPrice <= 0 || itemReorderPoint <= 0 || itemReorderAmount <= 0) {
			throw new StockException("The item '" + itemName + "' has a bad parameter.");	
		}
		else {
			this.name = itemName;
			this.cost = itemCost;
			this.price = itemPrice;
			this.rePoint = itemReorderPoint;
			this.reAmount = itemReorderAmount;
		}
	}
	
	/**
	 * A constructor that's used to declare an item that does require temperature restricted storage.
	 * 
	 * @param itemName The name of the item.
	 * @param itemCost The manufacturing cost of the item.
	 * @param itemPrice The sale price of the item.
	 * @param itemReorderPoint The amount current stock of an item must fall under for more of the item to be reordered.
	 * @param itemReorderAmount The amount that is reordered once an item falls under the reorder point.
	 * @param itemTemp The temperature required for the item throughout the supply chain proccess.
	 * 
	 * @throws StockException The exception thrown if: the item cost is less than zero,
	 * 												  the item price is less than zero,
	 * 												  the item reorder point is less than zero,
	 * 												  the item reorder amount is less than zero, or
	 * 												  the temperature is less than -20 or more than 10 degrees Celsius.
	 */
	public Item(String itemName, double itemCost, double itemPrice, int itemReorderPoint, int itemReorderAmount, Double itemTemp) throws StockException {
		if (itemCost <= 0 || itemPrice <= 0 || itemReorderPoint <= 0 || itemReorderAmount <= 0 || !(-20 <= itemTemp && itemTemp <= 10)) {
			throw new StockException("The item '" + itemName + "' has a bad parameter");	
		}
		else {
			this.name = itemName;
			this.cost = itemCost;
			this.price = itemPrice;
			this.rePoint = itemReorderPoint;
			this.reAmount = itemReorderAmount;
			this.temp = itemTemp;
		}	
	}
	
	
	/**
	 * This method is used to retrieve the name of the item.
	 * 
	 * @return A string representing the name of the item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method is used to retrieve the manufacturing cost of the item.
	 * 
	 * @return A double representing the manufacturing cost of the item.
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * This method is used to retrieve the sale price of the item.
	 * 
	 * @return A double representing the sale price of the item.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * This method is used to retrieve the reorder point of the item.
	 * 
	 * @return An integer representing the reorder point of the item/
	 */
	public int getRePoint() {
		return rePoint;
	}
	
	/**
	 * This method is used to retrieve the reorder amount of the item.
	 * 
	 * @return An integer representing the reorder amount of the item.
	 */
	public int getReAmount() {
		return reAmount;
	}
	
	/**
	 * This method is used to retrieved the temperature (if specificed) of the item.
	 * 
	 * @return A double representing the temperature of the item.
	 */
	public Double getTemp() {
		return temp;
	}
	
	
	/**
	 * This method is used to determine if the item needs to be temperature-controlled.
	 * 
	 * @return A boolean representing if the item needs to be temperature-controlled (true) or not (false).
	 */
	public boolean tempRequired() {
		if (temp != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	/**
	 * This method compares each parameter of the current item against the input to determine if they equal.
	 * 
	 * @param item The item that needs to be compared.
	 * 
	 * @return A boolean representing if both items are identical (true) or not (false);
	 */
	public boolean equals(Item item) {
		boolean equal = true;
		if (this.name != item.getName()) {
			equal = false;
		}
		
		if (this.cost != item.getCost()) {
			equal = false;
		}
		
		if (this.price != item.getPrice()) {
			equal = false;
		}
		
		if (this.rePoint != item.getRePoint()) {
			equal = false;
		}
		
		if (this.reAmount != item.getReAmount()) {
			equal = false;
		}
		
		if (this.temp != item.getTemp()) {
			equal = false;
		}
		return equal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Item otherItem) {
		if(this.getTemp() > otherItem.getTemp()) {
            return 1;
		}
        else if (this.getTemp() == otherItem.getTemp()) {
            return 0;
        }
        return -1;
	}

}
