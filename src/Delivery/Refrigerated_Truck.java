package Delivery;

import Stock.*;

public class Refrigerated_Truck extends Truck {

	public Refrigerated_Truck() {
		this.cargo = new Stock();
		this.truckType = "Refrigerated";
		this.capacity = 800;
	}

	@Override
	public void addItem(Item item, int amount) throws DeliveryException {
		if(item.getTemp() > 10 || item.getTemp() < -20) {
			throw new DeliveryException("Added item requires a temperature outside of truck capabilities");
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
	public double truckCost() throws DeliveryException {
		double cost = 900.00 + 200.00 * java.lang.Math.pow(0.7,this.getTemp());
		return cost;
	}

	@Override
	public Double getTemp() throws DeliveryException {
		if(this.getCargo().getListCold().length == 0) {
			throw new DeliveryException("Refrigerated truck has no refrigerated items");
		}
		else {
			return this.getCargo().getColdestTemp();
		}
	}

}
