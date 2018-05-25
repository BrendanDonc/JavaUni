package Delivery;

import static org.junit.Assert.*;
import org.junit.*;
import Stock.*;

public class Ordinary_TruckTest {

    // Declaring items to be used in tests
    Item rice;
    Item biscuits;
    Item almonds;
    Item cannedSpag;
    Item cereal;
    Item muffins;
    Item bread;
    Item chips;

    Item iceCream;

    Stock inventory;

    /* Test 0: Declaring O-Truck objects */
    Truck oTruck;
    Truck oTruck_02;
    Truck oTruck_03;

    /* Test 1: Constructing an oTruck object */
    @Before
    @Test
    public void setUpOTruck() {
        oTruck = new Ordinary_Truck();
        oTruck_02 = new Ordinary_Truck();
        oTruck_03 = new Ordinary_Truck();

        try {
            rice = new Item("Rice", 10, 15, 200, 300);
            biscuits = new Item("Biscuits", 10, 15, 200, 300);
            almonds = new Item("Almonds", 10, 15, 200, 300);
            cannedSpag = new Item("Canned Spaghetti", 10, 15, 200, 300);
            cereal = new Item("Cereal", 10, 15, 200, 300);
            muffins = new Item("Muffins", 10, 15, 200, 300);
            bread = new Item("Bread", 10, 15, 200, 300);
            chips = new Item("Chips", 10, 15, 200, 300);

            iceCream = new Item("Ice Cream", 10, 15, 200, 300, (double) -5);

            inventory = new Stock();
            inventory.addItem(rice, 0);
            inventory.addItem(iceCream, 0);
            Store.getInstance().setInventory(inventory);
        } catch (StockException badParam) {
            fail("Ordinary Truck items failed to initialise.");
        }
    }

    /* Test 2: Add one kind of item to truck cargo */
    @Test
    public void addAnItem() throws DeliveryException, StockException {
        oTruck.addItem(rice, 52);
        assertEquals(52, oTruck.getCargoItem(rice));
    }

    /* Test 3: Add multiple kinds of item to truck cargo */
    @Test
    public void addMultipleItems() throws DeliveryException, StockException {
        oTruck.addItem(biscuits, 128);
        oTruck.addItem(almonds, 67);
        oTruck.addItem(cannedSpag, 52);
        assertEquals(128, oTruck.getCargoItem(biscuits));
        assertEquals(67, oTruck.getCargoItem(almonds));
        assertEquals(52, oTruck.getCargoItem(cannedSpag));
    }

    /* Test 4: Calculating the ordinary truck cost */
    @Test
    public void calcOTruckCost() throws DeliveryException {
        int truckSize = oTruck.getTruckSize();
        double actualCost = 750 + (0.25 * truckSize);

        assertEquals(oTruck.truckCost(), actualCost, 0.1);
    }

    /* Test 5: Removing an item from the truck cargo */
    @Test
    public void removeAnItem() throws StockException, DeliveryException {
        oTruck.addItem(rice, 100);
        oTruck.removeItem(rice, 52);
        assertEquals(100 - 52, oTruck.getCargoItem(rice));
    }

    /* Test 6: Adding items and removing items from multiple trucks */
    @Test
    public void manageMultipleTrucks() throws DeliveryException, StockException {
        oTruck_02.addItem(cereal, 128);
        oTruck_02.addItem(muffins, 132);
        oTruck_03.addItem(bread, 465);
        oTruck_03.addItem(chips, 243);

        oTruck_02.removeItem(muffins, 32);
        oTruck_03.removeItem(bread, 65);

        assertEquals(128, oTruck_02.getCargoItem(cereal));
        assertEquals(100, oTruck_02.getCargoItem(muffins));
        assertEquals(400, oTruck_03.getCargoItem(bread));
        assertEquals(243, oTruck_03.getCargoItem(chips));
    }

    /* Test 7: Checking the truck cargo has the correct number of items */
    @Test
    public void checkTruckSize() throws DeliveryException, StockException {
        oTruck.addItem(rice, 50);
        oTruck.addItem(muffins, 80);
        int actualSize = 50 + 80;
        assertEquals(actualSize, oTruck.getTruckSize());
    }

    /* Test 8: Checking the truck is not empty */
    @Test
    public void emptyTruck() {
        oTruck.getTruckSize();
    }

    /* Test 9: Checking the truck cargo has not exceeded capacity */
    @Test(expected = DeliveryException.class)
    public void maxCapacity() throws DeliveryException, StockException {
        oTruck.addItem(rice, 1100);

        oTruck.getTruckSize();
    }

    /* Test 10: Checking there are no temperature-controlled items in the truck */
    @Test(expected = DeliveryException.class)
    public void checkForColdItems() throws DeliveryException, StockException {
        oTruck.addItem(iceCream, 50);
    }

    /* Test 11 : Gets the truck type */
    @Test
    public void checkTruckType() {
        String actualTruckType = "Ordinary";

        assertEquals(actualTruckType, oTruck.getTruckType());
    }

    /* Test 12 : Gets the truck temp */
    @Test
    public void checkTruckTemp() throws DeliveryException {
        Double temp = null;

        assertEquals(temp, oTruck.getTemp());
    }

    /* Test 13: Checking you are not adding negative of an item */
    @Test(expected = StockException.class)
    public void checkBadAddItem() throws DeliveryException, StockException {
        oTruck.addItem(rice, -1);
    }

    /* Test 14: Adds an item to the truck by its name */
    @Test
    public void checkAddItemName() throws DeliveryException, StockException {
        oTruck.addItemName("Rice", 10);
    }

    /* Test 15: Checking the items name is valid */
    @Test(expected = StockException.class)
    public void checkAddItemBadName() throws DeliveryException, StockException {
        oTruck.addItemName("rice", 10);
    }

    /* Test 16: Checking there are no temperature-controlled items in the truck */
    @Test(expected = DeliveryException.class)
    public void checkAddItemNameTemp() throws DeliveryException, StockException {
        oTruck.addItemName("Ice Cream", 10);
    }

    /* Test 17: Checking the truck cargo has not exceeded capacity */
    @Test(expected = DeliveryException.class)
    public void checkAddItemNameAmount() throws DeliveryException, StockException {
        oTruck.addItemName("Rice", 1100);
    }

}
