package Delivery;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.internal.JUnitSystem;

import Stock.Item;


public class Refrigerated_TruckTest {
	
	//Declaring items to be used in tests
	Item pork = new Item("Pork", 10, 15, 200, 300, (double) -3);
	Item iceCream = new Item("Ice Cream", 10, 15, 200, 300, (double) -5);
	Item milk = new Item("Milk", 10, 15, 200, 300, (double) 2);
	Item banana = new Item("Banana", 10, 15, 200, 300, (double) 4);
	Item yoghurt = new Item("Yoghurt", 10, 15, 200, 300, (double) 2);
	Item chicken = new Item("Chicken", 10, 15, 200, 300, (double) -2);
	Item peas = new Item("Peas", 10, 15, 200, 300, (double) -20);

	/* Test 0: Declaring R-Truck objects */
	Truck rTruck;
	Truck rTruck_02;
	Truck rTruck_03;

	
	/* Test 1: Constructing an rTruck object*/
	@Before
	@Test
	public void setUpRTruck() {
		rTruck = new Refrigerated_Truck();
		rTruck_02 = new Refrigerated_Truck();
		rTruck_03 = new Refrigerated_Truck();
	}
	
	/* Test 2: Add one kind of item to truck cargo */
	@Test(expected = DeliveryException.class)
	public void addAnItem() {
		int testQuantity = 52;
		
		rTruck.addItem(pork, 52);
		assertEquals(testQuantity, rTruck.getCargoItem(pork));
	}
	
	/* Test 3: Add multiple kinds of item to truck cargo */
	@Test(expected = DeliveryException.class)
	public void addMultipleItems() {
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
	@Test public void calcTruckTemp() {
		int actualTemp = -5;
		assertEquals(actualTemp, rTruck.getTemp()); //getTemp will need to get the lowest temperature from the items added
	}
	
	
	/* Test 5: Calculating the refrigerated truck cost */
	@Test
	public void calcRTruckCost() {
		int actualTemp = -5;
		double actualCost = Math.pow(900.0 + 200.0 * 0.7, actualTemp/5.0);
		assertEquals(rTruck.truckCost(actualTemp), actualCost);
	} 
	
	
	/* Test 6: Removing an item from the truck cargo */
	@Test(expected = DeliveryException.class)
	public void removeAnItem() {
		rTruck.removeItem(iceCream, 128); //will remove all ice cream items
		assertEquals(0, rTruck.getCargoItem(iceCream));
	}
	
	/* Test 7: Adding items and removing items from multiple trucks */
	@Test(expected = DeliveryException.class)
	public void manageMultipleTrucks() {
		rTruck_02.addItem(yoghurt, 128);
		rTruck_02.addItem(peas, 132);
		rTruck_03.addItem(chicken, 465);
		rTruck_03.addItem(milk, 243);
		
		rTruck_02.removeItem(peas, 32);
		rTruck_03.removeItem(chicken, 65);
		
		assertEquals(128, rTruck_02.getCargoItem(yoghurt));
		assertEquals(100, rTruck_02.getCargoItem(peas));
		assertEquals(400, rTruck_03.getCargoItem(chicken));
		assertEquals(243, rTruck_03.getCargoItem(milk));
	}
	
	/* Test 8: Checking the truck cargo has the correct number of items */
	@Test
	public void checkCapacity() {
		int actualCapacity = 171;
		assertEquals(rTruck.truckCapacity(), actualCapacity);
	}
	
	/* Test 9: Checking the truck cargo has not exceeded capacity */
	@Test (expected = DeliveryException.class)
	public void maxCapacity() {
		rTruck.addItem(iceCream, 776);
		
		rTruck.truckCapacity();
	}
	
	/* Test 10: Checking the truck is not empty */
	@Test (expected = DeliveryException.class)
	public void emptyTruck() {
		rTruck.removeItem(iceCream, 904);
		rTruck.removeItem(milk, 67);
		rTruck.removeItem(banana, 52);
		
		rTruck.truckCapacity();
	}
	
	/* Test 11: Checking the temperature of the truck is safe (ie. has not exceeded either limit) */
	@Test(expected = DeliveryException.class)
	public void checkTemp() {
		rTruck.addItem(peas, 5);
		double newTemp = -20.0;
		assertEquals("Truck temperature is unsafe", newTemp, rTruck.getTemp());
	}
	
	/* Test 12: Checking that at there is least 1 temperature-controlled item in cargo */
	@Test
	public void getMinTemp() {
		int actualMinTemp = -5;
		
		assertEquals(actualMinTemp, rTruck.getColdestTemp());
		
	}
	
	/* Test : Gets the truck type */
	public void getTruckType() {
		String actualTruckType = "Refrigerated Truck";
		
		assertEquals(actualTruckType, rTruck.truckType());
	}
	
}
