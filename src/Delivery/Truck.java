package Delivery;


import Stock.*;

/**
 * An abstract Truck class, used for implementing Ordinary Truck and Refrigerated Truck
 * 
 * @author Brendan Doncaster
 * @see Ordinary_Truck
 * @see Refrigerated_Truck
 */
public abstract class Truck {

	protected String truckType;
	protected Stock cargo;
	protected int capacity;
	

	/**
	 * Constructor method for default Truck, should not be used
	 * 
	 * @author Brendan Doncaster
	 * @see Ordinary_Truck
     * @see Refrigerated_Truck
	 */
	public Truck() {
		truckType = null;
		cargo = null;
		capacity = 0;
	}
	
	/**
	 * Getter method for truck's type
	 * 
	 * @return String representation of the type of truck
	 * @author Brendan Doncaster
	 */
	public String getTruckType() {
		return truckType;
	}
	
	/**
	 * Getter method for the truck's cargo
	 * 
	 * @return Stock that makes up the truck's cargo
	 * @author Brendan Doncaster
	 */
	public Stock getCargo() {
		return cargo;
	}
	
	/**
	 * Getter method for the truck's maximum capacity
	 * 
	 * @return Int that represents the maximum amount of items the truck can store
	 * @author Brendan Doncaster
	 */
	public int truckCapacity() {
		return capacity;
	}
	
	/**
	 * Get the truck's current amount of items
	 * 
	 * @return Sum of the amount of all items within the truck's cargo
	 * @author Brendan Doncaster
	 */
	public int getTruckSize() {
		return cargo.sumAmount();
	}
	
	/**
	 * Get the remaining capacity for the truck
	 * 
	 * @return Amount of items that can still fit into the truck's cargo
	 * @author Brendan Doncaster
	 */
	public int getRemainingCapacity() {
		return this.truckCapacity()-this.getTruckSize();
	}
	
	/**
	 * Truck wrapper for Stock's subtractItem method
	 * 
	 * @param item Item to be removed
	 * @param amount Quantity of the item to be removed
	 * @throws StockException Exception thrown from Stock's subtractItem method
	 * @author Brendan Doncaster
	 */
	public void removeItem(Item item, int amount) throws StockException{
		try {
			cargo.subtractItem(item, amount);
		} catch (StockException e) {
			throw e;
		}	
	}
	
	/**
	 * Truck wrapper for Stock's getAmount method
	 * 
	 * @param item Item to get the amount of
	 * @return Amount of given item that is in the truck's cargo
	 * @author Brendan Doncaster
	 */
	public int getCargoItem(Item item) {
		return this.getCargo().getAmount(item);
	}
	
	/**
	 * Get the coldest temperature required by an item within truck's cargo
	 * 
	 * @return Double value of the coldest temperature
	 * @throws DeliveryException Refrigerated truck with no refrigerated item
	 * @author Brendan Doncaster
	 */
	public abstract Double getTemp() throws DeliveryException;
	
	/**
	 * 
	 * @return
	 * @throws DeliveryException
	 */
	public abstract double truckCost() throws DeliveryException;
	
	/**
	 * Adds an item to the truck's cargo
	 * 
	 * @param item Item to add to the cargo
	 * @param amount Quantity of the item to add
	 * @throws DeliveryException Item exceeds cargo space or temperature is invalid
	 * @author Brendan Doncaster
	 */
	public abstract void addItem(Item item, int amount) throws DeliveryException;

}
