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
	public void testOrdinary() throws StockException {
		item = new Item("rice", 2, 3, 225, 300);
	}
	
	/*
	 * Test 2 Create a refrigerated item
	 * 
	 */
	@Test
	public void testRefrigerated() throws StockException {
		item = new Item("mushroom", 2, 4, 200, 325, (double) 10);
	}
	
	/*
	 * Test 3 Get item's name
	 * 
	 */
	@Test
	public void testName() throws StockException {
		String name = "john";
		item = new Item(name, 2, 3, 225, 300);
		assertEquals(name, item.getName());
	}
	
	/*
	 * Test 4 Get item's manufacturing cost
	 * 
	 */
	@Test
	public void testCost() throws StockException {
		double cost = 12;
		item = new Item("rice", cost, 3, 225, 300);
		assertEquals(cost, item.getCost(), 0.1);
	}
	
	/*
	 * Test 5 Get item's sell price
	 * 
	 */
	@Test
	public void testPrice() throws StockException {
		double price = 23;
		item = new Item("rice", 2, price, 225, 300);
		assertEquals(price, item.getPrice(), 0.1);
	}
	
	/*
	 * Test 6 Get item's reorder point
	 * 
	 */
	@Test
	public void testRePoint() throws StockException {
		int rePoint = 184;
		item = new Item("rice", 2, 3, rePoint, 300);
		assertEquals(rePoint, item.getRePoint(), 0.1);
	}
	
	/*
	 * Test 7 Get item's reorder amount
	 * 
	 */
	@Test
	public void testReAmount() throws StockException {
		int reAmount = 326;
		item = new Item("rice", 2, 3, 225, reAmount);
		assertEquals(reAmount, item.getReAmount());
	}
	
	/*
	 * Test 8 Get refrigerated item's temp
	 * 
	 */
	@Test
	public void testTemp() throws StockException {
		double temp = 18;
		item = new Item("mushroom", 2, 4, 200, 325, temp);
		assertEquals(temp, item.getTemp(), 0.1);
	}
	
	/*
	 * Test 9 Check ordinary item if refrigeration is required
	 * 
	 */
	@Test
	public void testOrdinaryTempRequired() throws StockException {
		item = new Item("rice", 2, 3, 225, 300);
		assertEquals(false, item.tempRequired());
	}
	
	/*
	 * Test 10 Check refrigerated item if refrigeration is required
	 * 
	 */
	@Test
	public void testRefrigeratedTempRequired() throws StockException {
		item = new Item("mushroom", 2, 4, 200, 325, (double)10);
		assertEquals(true, item.tempRequired());
	}
	
	/*
	 * Test 11 Check manufacturing cost is positive
	 * 
	 */
	@Test(expected = StockException.class)
	public void testPosistiveManufacturing() throws StockException {
		item = new Item("rice", -2, 3, 225, 300);
	}
	
	/*
	 * Test 12 Check manufacturing cost is above 0
	 * 
	 */
	
	@Test(expected = StockException.class)
	public void testManufacturingNotZero() throws StockException {
		item = new Item("rice", 0, 3, 225, 300);
	}
	
	/*
	 * Test 13 Check sale price is positive
	 * 
	 */
	@Test(expected = StockException.class)
	public void testPosistiveSale() throws StockException {
		item = new Item("rice", 2, -3, 225, 300);
	}
	
	/*
	 * Test 14 Check sale price is above 0
	 * 
	 */
	
	@Test(expected = StockException.class)
	public void testSaleNotZero() throws StockException {
		item = new Item("rice", 2, 0, 225, 300);
	}
	
	/*
	 * Test 15 Check reorder point is positive
	 * 
	 */
	@Test(expected = StockException.class)
	public void testPosistiveRePoint() throws StockException {
		item = new Item("rice", 2, 3, -225, 300);
	}
	
	/*
	 * Test 16 Check reorder amount is positive
	 * 
	 */
	@Test(expected = StockException.class)
	public void testPosistiveReAmount() throws StockException {
		item = new Item("rice", 2, 3, 225, -300);
	}
	
	/*
	 * Test 17 Check reorder amount is above 0
	 * 
	 */
	
	@Test(expected = StockException.class)
	public void testReAmountNotZero() throws StockException {
		item = new Item("rice", 2, 3, 225, 0);
	}
	
	
}
