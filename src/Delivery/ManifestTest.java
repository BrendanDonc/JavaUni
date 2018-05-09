package Delivery;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.JUnitSystem;

import Stock.Item;

public class ManifestTest {
	
	//Setting up ordinary truck for testing
	Ordinary_Truck truckOrdinary = new Ordinary_Truck();
		
	Item rice = new Item("Rice", 10, 15, 200, 300);
	truckOrdinary.addItem(rice, 50);
		
	Truck truckRefrigerated = new Refrigerated_Truck();
	
	//truck collection

	//Declaring a manifest object
	Manifest manifestTest;
	
	/* Test 1: Constructing a manifest object*/
	@Before
	@Test
	public void setUpManifest() {
		manifestTest = new Manifest();
	}
	
	/* Test : Check manifest isn't empty */
	@Test
	public void emptyManifest() throws ManifestException {
		manifestTest.
	}
	
	/* Test: Add truck to manifests */
	@Test
	
	
	/* Test: Check manifest matches truck cargo */
	@Test
	
	
	/* Test: Convert Manifest into 'printable' format */
	@Test
	public void printManifestTest() {
		assertEquals(manifestTest.printManifest());
	}
	
	/*Test: Export manifest into a .csv file */
	@Test
	
	
	
}
