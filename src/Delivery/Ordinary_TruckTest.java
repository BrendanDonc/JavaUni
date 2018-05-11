package Delivery;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;
import org.junit.internal.JUnitSystem;

import Stock.Item;

public class Ordinary_TruckTest {
	
	//Declaring items to be used in tests
		Item rice = new Item("Rice", 10, 15, 200, 300);
		Item biscuits = new Item("Biscuits", 10, 15, 200, 300);
		Item almonds = new Item("Almonds", 10, 15, 200, 300);
		Item cannedSpag = new Item("Canned Spaghetti", 10, 15, 200, 300);
		Item cereal = new Item("Cereal", 10, 15, 200, 300);
		Item muffins = new Item("Muffins", 10, 15, 200, 300);
		Item bread = new Item("Bread", 10, 15, 200, 300);
		Item chips = new Item("Chips", 10, 15, 200, 300);
		
		Item iceCream = new Item("Ice Cream", 10, 15, 200, 300, (double) -5);

	/* Test 0: Declaring O-Truck objects */
	Truck oTruck;
	Truck oTruck_02;
	Truck oTruck_03;
	
	/* Test 1: Constructing an oTruck object*/
	@Before
	@Test
	public void setUpOTruck() {
		oTruck = new Ordinary_Truck();
		oTruck_02 = new Ordinary_Truck();
		oTruck_03 = new Ordinary_Truck();
	}

	
	/* Test 2: Add one kind of item to truck cargo */
	@Test
	public void addAnItem() throws DeliveryException {
		oTruck.addItem(rice, 52);
		assertEquals(52, oTruck.getCargoItem(rice));
	}
	
	/* Test 3: Add multiple kinds of item to truck cargo */
	@Test
	public void addMultipleItems() throws DeliveryException {
		oTruck.addItem(biscuits, 128);
		oTruck.addItem(almonds, 67);
		oTruck.addItem(cannedSpag, 52);
		assertEquals(128, oTruck.getCargoItem(biscuits));
		assertEquals(67, oTruck.getCargoItem(almonds));
		assertEquals(52, oTruck.getCargoItem(cannedSpag));
	}

	/* Test 4: Calculating the ordinary truck cost */
	@Test
	public void calcOTruckCost() {
		int truckSize = oTruck.getTruckSize(); 
		double actualCost = 750 + (25 * truckSize);
		
		assertEquals(oTruck.truckCost(), actualCost, 0.1);
	} 
	
	/* Test 5: Removing an item from the truck cargo */
	@Test
	public void removeAnItem() {
		oTruck.removeItem(rice, 52);
		assertEquals(0, oTruck.getCargoItem(rice));
	}
	
	
	/* Test 6: Adding items and removing items from multiple trucks */
	@Test
	public void manageMultipleTrucks() throws DeliveryException {
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
	public void checkTruckSize() {
		int actualSize = 228;
		assertEquals(actualSize, oTruck_02.getTruckSize());
	}

	/* Test 8: Checking the truck is not empty */
	@Test
	public void emptyTruck(){
		oTruck.getTruckSize();
	}
	
	/* Test 9: Checking the truck cargo has not exceeded capacity */
	@Test
	public void maxCapacity() throws DeliveryException {
		oTruck.addItem(rice, 1000);
		
		oTruck.getTruckSize();
	}
	
	/* Test 10: Checking there are no temperature-controlled items in the truck */
	@Test
	public void checkForColdItems() throws DeliveryException {
		oTruck.addItem(iceCream, 50);
	}
	
	
	/* Test : Gets the truck type */
	public void checkTruckType() {
		String actualTruckType = "Refrigerated Truck";
		
		assertEquals(actualTruckType, oTruck.getTruckType());
	}
}
