package Delivery;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.JUnitSystem;

import Stock.Item;

public class ManifestTest {
	

	//truck array

	//Declaring a manifest object
	Manifest manifestTest;
	
	/* Test 1: Constructing a manifest object*/
	@Before
	@Test
	public void setUpManifest() {
		manifestTest = new Manifest();
	}
	
	/* Test : Check manifest isn't empty (check array isn't empty) */
	@Test
	public void emptyManifest() throws ManifestException {
		
	}
	
	public void setup() {
		//Setting up ordinary truck for testing
		Truck truckOrdinary = new Ordinary_Truck();
			
		Item rice = new Item("Rice", 10, 15, 200, 300);
		Item biscuits = new Item("Biscuits", 10, 15, 200, 300);
		
		truckOrdinary.addTruckItem(rice, 50); 
		truckOrdinary.addTruckItem(biscuits, 30); 
			
		
		Truck truckRefrigerated = new Refrigerated_Truck();
		
		Item iceCream = new Item("Ice Cream", 10, 15, 200, 300, -5);
		Item milk = new Item("Milk", 10, 15, 200, 300, 2);
		
		truckRefrigerated.addTruckItem(iceCream, 20);
		truckRefrigerated.addTruckItem(milk, 70);
		
	}
	
	/* Test: Add truck to manifests (manifest is an array of trucks) */
	@Test
	
	
	/* Test: Check manifest matches truck cargo */
	@Test
	
	
	/* Test: Convert Manifest into 'printable' format */
	@Test
	public void printManifestTest() {
		assertEquals(manifestTest.printManifest());
	}
	
	/*Test: Export manifest into a .csv file */
//	@Test
	
	
	
}
