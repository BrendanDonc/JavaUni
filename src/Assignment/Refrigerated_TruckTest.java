package Assignment;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.internal.JUnitSystem;


public class Refrigerated_TruckTest {

	/* Test 0: Declaring R-Truck objects */
	Refrigerated_Truck rTruck;
	Refrigerated_Truck rTruck_02;
	Refrigerated_Truck rTruck_03;
	
	/* Test 1: Constructing an rTruck object*/
	@Before
	@Test
	public void setUpRTruck() {
		rTruck = new Refrigerated_Truck();
		rTruck_02 = new Refrigerated_Truck();
		rTruck_03 = new Refrigerated_Truck();
	}
	
	/* Test 2: Add one kind of item to truck cargo */
	@Test
	public void addAnItem() {
		rTruck.addItem("Pork", 52, -3.0);
		assertEquals("52", rTruck.getCargoItem("Pork"));
	}
	
	/* Test 3: Add multiple kinds of item to truck cargo */
	@Test
	public void addMultipleItems() {
		rTruck.addItem("Ice Cream", 128, -5.0);
		rTruck.addItem("Milk", 67, 2.0);
		rTruck.addItem("Banana", 52, 4.0);
		assertEquals(128, rTruck.getCargoItem("Ice Cream"));
		assertEquals(67, rTruck.getCargoItem("Milk"));
		assertEquals(52, rTruck.getCargoItem("Banana"));
	}

	/*Test 4: Get the temperature of the truck */
	@Test public void calcTruckTemp() {
		double actualTemp = -5.0;
		assertEquals(actualTemp, rTruck.getTemp()); //getTemp will need to get the lowest temperature from the items added
	}
	
	
	/* Test 5: Calculating the refrigerated truck cost */
	@Test
	public void calcRTruckCost() {
		double actualTemp = -5.0;
		double actualCost = Math.pow(900.0 + 200.0 * 0.7, actualTemp/5.0);
		assertEquals(rTruck.truckCost(actualTemp), actualCost);
	} 
	
	
	/* Test 6: Removing an item from the truck cargo */
	public void removeAnItem() {
		rTruck.removeItem("Ice Cream", 128);
		assertEquals(0, rTruck.getCargoItem("Ice Cream"));
	}
	
	/* Test 7: Adding items and removing items from multiple trucks */
	public void manageMultipleTrucks() {
		rTruck_02.addItem("Beef", 128, -2.0);
		rTruck_02.addItem("Salmon", 132, -2.0);
		rTruck_03.addItem("Yoghurt", 465, 2.0);
		rTruck_03.addItem("Milk", 243, 3.0);
		
		rTruck_02.removeItem("Salmon", 32);
		rTruck_03.removeItem("Yoghurt", 65);
		
		assertEquals(128, rTruck_02.getCargoItem("Beef"));
		assertEquals(100, rTruck_02.getCargoItem("Salmon"));
		assertEquals(400, rTruck_03.getCargoItem("Yoghurt"));
		assertEquals(243, rTruck_03.getCargoItem("Milk"));
	}
	
	/* Test 8: Checking the truck cargo has the correct number of items */
	@Test
	public void checkCapacity() {
		int actualCapacity = 171;
		assertEquals(rTruck.truckCapacity(), actualCapacity);
	}
	
	/* Test 9: Checking the truck cargo has not exceeded capacity */
	@Test
	public void maxCapacity() throws CapacityException {
		rTruck.addItem("Ice Cream", 776, -5.0);
		
		rTruck.truckCapacity();
	}
	
	/* Test 10: Checking the truck is not empty */
	public void emptyTruck() throws CapacityException {
		rTruck.removeItem("Ice Cream", 904);
		rTruck.removeItem("Milk", 67);
		rTruck.removeItem("Banana", 52);
		
		rTruck.truckCapacity();
	}
	
	/* Test 11: Checking the temperature of the truck is safe (ie. has not exceeded either limit) */
	public void checkTemp() throws TemperatureException {
		rTruck.addItem("Peas", 5, -20.0);
		double newTemp = -20.0;
		assertEquals("Truck temperature is unsafe", newTemp, rTruck.getTemp());
	}
	
	/* Test 12: Checking that at there is least 1 temperature-controlled item in cargo */
		//figure out how to differentiate between dry & cold foods (add new 'type'? argument?)
}
