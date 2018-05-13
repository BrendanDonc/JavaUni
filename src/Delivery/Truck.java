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
	
	public void removeItem(Item item, int amount) {
		cargo.subtractItem(item, amount);	
	}
	
	public int getCargoItem(Item item) {
		return this.getCargo().getAmount(item);
	}
	
	public Truck importTruck(String string) throws DeliveryException {
		Truck importTruck;
		
		if (string.substring(0,1) == ">") {
			string = string.substring(1);			
		}
		
		if(string == "ordinary") {
			importTruck = new Ordinary_Truck();
		}
		
		else if(string == "refrigerated") {
			importTruck = new Refrigerated_Truck();
		}
		else {
			throw new DeliveryException();
		}
		return importTruck;
	}
	
	public abstract Double getTemp() throws DeliveryException;
	
	public abstract double truckCost() throws DeliveryException;
	
	public abstract void addItem(Item item, int amount) throws DeliveryException;

}
