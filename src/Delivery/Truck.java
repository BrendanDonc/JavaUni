package Delivery;

import Stock.Item;

public abstract class Truck {

	private String truckType;
	private Stock cargo;
	private int capacity;
	
	public Truck() {
		truckType = null;
		cargo = null;
		capacity = null;
	}
	
	public String getTruckType() {
		return truckType;
	}
	
	public Stock getCargo() {
		return cargo;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public abstract int getRemainingCapacity();
	
	public abstract double getCost();
	
	public abstract void addTruckItem(Item item, int amount);

}
