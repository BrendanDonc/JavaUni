package Delivery;

import Stock.*;

public class Ordinary_Truck extends Truck {


	public Ordinary_Truck() {
		this.cargo = new Stock();
		this.truckType = "Ordinary";
		this.capacity = 750;
	}

	@Override
	public void addItem(Item item, int amount) throws DeliveryException {
		if(item.tempRequired()) {
			throw new DeliveryException("Attempted to add refrigerated item to an ordinary truck");
		}
		else {
			if(this.getTruckSize() + amount > this.truckCapacity()) {
				throw new DeliveryException("Added amount exceeds truck capacity");
			}
			else {
				cargo.addItem(item, amount);
			}
		}
		
	}

	@Override
	public double truckCost() {
		double cost = 750 + (25*this.getTruckSize());
		return cost;
	}

	@Override
	public Double getTemp() {
		return null;
	}
}
