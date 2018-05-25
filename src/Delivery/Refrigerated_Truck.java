/**
 * This file forms part of the Inventory Management Application Project
 * Assignment Two - CAB302 2018
 * 
 * Developed by Brendan Doncaster and Mary Millar
 * 
 */

package Delivery;

import Stock.*;

/**
 * Refrigerated Truck class that extends the abstract class Truck. Used for
 * implementing a truck that can store refrigerated items within its
 * cargo(Stock)
 * 
 * @author Brendan Doncaster
 * @see Truck
 * @see Ordinary_Truck
 */
public class Refrigerated_Truck extends Truck {

    /**
     * Constructor method for a refrigerated truck
     * 
     * @author Brendan Doncaster
     * @see Truck
     * @see Ordinary_Truck
     */
    public Refrigerated_Truck() {
        this.cargo = new Stock();
        this.truckType = "Refrigerated";
        this.capacity = 800;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(Item item, int amount) throws DeliveryException, StockException {
        if (item.getTemp() != null && (item.getTemp() > 10 || item.getTemp() < -20)) {
            throw new DeliveryException("Added item requires a temperature outside of truck capabilities");
        } else {
            if (this.getTruckSize() + amount > this.truckCapacity()) {
                throw new DeliveryException("Added amount exceeds truck capacity");
            } else {
                try {
                    cargo.addItem(item, amount);
                } catch (StockException e) {
                    throw e;
                }
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItemName(String string, int amount) throws DeliveryException, StockException {
        Item stringItem;
        try {
            stringItem = Stock.getItemFromName(string);
        } catch (StockException e) {
            throw e;
        }

        if (stringItem.getTemp() != null && (stringItem.getTemp() > 10 || stringItem.getTemp() < -20)) {
            throw new DeliveryException("Added item requires a temperature outside of truck capabilities");
        } else {
            if (this.getTruckSize() + amount > this.truckCapacity()) {
                throw new DeliveryException("Added amount exceeds truck capacity");
            } else {
                cargo.addItem(stringItem, amount);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double truckCost() throws DeliveryException {
        double temp;
        try {
            temp = this.getTemp();
        } catch (DeliveryException e) {
            throw e;
        }
        double cost = 900.00 + 200.00 * java.lang.Math.pow(0.7, temp / 5);
        return cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getTemp() throws DeliveryException {
        if (this.getCargo().getListCold().length == 0) {
            throw new DeliveryException("Refrigerated truck has no refrigerated items");
        } else {
            return this.getCargo().getColdestTemp();
        }
    }

}
