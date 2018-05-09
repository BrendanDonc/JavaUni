package Stock;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class StoreTest {
	
	//Setup items to be used in store tests
	Item rice = new Item("rice", 2, 3, 225, 300);
	Item bread = new Item("bread", 1, 2, 185, 224);
	Item mushroom = new Item("mushroom", 2, 4, 200, 325, 10);
	Item icecream = new Item("ice cream", 3, 4, 74, 221, -5);
	
	//Setup inventory stock for setInventory
	Stock inventory = new Stock();
	inventory.addItem(rice, 0);
	inventory.addItem(bread, 0);
	inventory.addItem(mushroom, 0);
	inventory.addItem(icecream, 0);
	
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
		double capital = 100000.00;
		assertEquals(capital, Store.getInstance().getCapital);
	}
	
	/*
	 * Test 4 Get the starting capital as a String to be displayed
	 * 
	 */
	@Test
	public void testGetCapitalString() {
		String capitalString = "$100,000.00";
		assertEquals(capitalString, Store.getInstance().getCapital);
	}
	
	/*
	 * Test 5 Set the initialized items as the inventory
	 * 
	 */
	@Test
	public void testSetInventory() {
		Store.getInstance().setInventory(inventory);		
	}
	
	/*
	 * Test 6 Get the inventory stock
	 * 
	 */
	@Test
	public void testGetInventory() {
		assertEquals(inventory, Store.getInstance().getInventory());
	}
	
	/*
	 * Test 7 Increase the capital
	 * 
	 */
	@Test
	public void testRaiseCapital() {
		double prevCapital = Store.getInstance().getCapital();
		double raiseAmount = 500.00;
		Store.getInstance().raiseCapital(raiseAmount);
		assertEquals(prevCapital + raiseAmount, Store.getInstance().getCapital());
	}
	
	/*
	 * Test 8 Decrease the capital
	 * 
	 */
	@Test
	public void testLowerCapital() {
		double prevCapital = Store.getInstance().getCapital();
		double lowerAmount = 200.00;
		Store.getInstance().raiseCapital(lowerAmount);
		assertEquals(prevCapital - lowerAmount, Store.getInstance().getCapital());
	}


}
