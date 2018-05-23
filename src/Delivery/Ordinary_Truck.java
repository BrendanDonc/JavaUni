package Delivery;


import Stock.*;

/**
 * Ordinary Truck class that extends the abstract class Truck.
 * Used for implementing a truck that can not store refrigerated items within its cargo(Stock)
 * 
 * @see Truck
 * @see Refrigerate_Truck
 * @see Item
 * @see Stock
 * @author Brendan Doncaster
 */
public class Ordinary_Truck extends Truck {

    /**
     * Constructor method for an ordinary truck
     * 
     * @see Truck
     * @see Refrigerate_Truck
     * @author Brendan Doncaster
     */
	public Ordinary_Truck() {
		this.cargo = new Stock();
		this.truckType = "Ordinary";
		this.capacity = 1000;
	}

	/**
	 * {@inheritDoc}
	 */
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
    public void addItemName(String string, int amount) throws DeliveryException, StockException {
        Item stringItem;
        try {
            stringItem = Stock.getItemFromName(string);
        } catch (StockException e) {
            throw e;
        }
        
        if(stringItem.tempRequired()) {
            throw new DeliveryException("Attempted to add refrigerated item to an ordinary truck");
        }
        else {
            if(this.getTruckSize() + amount > this.truckCapacity()) {
                throw new DeliveryException("Added amount exceeds truck capacity");
            }
            else {
                cargo.addItem(stringItem, amount);
            }
        }
        
    }

	/**
     * {@inheritDoc}
     */
	@Override
	public double truckCost() {
		double cost = 750 + (0.25 * (double)this.getTruckSize());
		return cost;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public Double getTemp() {
		return null;
	}

}
