package Delivery;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import Stock.*;


public class Refrigerated_TruckTest {
	
	//Declaring items to be used in tests
	Item pork;
	Item iceCream;
	Item milk;
	Item banana;
	Item yoghurt;
	Item chicken;
	Item peas;
	Item rice;
	
	Stock inventory;

	/* Test 0: Declaring R-Truck objects */
	Truck rTruck;
	Truck rTruck_02;
	Truck rTruck_03;

	
	/* Test 1: Constructing an rTruck object*/
	@Before
	@Test
	public void setUpRTruck() throws StockException {
		pork = new Item("Pork", 10, 15, 200, 300, (double) -3);
		iceCream = new Item("Ice Cream", 10, 15, 200, 300, (double) -5);
		milk = new Item("Milk", 10, 15, 200, 300, (double) 2);
		banana = new Item("Banana", 10, 15, 200, 300, (double) 4);
		yoghurt = new Item("Yoghurt", 10, 15, 200, 300, (double) 2);
		chicken = new Item("Chicken", 10, 15, 200, 300, (double) -2);
		
		rice = new Item("Rice", 10, 15, 200, 300);
		
		rTruck = new Refrigerated_Truck();
		rTruck_02 = new Refrigerated_Truck();
		rTruck_03 = new Refrigerated_Truck();
		
		inventory = new Stock();
        inventory.addItem(rice, 0);
        inventory.addItem(iceCream, 0);
        Store.getInstance().setInventory(inventory);
	}
	
	/* Test 2: Add one kind of item to truck cargo */
	@Test
	public void addAnItem() throws DeliveryException, StockException {
		int testQuantity = 52;
		
		rTruck.addItem(pork, 52);
		assertEquals(testQuantity, rTruck.getCargoItem(pork));
	}
	
	/* Test 3: Add multiple kinds of item to truck cargo */
	@Test
	public void addMultipleItems() throws DeliveryException, StockException {
		int iceCreamQuantity = 128;
		int milkQuantity = 67;
		int bananaQuantity = 52;
		rTruck.addItem(iceCream, 128);
		rTruck.addItem(milk, 67);
		rTruck.addItem(banana, 52);
		assertEquals(iceCreamQuantity, rTruck.getCargoItem(iceCream));
		assertEquals(milkQuantity, rTruck.getCargoItem(milk));
		assertEquals(bananaQuantity, rTruck.getCargoItem(banana));
	}

	/*Test 4: Get the temperature of the truck */
	@Test public void calcTruckTemp() throws DeliveryException, StockException {
		rTruck.addItem(iceCream, 50);
		double actualTemp = -5;
		assertEquals(actualTemp, rTruck.getTemp(), 0.1); //getTemp will need to get the lowest temperature from the items added
	}
	
	
	/* Test 5: Calculating the refrigerated truck cost */
	@Test
	public void calcRTruckCost() throws DeliveryException, StockException {
		rTruck.addItem(iceCream, 50);
		int actualTemp = -5;
		double actualCost = 900.0 + 200.0 *Math.pow(0.7, actualTemp/5.0);
		assertEquals(rTruck.truckCost(), actualCost, 0.1);
	} 
	
	
	/* Test 6: Removing an item from the truck cargo */
	@Test
	public void removeAnItem() throws StockException, DeliveryException {
		rTruck.addItem(iceCream, 128);
		rTruck.removeItem(iceCream, 128); //will remove all ice cream items
		assertEquals(0, rTruck.getCargoItem(iceCream));
	}
	
	/* Test 7: Adding items and removing items from multiple trucks */
	@Test
	public void manageMultipleTrucks() throws DeliveryException, StockException {
		rTruck_02.addItem(yoghurt, 128);
		rTruck_03.addItem(chicken, 465);
		rTruck_03.addItem(milk, 243);
		
		rTruck_03.removeItem(chicken, 65);
		
		assertEquals(128, rTruck_02.getCargoItem(yoghurt));
		assertEquals(400, rTruck_03.getCargoItem(chicken));
		assertEquals(243, rTruck_03.getCargoItem(milk));
	}
	
	/* Test 8: Checking the truck cargo has the correct number of items */
	@Test
	public void checkCapacity() {
		int actualCapacity = 800;
		assertEquals(rTruck.truckCapacity(), actualCapacity);
	}
	
	/* Test 9: Checking the truck cargo has not exceeded capacity */
	@Test
	public void maxCapacity() throws DeliveryException, StockException {
		rTruck.addItem(iceCream, 776);
		
		rTruck.truckCapacity();
	}
	
	/* Test 10: Checking the truck is not empty */
	@Test(expected = StockException.class)
	public void emptyTruck() throws StockException {
		rTruck.removeItem(iceCream, 904);
		rTruck.removeItem(milk, 67);
		rTruck.removeItem(banana, 52);
		
		rTruck.truckCapacity();
	}
	
	/* Test 11: Checking the temperature of the truck is safe (ie. has not exceeded either limit) */
	@Test(expected = StockException.class)
	public void checkTemp() throws DeliveryException, StockException {
	    peas = new Item("Peas", 10, 15, 200, 300, (double) -21);
		rTruck.addItem(peas, 5);
	}
	
	/* Test 12: Checking that at there is least 1 temperature-controlled item in cargo */
	@Test
	public void getMinTemp() throws DeliveryException, StockException {
		Double actualMinTemp = (double) -5;
		rTruck.addItem(iceCream, 50);
		assertEquals(actualMinTemp, rTruck.getTemp());
		
	}
	
	/* Test 13 : Gets the truck type */
	@Test
	public void getTruckType() {
		String actualTruckType = "Refrigerated";
		
		assertEquals(actualTruckType, rTruck.getTruckType());
	}
	
	/* Test 14: Adds an item to the truck by its name*/
    @Test
    public void checkAddItemName() throws DeliveryException, StockException {
        rTruck.addItemName("Rice", 10);
    }
    
    /* Test 15: Checking the items name is valid */
    @Test (expected = StockException.class)
    public void checkAddItemBadName() throws DeliveryException, StockException {
        rTruck.addItemName("rice", 10);
    }

    /* Test 16: Checking the truck cargo has not exceeded capacity */
    @Test (expected = DeliveryException.class)
    public void checkAddItemNameAmount() throws DeliveryException, StockException {
        rTruck.addItemName("Rice", 1100);
    }
    
    /* Test 17: Checking the truck has a refrigerated item before returning temperature */
    @Test (expected = DeliveryException.class)
    public void checkTempWithoutTemp() throws DeliveryException, StockException {
        Truck oTruck = new Refrigerated_Truck();
        oTruck.addItem(rice, 0);
        oTruck.getTemp();
    }
    
    /* Test 18: Checking the truck has a refrigerated item before calculating the cost */
    @Test (expected = DeliveryException.class)
    public void checkCostWithoutTemp() throws DeliveryException, StockException {
        Truck oTruck = new Refrigerated_Truck();
        oTruck.addItem(rice, 0);
        oTruck.truckCost();
    }
    
    /* Test 19: Checking the truck cargo has not exceeded capacity */
    @Test (expected = DeliveryException.class)
    public void checkAddItemAmount() throws DeliveryException, StockException {
        rTruck.addItem(pork, 810);
    }
	
}
