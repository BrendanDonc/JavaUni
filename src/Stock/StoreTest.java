package Stock;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

//All tests within StoreTest were created by Brendan Doncaster

public class StoreTest {
	
	Item rice;
	Item bread;
	Item mushroom;
	Item icecream;
	Stock inventory;
	
	@Before
	public void setup() {
		//Setup items to be used in store tests
		
		try {	
			rice = new Item("rice", 2, 3, 225, 300);	
			bread = new Item("bread", 1, 2, 185, 224);
			mushroom = new Item("mushroom", 2, 4, 200, 325, (double)10);
			icecream = new Item("ice cream", 3, 4, 74, 221, (double)-5);
		
		} catch (StockException badParam) {
			fail("Store items failed to initialise.");
		}
		
		//Setup inventory stock for setInventory
		inventory = new Stock();
		inventory.addItem(rice, 0);
		inventory.addItem(bread, 0);
		inventory.addItem(mushroom, 0);
		inventory.addItem(icecream, 0);
	}
	
	/*
	 * Test 1 Create the single instance
	 * 
	 */
	@Test
	public void testGetInstance() {
		Store.getInstance();
	}
	
	/*
	 * Test 2 Get the instance's name
	 * 
	 */
	@Test
	public void testGetName() {
		String name = "SuperMart";
		assertEquals(name, Store.getInstance().getName());
	}
	
	/*
	 * Test 3 Get the instance's starting capital
	 * 
	 */
	@Test
	public void testGetCapital() {
	    Store.getInstance().raiseCapital(100000.00-Store.getInstance().getCapital());
		double capital = 100000.00;
		assertEquals(capital, Store.getInstance().getCapital(), 0.1);
	}
	
	/*
	 * Test 4 Get the starting capital as a String to be displayed
	 * 
	 */
	@Test
	public void testGetCapitalString() {
	    Store.getInstance().raiseCapital(100000.00-Store.getInstance().getCapital());
		String capitalString = "$100,000.00";
		assertEquals(capitalString, Store.getInstance().getCapitalString());
	}
	
	/*
	 * Test 5 Set the initialized items as the inventory
	 * 
	 */
	@Test
	public void testSetInventory() throws StockException {
		Store.getInstance().setInventory(inventory);		
	}
	
	/*
	 * Test 6 Get the inventory stock
	 * 
	 */
	@Test
	public void testGetInventory() throws StockException {
		Store.getInstance().setInventory(inventory);
		assertArrayEquals(inventory.getItems(), Store.getInstance().getInventory().getItems());
	}
	
	/*
	 * Test 7 Increase the capital
	 * 
	 */
	@Test
	public void testRaiseCapital() {
		double prevCapital = Store.getInstance().getCapital();
		double raiseAmount = 200.00;
		Store.getInstance().raiseCapital(raiseAmount);
		assertEquals(prevCapital + raiseAmount, Store.getInstance().getCapital(), 0.1);
	}
	
	/*
	 * Test 8 Decrease the capital
	 * 
	 */
	@Test
	public void testLowerCapital() {
		double prevCapital = Store.getInstance().getCapital();
		double lowerAmount = 700.00;
		Store.getInstance().lowerCapital(lowerAmount);
		assertEquals(prevCapital - lowerAmount, Store.getInstance().getCapital(), 0.1);
	}


}
