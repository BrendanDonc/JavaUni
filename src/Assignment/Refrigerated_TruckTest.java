package Assignment;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.internal.JUnitSystem;

public class Refrigerated_TruckTest {

	/* Test 0: Declaring R-Truck objects */
	Refrigerated_Truck rTruck;
	
	/* Test 1: Constructing an rTruck object*/
	@Before
	@Test
	public void setUpRTruck() {
		rTruck = new Refrigerated_Truck();
	}

	/* Test 1: Calculating the refrigerated truck cost */
	@Test
	public void CalcRTruckCost() {
		int actualTemp = -5;
		int actualCost = 900 + 200 * 0.7^(actualTemp/5); //check how to do powers in java??
		assertEquals(rTruck.truckCost(actualTemp), actualCost);
	} 
	
	/* Test 2: Add one kind of item to truck cargo */
	@Test
	public void AddAnItem() {
		rTruck.AddItem("Ice Cream", 52);
		assertEquals("52", rTruck.getCargoItem("Ice Cream"));
	}
	
	/* Test 2: Add multiple kinds of item to truck cargo */
	@Test
	public void AddAnItem() {
		rTruck.AddItem("Ice Cream", 128);
		rTruck.AddItem("Milk", 67);
		rTruck.AddItem("Banana", 52);
		assertEquals(128, rTruck.getCargoItem("Ice Cream"));
		assertEquals(67, rTruck.getCargoItem("Milk"));
		assertEquals(52, rTruck.getCargoItem("Banana"));
	}
	
	/* Test 3: Removing an item from the truck cargo */
	
	/* Test 4: Adding items and removing items from multiple trucks */
	
	
	/* Test : Checking the truck cargo has the correct number of items */
	@Test
	public void CheckCapacity() {
		int actualCapacity = 612;
		assertEquals(rTruck.truckCapacity(), actualCapacity);
	}
	
	/* Test : Checking the truck cargo has not exceeded capacity */
	@Test
	public void TestCapacity() throws CapacityException {
		int actualCapacity = 947;
		int maxCapacity = 800;
		
		rTruck.truckCapacity();
	}
}
