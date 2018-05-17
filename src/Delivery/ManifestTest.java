package Delivery;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.JUnitSystem;

import CSV.ExportManifest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import Stock.Item;
import Stock.Stock;
import Stock.StockException;
import Stock.Store;

public class ManifestTest {

	//Declaring a manifest object
	Manifest manifestTest;
	Manifest manifestTest2;
	Manifest exportManifest;
	
	//Initalise manifest array
	ArrayList<Truck> manifestArray;
	
	//Initialise truck-related objects
	Truck truckOrdinary;
	Item rice;
	Item beans;
	Item pasta;
	Item biscuits;
	Item nuts;
	Item chips;
	Item chocolate;
	Item bread;
	
	Truck truckRefrigerated;
	Item mushrooms;
	Item tomatoes;
	Item lettuce;
	Item grapes;
	Item asparagus;
	Item celery;
	Item chicken;
	Item beef;
	Item fish;
	Item yoghurt;
	Item milk;
	Item cheese;
	Item iceCream;
	Item ice;
	Item frozenMeat;
	Item frozenVegetableMix;
	
	Stock inventory;
	
	
	/* Test 1: Constructing a manifest object*/
	@Before
	public void setUpManifest() throws DeliveryException, StockException {
		manifestTest = new Manifest();
		manifestTest2 = new Manifest();
		manifestArray = new ArrayList<Truck>();
		
		truckOrdinary = new Ordinary_Truck();
		
		rice = new Item("rice", 2, 3, 225, 300);
		beans = new Item("beans", 4, 6, 450, 525);
		pasta = new Item("pasta", 3, 4, 125, 250);
		biscuits = new Item("biscuits", 2, 5, 450, 575);
		nuts = new Item("nuts", 5, 9, 125, 250);
		chips = new Item("chips", 2, 4, 125, 200);
		chocolate = new Item("chocolate", 5, 8, 250, 375);
		bread = new Item("bread", 2, 3, 125, 200);
		
		truckOrdinary.addItem(rice, 50); 
		truckOrdinary.addItem(biscuits, 30); 
		
		truckRefrigerated = new Refrigerated_Truck();
		
		mushrooms = new Item("mushrooms", 2, 4, 200, 325, (double) 10);
		tomatoes = new Item("tomatoes", 1, 2, 325, 400, (double) 10);
		lettuce = new Item("lettuce", 1, 2, 250, 350, (double) 10);
		grapes = new Item("grapes", 4, 6, 125, 225, (double) 9);
		asparagus = new Item("asparagus", 2, 4, 175, 275, (double) 8);
		celery = new Item("celery", 2, 3, 225, 350, (double) 8);
		chicken = new Item("chicken", 10, 14, 325, 425, (double) 4);
		beef = new Item("beef", 12, 17, 425, 550, (double) 3);
		fish = new Item("fish", 13, 16, 375, 475, (double) 2);
		yoghurt = new Item("yoghurt", 10, 12, 200, 325, (double) 3);
		milk = new Item("milk", 2, 3, 300, 425, (double) 3);
		cheese = new Item("cheese", 4, 7, 375, 450, (double) 3);
		iceCream = new Item("ice cream", 8, 14, 175, 250, (double) -20);
		ice = new Item("ice", 2, 5, 225, 325, (double) -10);
		frozenMeat = new Item("frozen meat", 10, 14, 450, 575, (double) -14);
		frozenVegetableMix = new Item("frozen vegetable mix", 5, 8, 225, 325, (double) -12);

		
		
		truckRefrigerated.addItem(iceCream, 20);
		truckRefrigerated.addItem(milk, 70);
	}
	
	/* Test 2: Check manifest is empty */
	@Test
	public void emptyManifest() {
		int initSize = 0;
		
		assertEquals(initSize, manifestArray.size());
	}
	
	/* Test 3: Add truck to manifests (manifest is an array of trucks) */
	@Test
	public void addTruckToManifest() {
		manifestArray.add(truckOrdinary); 
		manifestTest.addTruck(truckOrdinary);
		int actualArraySize = 1;
		assertEquals(actualArraySize, 1);
	}
	
	/* Test 4: Return the truck array */
	@Test
	public void returnTruckManifest() {
		manifestArray.add(truckOrdinary); 
		manifestTest.addTruck(truckOrdinary);
		assertArrayEquals(manifestArray.get(0).getCargo().getItems(), manifestTest.returnManifest()[0].getCargo().getItems());
	}
	
	/* Test 5: Convert Manifest into 'printable' format (formatted) */
	@Test
	public void printManifestTest() {
		String stringResult = 
				">Ordinary\n"
				+ "rice,50\n"
				+ "biscuits,30\n";
		manifestTest.addTruck(truckOrdinary);
						
		assertEquals(stringResult, manifestTest.printManifest());
	}
	
	/*Test 6: Import manifest from a .csv file (we're converting .csv to strings so just use string inputs - see if test 3 matches to "manual" input/test)*/
	@Test
	public void importManifest() throws DeliveryException, NumberFormatException, StockException {
		inventory = new Stock();
		inventory.addItem(biscuits, 0);
		inventory.addItem(rice, 0);
		inventory.addItem(iceCream, 0);
		inventory.addItem(milk, 0);
		Store.getInstance().setInventory(inventory);
		
		String inputTruck = ">Ordinary";
		String inputItem1 = "rice";
		String inputValue1 = "50";
		String inputItem2 = "biscuits";
		String inputValue2 = "30";
		
		Truck truck = manifestTest.importTruck(inputTruck);
		truck.getCargo().addItemName(inputItem1, Integer.parseInt(inputValue1));
		truck.getCargo().addItemName(inputItem2, Integer.parseInt(inputValue2));
		
		manifestTest2.addTruck(truckOrdinary);
		
		assertEquals(manifestTest.printManifest(), manifestTest2.printManifest());	
	}
	
	/*Test 7: Generate a manifest of items that need reordering*/
	@Test
	public void generateManifest() throws StockException, DeliveryException {
		inventory = new Stock();
		inventory.addItem(asparagus, 0);
		inventory.addItem(beans, 0);
		inventory.addItem(beef, 0);
		inventory.addItem(biscuits, 0);
		inventory.addItem(bread, 0);
		inventory.addItem(celery, 0);
		inventory.addItem(cheese, 0);
		inventory.addItem(chicken, 0);
		inventory.addItem(chips, 0);
		inventory.addItem(chocolate, 0);
		inventory.addItem(fish, 0);
		inventory.addItem(frozenMeat, 0);
		inventory.addItem(frozenVegetableMix, 0);
		inventory.addItem(grapes, 0);
		inventory.addItem(ice, 0);
		inventory.addItem(iceCream, 0);
		inventory.addItem(lettuce, 0);
		inventory.addItem(milk, 0);
		inventory.addItem(mushrooms, 0);
		inventory.addItem(nuts, 0);
		inventory.addItem(pasta, 0);
		inventory.addItem(rice, 0);
		inventory.addItem(tomatoes, 0);
		inventory.addItem(yoghurt, 0);
		
		Store.getInstance().setInventory(inventory);
		
		exportManifest = Manifest.manifestToExport();
		System.out.println(exportManifest.printManifest());
		System.out.println(exportManifest.sumManifestCost());
		Store.getInstance().lowerCapital(exportManifest.sumManifestCost());
		System.out.println(Store.getInstance().getCapitalString());
		ExportManifest.ExportManifestCSV();
		assertEquals(42717.88, Store.getInstance().getCapital(), 0.01);
	}
	
}
