package Stock;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ItemTest {

	
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
		item = new Item("mushroom", 2, 4, 200, 325, Double.valueOf(10));
	}
	
	/*
	 * Test 3 Get item's name
	 * 
	 */
	@Test
	public void testName() {
		String name = "john";
		item = new Item(name, 2, 3, 225, 300);
		assertEquals(name, item.getName());
	}
	
	/*
	 * Test 4 Get item's manufacturing cost
	 * 
	 */
	@Test
	public void testCost() {
		double cost = 12;
		item = new Item("rice", cost, 3, 225, 300);
		assertEquals(cost, item.getCost(), 0.1);
	}
	
	/*
	 * Test 5 Get item's sell price
	 * 
	 */
	@Test
	public void testPrice() {
		double price = 23;
		item = new Item("rice", 2, price, 225, 300);
		assertEquals(price, item.getPrice(), 0.1);
	}
	
	/*
	 * Test 6 Get item's reorder point
	 * 
	 */
	@Test
	public void testRePoint() {
		int rePoint = 184;
		item = new Item("rice", 2, 3, rePoint, 300);
		assertEquals(rePoint, item.getRePoint(), 0.1);
	}
	
	/*
	 * Test 7 Get item's reorder amount
	 * 
	 */
	@Test
	public void testRePoint() {
		int reAmount = 326;
		item = new Item("rice", 2, 3, 225, reAmount);
		assertEquals(reAmount, item.getReAmount());
	}
	
	/*
	 * Test 8 Get refrigerated item's temp
	 * 
	 */
	@Test
	public void testTemp() {
		double temp = 18;
		item = new Item("mushroom", 2, 4, 200, 325, temp);
		assertEquals(temp, item.getTemp(), 0.1);
	}
	
	/*
	 * Test 9 Check ordinary item if refrigeration is required
	 * 
	 */
	@Test
	public void testOrdinaryTempRequired() {
		item = new Item("rice", 2, 3, 225, 300);
		assertEquals(false, item.tempRequired());
	}
	
	/*
	 * Test 10 Check refrigerated item if refrigeration is required
	 * 
	 */
	@Test
	public void testRefrigeratedTempRequired() {
		item = new Item("mushroom", 2, 4, 200, 325, Double.valueOf(10));
		assertEquals(true, item.tempRequired());
	}
	
	/*
	 * Test 11 Check manufacturing cost is positive
	 * 
	 */
	@Test(expected = StockException.class)
	public void testPosistiveManufacturing() {
		item = new Item("rice", -2, 3, 225, 300);
	}
	
	/*
	 * Test 12 Check manufacturing cost is above 0
	 * 
	 */
	
	@Test(expected = StockException.class)
	public void testManufacturingNotZero() {
		item = new Item("rice", 0, 3, 225, 300);
	}
	
	/*
	 * Test 13 Check sale price is positive
	 * 
	 */
	@Test(expected = StockException.class)
	public void testPosistiveSale() {
		item = new Item("rice", 2, -3, 225, 300);
	}
	
	/*
	 * Test 14 Check sale price is above 0
	 * 
	 */
	
	@Test(expected = StockException.class)
	public void testSaleNotZero() {
		item = new Item("rice", 2, 0, 225, 300);
	}
	
	/*
	 * Test 15 Check reorder point is positive
	 * 
	 */
	@Test(expected = StockException.class)
	public void testPosistiveRePoint() {
		item = new Item("rice", 2, 3, -225, 300);
	}
	
	/*
	 * Test 16 Check reorder amount is positive
	 * 
	 */
	@Test(expected = StockException.class)
	public void testPosistiveReAmount() {
		item = new Item("rice", 2, 3, 225, -300);
	}
	
	/*
	 * Test 17 Check reorder amount is above 0
	 * 
	 */
	
	@Test(expected = StockException.class)
	public void testReAmountNotZero() {
		item = new Item("rice", 2, 3, 225, 0);
	}
	
	
}
