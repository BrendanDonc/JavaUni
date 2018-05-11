package Delivery;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.JUnitSystem;

import static org.junit.Assert.assertEquals;

import Stock.Item;

public class ManifestTest {

	//Declaring a manifest object
	Manifest manifestTest;
	
	//Initalise manifest array
	ArrayList<Truck> manifestArray;
	
	//Initialise truck-related objects
	Truck truckOrdinary = new Ordinary_Truck();
	Item rice = new Item("Rice", 10, 15, 200, 300);
	Item biscuits = new Item("Biscuits", 10, 15, 200, 300);
	
	Truck truckRefrigerated = new Refrigerated_Truck();
	Item iceCream = new Item("Ice Cream", 10, 15, 200, 300, (double) -5); 
	Item milk = new Item("Milk", 10, 15, 200, 300, (double) 2);
	
	
	/* Test 1: Constructing a manifest object*/
	@Before
	@Test
	public void setUpManifest() {
		manifestTest = new Manifest();
		manifestArray = new ArrayList<Truck>();
	}
	
	/* Test : Check manifest isn't empty (check array isn't empty) */
	@Test (expected = DeliveryException.class)
	public void emptyManifest() {
		
	}
	
	public void setup() throws DeliveryException {
		//Setting up ordinary truck for testing
		truckOrdinary.addItem(rice, 50); 
		truckOrdinary.addItem(biscuits, 30); 
		
		truckRefrigerated.addItem(iceCream, 20);
		truckRefrigerated.addItem(milk, 70);
		
	}
	
	/* Test: Add truck to manifests (manifest is an array of trucks) */
	@Test
	public void addTruckToManifest() {
		manifestArray.add(truckOrdinary); 
		
		int actualArraySize = 1;
		
		assertEquals(actualArraySize, 1);
		assertEquals(manifestArray.get(0), manifestTest.addTruck());
	}
	
	/* Test: Return the truck array */
	@Test
	public void returnTruckManifest() {
		String stringResult = "Rice, 50"
							+ "Biscuits, 30";
		
		assertEquals(stringResult, manifestTest.returnManifest());
	}
	
	/* Test: Convert Manifest into 'printable' format (formatted) */
	@Test
	public void printManifestTest() {
		String stringResult = 
				">Ordinary Truck/n"
				+ "Rice, 50/n"
				+ "Biscuits, 30";
						
		assertEquals(stringResult, manifestTest.printManifest());
	}
	
	/*Test: Import manifest from a .csv file (we're converting .csv to strings so just use string inputs - see if test 3 matches to "manual" input/test)*/
	@Test
	public void importManifest() {
		String inputString = "Cereal, 100/n"
							+ "Berries, 20/n"
							+ "Beef, 45/n"
							+ "Yoghurt, 70";
		assertEquals(inputString, manifestTest.returnManifest());	
	}
	
}
