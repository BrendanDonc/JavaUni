package Assignment;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

class ItemTest {

	
	/*
	 * Test 0 Declaring Item object
	 * 
	 */
	Item item;
	
	//Clear item before each test
	@Before
	public void setupItem() {
		item = null;
	}
	
	/*
	 * Test 1 Create an ordinary item 
	 * 
	 */
	@Test
	public void testOrdinary() {
		item = new Item("rice", 2, 3, 225, 300);
	}
	
	/*
	 * Test 2 Create a refrigerated item
	 * 
	 */
	@Test
	public void testRefrigerated() {
		item = new Item("mushroom", 2, 4, 200, 325, 10);
	}
	
	/*
	 * Test 3 Get item's name
	 * 
	 */
	@Test
	public void testName() {
		String name = "john";
		item = new Item(name, 2, 3, 225, 300);
		assertequals(name, item.getName());
	}
	
	/*
	 * Test 4 Get item's manufacturing cost
	 * 
	 */
	@Test
	public void testCost() {
		double cost = 12;
		item = new Item("rice", cost, 3, 225, 300);
		assertequals(cost, item.getCost());
	}
	
	/*
	 * Test 5 Get item's sell price
	 * 
	 */
	@Test
	public void testPrice() {
		double price = 23;
		item = new Item("rice", 2, price, 225, 300);
		assertequals(price, item.getPrice());
	}
	
	/*
	 * Test 6 Get item's reorder point
	 * 
	 */
	@Test
	public void testRePoint() {
		int rePoint = 184;
		item = new Item("rice", 2, 3, rePoint, 300);
		assertequals(rePoint, item.getRePoint());
	}
	
	/*
	 * Test 7 Get item's reorder amount
	 * 
	 */
	@Test
	public void testRePoint() {
		int reAmount = 326;
		item = new Item("rice", 2, 3, 225, reAmount);
		assertequals(reAmount, item.getReAmount());
	}
	
	/*
	 * Test 8 Get refrigerated item's temp
	 * 
	 */
	@Test
	public void testTemp() {
		double temp = 18;
		item = new Item("mushroom", 2, 4, 200, 325, temp);
		assertequals(temp, item.getTemp());
	}
	
	/*
	 * Test 9 Check ordinary item if refrigeration is required
	 * 
	 */
	@Test
	public void testOrdinaryTempRequired() {
		item = new Item("rice", 2, 3, 225, 300);
		assertequals(false, item.tempRequired());
	}
	
	/*
	 * Test 10 Chec regrigerated item if refrigeration is required
	 * 
	 */
	@Test
	public void testRefrigeratedTempRequired() {
		item = new Item("mushroom", 2, 4, 200, 325, 10);
		assertequals(true, item.tempRequired());
	}
}
