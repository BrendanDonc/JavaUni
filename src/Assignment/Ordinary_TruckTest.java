package Assignment;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;
import org.junit.internal.JUnitSystem;

public class Ordinary_TruckTest {

	/* Test 0: Declaring O-Truck objects */
	Ordinary_Truck oTruck;
	Ordinary_Truck oTruck_02;
	Ordinary_Truck oTruck_03;
	
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
	public void addAnItem() {
		oTruck.addItem("Rice", 52);
		assertEquals(52, oTruck.getCargoItem("Rice"));
	}
	
	/* Test 3: Add multiple kinds of item to truck cargo */
	@Test
	public void addMultipleItems() {
		oTruck.addItem("Biscuits", 128);
		oTruck.addItem("Almonds", 67);
		oTruck.addItem("Canned Spaghetti", 52);
		assertEquals(128, oTruck.getCargoItem("Biscuits"));
		assertEquals(67, oTruck.getCargoItem("Almonds"));
		assertEquals(52, oTruck.getCargoItem("Canned Spaghetti"));
	}

	/* Test 4: Calculating the ordinary truck cost */
	@Test
	public void calcOTruckCost() {
		int truckSize = oTruck.getTruckSize(); 
		double actualCost = 750 + (25 * truckSize);
		
		assertEquals(oTruck.truckCost(), actualCost);
	} 
	
	/* Test 5: Removing an item from the truck cargo */
	@Test
	public void removeAnItem() {
		oTruck.removeItem("Rice", 52);
		assertEquals(0, oTruck.getCargoItem("Rice"));
	}
	
	
	/* Test 6: Adding items and removing items from multiple trucks */
	@Test
	public void manageMultipleTrucks() {
		oTruck_02.addItem("Cereal", 128);
		oTruck_02.addItem("Muffins", 132);
		oTruck_03.addItem("Bread", 465);
		oTruck_03.addItem("Chips", 243);
		
		oTruck_02.removeItem("Muffins", 32);
		oTruck_03.removeItem("Bread", 65);
		
		assertEquals(128, oTruck_02.getCargoItem("Cereal"));
		assertEquals(100, oTruck_02.getCargoItem("Muffins"));
		assertEquals(400, oTruck_03.getCargoItem("Bread"));
		assertEquals(243, oTruck_03.getCargoItem("Chips"));
	}
	
	/* Test 7: Checking the truck cargo has the correct number of items */
	@Test
	public void checkTruckSize() {
		int actualSize = 228;
		assertEquals(actualSize, oTruck_02.getTruckSize());
	}

	/* Test 8: Checking the truck is not empty */
	@Test
	public void emptyTruck() throws CapacityException {
		oTruck.getTruckSize();
	}
	
	/* Test 9: Checking the truck cargo has not exceeded capacity */
	@Test
	public void maxCapacity() throws CapacityException {
		oTruck.addItem("Rice", 1000);
		
		oTruck.getTruckSize();
	}
	
	/* Test 10: Checking there are no temperature-controlled items in the truck */
}
