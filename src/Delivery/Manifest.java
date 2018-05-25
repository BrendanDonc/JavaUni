/**
 * This file forms part of the Inventory Management Application Project
 * Assignment Two - CAB302 2018
 * 
 * Developed by Brendan Doncaster and Mary Millar
 * 
 */

package Delivery;

import java.util.ArrayList;
import Stock.*;

/**
 * A manifest class used for storing trucks that contain stocks of items that
 * need to be delivered
 *
 * @author Brendan Doncaster
 * @see Truck
 * @see Stock
 * @see Item
 */
public class Manifest {
    private ArrayList<Truck> manifest;

    /**
     * Manifest constructor class, creates a new array list of trucks
     * 
     * @see Truck
     * @author Brendan Doncaster
     */
    public Manifest() {
        manifest = new ArrayList<Truck>();
    }

    /**
     * Add a truck to the manifest's list
     * 
     * @param truck
     * @author Brendan Doncaster
     */
    public void addTruck(Truck truck) {
        manifest.add(truck);
    }

    /**
     * Getter method for returning the manifest's list as an array
     * 
     * @return An array of trucks from the manifest
     * @author Brendan Doncaster
     */
    public Truck[] returnManifest() {
        Truck[] manifestArray = new Truck[manifest.size()];
        this.manifest.toArray(manifestArray);
        return manifestArray;
    }

    /**
     * Getter method for the printable version of the manifest, follows the style of
     * a '>' before each truck and a new line after each truck/item
     * 
     * @return String version of the Trucks and their contents
     * @author Brendan Doncaster
     */
    public String printManifest() {
        String print = "";
        if (manifest.isEmpty()) {
            return print;
        } else {

            for (Truck truck : manifest) {
                print = print + ">" + truck.getTruckType() + "\n";
                print = print + truck.getCargo().getManifestPrintStyle();
            }
            return print;
        }
    }

    /**
     * Create a new truck and add it to the manifest based on a string
     * representation of the truck type
     * 
     * @param string
     *            String representation of a truck type
     * @return Truck that was just added to the manifest
     * @throws DeliveryException
     *             when the input truck type is not recognized
     * @author Brendan Doncaster
     */
    public Truck importTruck(String string) throws DeliveryException {
        Truck importTruck;

        if (string.substring(0, 1).equals(">")) {
            string = string.substring(1);
        }

        if (string.equals("Ordinary")) {
            importTruck = new Ordinary_Truck();
        }

        else if (string.equals("Refrigerated")) {
            importTruck = new Refrigerated_Truck();
        } else {
            throw new DeliveryException("Unrecognised truck type '" + string + "'");
        }
        manifest.add(importTruck);
        return importTruck;
    }

