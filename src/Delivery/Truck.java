package Delivery;

import Stock.*;

public abstract class Truck {

	protected String truckType;
	protected Stock cargo;
	protected int capacity;
	
	public Truck() {
		truckType = null;
		cargo = null;
		capacity = 0;
	}
	
	public String getTruckType() {
		return truckType;
	}
	
	public Stock getCargo() {
		return cargo;
	}
	
	public int truckCapacity() {
		return capacity;
	}
	
	public int getTruckSize() {
		return cargo.sumAmount();
	}
	
	public abstract double truckCost();
	
	public abstract void addItem(Item item, int amount) throws DeliveryException;
	
	public void removeItem(Item item, int amount) {
		cargo.subtractItem(item, amount);	
	}
	
	public int getCargoItem(Item item) {
		return this.getCargo().getAmount();
	}
	
	public abstract Double getTemp();

}
