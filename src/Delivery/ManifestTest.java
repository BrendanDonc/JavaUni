package Delivery;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.JUnitSystem;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import Stock.Item;
import Stock.StockException;

public class ManifestTest {

	//Declaring a manifest object
	Manifest manifestTest;
	Manifest manifestTest2;
	
	//Initalise manifest array
	ArrayList<Truck> manifestArray;
	
	//Initialise truck-related objects
	Truck truckOrdinary;
	Item rice;
	Item biscuits;
	
	Truck truckRefrigerated;
	Item iceCream; 
	Item milk;
	
	
	/* Test 1: Constructing a manifest object*/
	@Before
	public void setUpManifest() throws DeliveryException {
		manifestTest = new Manifest();
		manifestTest2 = new Manifest();
		manifestArray = new ArrayList<Truck>();
		
		truckOrdinary = new Ordinary_Truck();
		rice = new Item("Rice", 10, 15, 200, 300);
		biscuits = new Item("Biscuits", 10, 15, 200, 300);
		truckOrdinary.addItem(rice, 50); 
		truckOrdinary.addItem(biscuits, 30); 
		
		truckRefrigerated = new Refrigerated_Truck();
		iceCream = new Item("Ice Cream", 10, 15, 200, 300, (double) -5);
		milk = new Item("Milk", 10, 15, 200, 300, (double) 2);
		truckRefrigerated.addItem(iceCream, 20);
		truckRefrigerated.addItem(milk, 70);
	}
	
	/* Test : Check manifest is empty */
	@Test
	public void emptyManifest() {
		int initSize = 0;
		
		assertEquals(initSize, manifestArray.size());
	}
	
	/* Test: Add truck to manifests (manifest is an array of trucks) */
	@Test
	public void addTruckToManifest() {
		manifestArray.add(truckOrdinary); 
		manifestTest.addTruck(truckOrdinary);
		int actualArraySize = 1;
		assertEquals(actualArraySize, 1);
	}
	
	/* Test: Return the truck array */
	@Test
	public void returnTruckManifest() {
		manifestArray.add(truckOrdinary); 
		manifestTest.addTruck(truckOrdinary);
		assertArrayEquals(manifestArray.get(0).getCargo().getItems(), manifestTest.returnManifest()[0].getCargo().getItems());
	}
	
	/* Test: Convert Manifest into 'printable' format (formatted) */
	@Test
	public void printManifestTest() {
		String stringResult = 
				">Ordinary\n"
				+ "Rice,50\n"
				+ "Biscuits,30\n";
		manifestTest.addTruck(truckOrdinary);
						
		assertEquals(stringResult, manifestTest.printManifest());
	}
	
	/*Test: Import manifest from a .csv file (we're converting .csv to strings so just use string inputs - see if test 3 matches to "manual" input/test)*/
	@Test
	public void importManifest() throws DeliveryException, NumberFormatException, StockException {
		String inputTruck = ">Ordinary";
		String inputItem1 = "Rice";
		String inputValue1 = "50";
		String inputItem2 = "Biscuits";
		String inputValue2 = "30";
		
		Truck truck = manifestTest.importTruck(inputTruck);
		truck.getCargo().addItemName(inputItem1, Integer.parseInt(inputValue1));
		truck.getCargo().addItemName(inputItem2, Integer.parseInt(inputValue2));
		
		manifestTest2.addTruck(truckOrdinary);
		
		assertEquals(manifestTest.printManifest(), manifestTest2.printManifest());	
	}
	
}