    /**
     * Creates an optimal manifest based on the store's inventory and which items
     * need to be reordered.
     * 
     * @return Manifest to be exported for reordering that has been optimally sorted
     * @throws DeliveryException
     *             when adding an item to a truck throws a DeliveryException
     * @throws StockException
     *             when subtracting the already added items from the stock that
     *             needs to be reordered throws a StockException
     * @author Brendan Doncaster
     */
    public static Manifest manifestToExport() throws DeliveryException, StockException {
        Item[] exportItems = Store.getInstance().getInventory().needsReorder();
        Stock exportStock = new Stock();
        for (Item item : exportItems) {
            exportStock.addItem(item, item.getReAmount());
        }

        Item[] cold = exportStock.getListCold();
        Item[] ordinary = exportStock.getListOrdinary();

        Manifest exportManifest = new Manifest();
        boolean coldsLeft = true;
        boolean singleCold = true;
        int currentRefTruck = 0;
        int amount;

        // Adding in the cold items
        while (coldsLeft) {

            singleCold = false;

            for (Item item : cold) {
                if (exportStock.getAmount(item) > 0) {
                    singleCold = true;
                }
            }

            coldsLeft = singleCold;

            // If current truck does not exist, create a new one
            if (exportManifest.returnManifest().length == currentRefTruck) {
                if (coldsLeft) {
                    exportManifest.addTruck(new Refrigerated_Truck());
                }
            }
            if (coldsLeft) {
                Truck currentTruck = exportManifest.returnManifest()[currentRefTruck];

                // If truck has space, start adding items
                if (currentTruck.getRemainingCapacity() > 0) {

                    // Go through all cold items
                    for (Item item : cold) {
                        // Check if truck still has space for items at this point
                        if (currentTruck.getRemainingCapacity() > 0) {

                            // Check if item still has an amount to add
                            if (exportStock.getAmount(item) > 0) {

                                // If truck has space for all of the item, add it all in
                                if (exportStock.getAmount(item) < currentTruck.getRemainingCapacity()) {
                                    amount = exportStock.getAmount(item);
                                    try {
                                        currentTruck.addItem(item, amount);
                                    } catch (DeliveryException e1) {
                                        throw e1;
                                    }
                                    try {
                                        exportStock.subtractItem(item, amount);
                                    } catch (StockException e) {
                                        throw e;
                                    }

                                }
                                // If not enough space for all of item, just fill remainder of truck
                                else {
                                    amount = currentTruck.getRemainingCapacity();
                                    try {
                                        currentTruck.addItem(item, amount);
                                    } catch (DeliveryException e1) {
                                        throw e1;
                                    }
                                    try {
                                        exportStock.subtractItem(item, amount);
                                    } catch (StockException e) {
                                        throw e;
                                    }

                                }
                            }
                        }
                    }
                }

                // If truck does not have space, move on to the next truck
                else {
                    currentRefTruck++;
                }
            }

        } // Finish adding cold items

        boolean ordsLeft = true;
        boolean singleOrdinary = true;

        // Adding in the ordinary items
        while (ordsLeft) {

            singleOrdinary = false;

            for (Item item : ordinary) {
                if (exportStock.getAmount(item) > 0) {
                    singleOrdinary = true;
                }
            }

            ordsLeft = singleOrdinary;

            // If current truck does not exist, create a new one
            if (exportManifest.returnManifest().length == currentRefTruck) {
                if (ordsLeft) {
                    exportManifest.addTruck(new Ordinary_Truck());
                }
            }
            if (ordsLeft) {
                Truck currentTruck = exportManifest.returnManifest()[currentRefTruck];

                // If truck has space, start adding items
                if (currentTruck.getRemainingCapacity() > 0) {

                    // Go through all ordinary items
                    for (Item item : ordinary) {

                        // Check if truck still has space for items at this point
                        if (currentTruck.getRemainingCapacity() > 0) {

                            // Check if item still has an amount to add
                            if (exportStock.getAmount(item) > 0) {

                                // If truck has space for all of the item, add it all in
                                if (exportStock.getAmount(item) < currentTruck.getRemainingCapacity()) {
                                    amount = exportStock.getAmount(item);
                                    try {
                                        currentTruck.addItem(item, amount);
                                    } catch (DeliveryException e1) {
                                        throw e1;
                                    }
                                    try {
                                        exportStock.subtractItem(item, amount);
                                    } catch (StockException e) {
                                        throw e;
                                    }

                                }
                                // If not enough space for all of item, just fill remainder of truck
                                else {
                                    amount = currentTruck.getRemainingCapacity();
                                    try {
                                        currentTruck.addItem(item, amount);
                                    } catch (DeliveryException e1) {
                                        throw e1;
                                    }
                                    try {
                                        exportStock.subtractItem(item, amount);
                                    } catch (StockException e) {
                                        throw e;
                                    }
                                }

                            }
                        }
                    }
                }
                // If truck does not have space, move on to the next truck
                else {
                    currentRefTruck++;
                }
            }
        }
        return exportManifest;

    }

    /**
     * Sum the cost of all trucks and items within the manifest
     * 
     * @return double value for the cost of the manifest
     * @throws DeliveryException
     *             Manifest contains a refrigerated truck with no refrigerated items
     *             and therefore has no cost
     * @author Brendan Doncaster
     */
    public double sumManifestCost() throws DeliveryException {
        double sumAmount = 0;
        for (Truck truck : this.returnManifest()) {
            try {
                sumAmount += truck.truckCost();
            } catch (DeliveryException e) {
                throw e;
            }

            sumAmount += truck.getCargo().sumCosts();
        }

        return sumAmount;
    }

}
