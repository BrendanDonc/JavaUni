package Stock;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class StockTest {
	
	Item rice;
	Item bread;
	Item mushroom;
	Item icecream;
	Stock inventory;
	
	/*
	 * Test 0 Declaring Stock object
	 * 
	 */
	Stock stock;
	Stock tempStock;
	
	//Clear stock before each test
	@Before
	public void setupStock() {
		rice = new Item("rice", 2, 3, 225, 300);
		bread = new Item("bread", 1, 2, 185, 224);
		mushroom = new Item("mushroom", 2, 4, 200, 325, (double)10);
		icecream = new Item("ice cream", 3, 4, 74, 221, (double)-5);
		
		//Setup store inventory for addItemName
//		Stock inventory = new Stock();
//		inventory.addItem(rice, 0);
//		inventory.addItem(bread, 0);
//		inventory.addItem(mushroom, 0);
//		inventory.addItem(icecream, 0);
//		Store.getInstance().setInventory(inventory);
		
		stock = null;
		tempStock = null;
	}
	
	/*
	 * Test 1 Create a stock
	 * 
	 */
	@Test
	public void testStock() {
		stock = new Stock();
	}
	
	/*
	 * Test 2 Get list of items from stock
	 * 
	 */
	@Test
	public void testGetItems() {
		stock = new Stock();
		assertArrayEquals(null, stock.getItems());
	}
	
	/*
	 * Test 3 Adding an item
	 * 
	 */
	@Test
	public void testAddItem() {
		Item[] resultArray = {rice};
		stock = new Stock();
		stock.addItem(rice, 40);
		assertArrayEquals(resultArray, stock.getItems());
	}
	
//Can't be done until store is implemented	
	/*
	 * Test 4 Adding an item by name
	 * 
	 */
//	@Test
//	public void testAddItemName() {		
//		Item[] resultArray = {rice};
//		stock = new Stock();
//		stock.addItemName("rice", 40);
//		assertArrayEquals(resultArray, stock.getItems());
//	}

	/*
	 * Test 5 Adding whole stock
	 * 
	 */
	@Test
	public void testAddStock() {
		Item[] resultArray = {rice, mushroom};
		Stock tempStock = new Stock();
		tempStock.addItem(rice, 2);
		tempStock.addItem(mushroom, 50);
		stock = new Stock();
		stock.addStock(tempStock);
		assertArrayEquals(resultArray, stock.getItems());
	}
	
	/*
	 * Test 6 Summing costs of stock's items
	 * 
	 */
	@Test
	public void testSumCosts() {
		stock = new Stock();
		stock.addItem(rice, 100);
		stock.addItem(mushroom, 300);
		double result = 500;
		assertEquals(result, stock.sumCosts(), 0.1);
	}
	
	/*
	 * Test 7 Summing prices of stock's items
	 * 
	 */
	@Test
	public void testSumPrices() {
		stock = new Stock();
		stock.addItem(rice, 100);
		stock.addItem(mushroom, 300);
		double result = 900;
		assertEquals(result, stock.sumPrices(), 0.1);
	}
	
	/*
	 * Test 8 Get a list of all item's that need to be reordered
	 * 
	 */
	@Test
	public void testNeedsReorder() {
		stock = new Stock();
		stock.addItem(rice, 100);
		stock.addItem(mushroom, 300);
		stock.addItem(bread, 400);
		stock.addItem(icecream, 500);
		Item[] resultArray = {mushroom, bread, icecream};
		assertArrayEquals(resultArray, stock.needsReorder());
	}
	
	/*
	 * Test 9 Get a sorted list(coldest first) of items that need refrigeration
	 *  
	 */
	@Test
	public void testGetListCold() {
		stock = new Stock();
		stock.addItem(rice, 100);
		stock.addItem(mushroom, 300);
		stock.addItem(bread, 400);
		stock.addItem(icecream, 500);
		Item[] resultArray = {icecream, mushroom};
		assertArrayEquals(resultArray, stock.getListCold());
	}
	
	/*
	 * Test 10 Get a list of items that don't need refrigeration
	 * 
	 */
	@Test
	public void testGetListOrdinary() {
		stock = new Stock();
		stock.addItem(rice, 100);
		stock.addItem(mushroom, 300);
		stock.addItem(bread, 400);
		stock.addItem(icecream, 500);
		Item[] resultArray = {};
		assertArrayEquals(resultArray, stock.getListOrdinary());
	}
	
	/*
	 * Test 11 Get the stock's coldest item's temperature
	 * 
	 */
	@Test
	public void testGetColdestTemp() {
		stock = new Stock();
		stock.addItem(rice, 100);
		stock.addItem(mushroom, 300);
		stock.addItem(bread, 400);
		stock.addItem(icecream, 500);
		assertEquals(-5, stock.getColdestTemp);
	}
	
	/*
	 * Test 12 Get a manifest styled string form of the stock
	 * 
	 */
	@Test
	public void testGetManifestPrintStyle() {
		stock = new Stock();
		stock.addItem(rice, 100);
		stock.addItem(mushroom, 300);
		stock.addItem(bread, 400);
		stock.addItem(icecream, 500);
		String result = "rice,100\nmushroom,300\nbread,400\nice cream,500\n";
		assetEquals(result, stock.getManifestPrintStyle());
	}
	
	/*
	 * Test 13 Get stock amount of an item
	 * 
	 */
	@Test
	public void testGetAmount() {
		stock = new Stock();
		int amount = 200;
		stock.addItem(rice, amount);
		assertEquals(amount, stock.getAmount(rice));
	}
	
	/*
	 * Test 14 Subtract an item amount from a stock
	 * 
	 */
	@Test
	public void testSubtractItem() throws StockException{
		stock = new Stock();
		int startAmount = 200;
		int subtractAmount = 50;
		stock.addItem(rice, startAmount);
		stock.subtractItem(rice, subtractAmount);
		assertEquals(startAmount - subtractAmount, stock.getAmount(rice));
		
	}

	
	/*
	 * Test 15 Add item from string that isn't in store's inventory
	 * 
	 */
	@Test(expected = StockException.class)
	public void testNoItemName() throws StockException{
		stock = new Stock();
		stock.addItemName("apple", 50);
	}
	
	/*
	 * Test 16 Subtract more from a stock than it has
	 * 
	 */
	@Test(expected = StockException.class)
	public void testNotEnoughItem() throws StockException{
		stock = new Stock();
		stock.addItem(rice, 20);
		stock.subtractItem(rice, 400);
	}
	
	/*
	 * Test 17 Subtract an item that doesn't exist in stock
	 * 
	 */
	@Test(expected = StockException.class)
	public void testNoItem() throws StockException{
		stock = new Stock();
		stock.subtractItem(rice, 10);
	}
	
	/*
	 * Test 18 Check if item is a valid subtraction (success)
	 * 
	 */
	@Test
	public void testValidSubtractItemSuccess() {
		stock = new Stock();
		stock.addItem(rice, 50);
		assertEquals(true, stock.validSubtractItem(rice, 30));
	}
	
	/*
	 * Test 19 Check if item is a valid subtraction (failure, not enough of item)
	 * 
	 */
	@Test
	public void testValidSubtractItemFailureAmount() {
		stock = new Stock();
		stock.addItem(rice, 50);
		assertEquals(true, stock.validSubtractItem(rice, 70));
	}
	
	/*
	 * Test 20 Check if item is a valid subtraction (failure, item does not exist)
	 * 
	 */
	@Test
	public void testValidSubtractItemFailureExist() {
		stock = new Stock();
		assertEquals(true, stock.validSubtractItem(rice, 70));
	}
	
	/*
	 * Test 21 Subtract a stock from a stock
	 * 
	 */
	@Test
	public void testSubtractStock() {
		stock = new Stock();
		tempStock = new Stock();
		stock.addItem(rice, 500);
		tempStock.addItem(rice, 200);
		stock.subtractStock(tempStock);
		assertEquals(300, stock.getAmount(rice));
	}
	
	/*
	 * Test 22 Subtract a stock with invalid amount
	 * 
	 */
	@Test(expected = StockException.class)
	public void testSubtractStockAmount() {
		stock = new Stock();
		tempStock = new Stock();
		stock.addItem(rice, 200);
		tempStock.addItem(rice, 300);
		stock.subtractStock(tempStock);
	}
	
	/*
	 * Test 23 Subtract a stock without the item
	 * 
	 */
	@Test(expected = StockException.class)
	public void testSubtractStockExist() {
		stock = new Stock();
		tempStock = new Stock();
		tempStock.addItem(rice, 200);
		stock.subtractStock(tempStock);
	}
	
	/*
	 * Test 24 Check if stock is a valid subtraction (success)
	 * 
	 */
	@Test
	public void testValidSubtractStockSuccess() {
		stock = new Stock();
		tempStock = new Stock();
		stock.addItem(rice, 500);
		tempStock.addItem(rice, 200);
		assertEquals(true, stock.validSubtractStock(tempStock));
	}
	
	/*
	 * Test 25 Check if stock is a valid subtraction (failure, not enough of an item)
	 * 
	 */
	@Test
	public void testValidSubtractStockFailureAmount() {
		stock = new Stock();
		tempStock = new Stock();
		stock.addItem(rice, 200);
		tempStock.addItem(rice, 300);
		assertEquals(false, stock.validSubtractStock(tempStock));
	}
	
	/*
	 * Test 26 Check if stock is a valid subtraction (failure, an item does not exist)
	 * 
	 */
	@Test
	public void testValidSubtractStockFailureExist() {
		stock = new Stock();
		tempStock = new Stock();
		tempStock.addItem(rice, 200);
		assertEquals(false, stock.validSubtractStock(tempStock));
	}
	
	/*
	 * Test 27 Sum the total amount of items in the stock
	 * 
	 */
	@Test
	public void testSumAmount() {
		stock = new Stock();
		stock.addItem(rice, 200);
		stock.addItem(bread, 100);
		stock.addItem(mushroom, 300);
		assertEquals(600, stock.sumAmount());
	}
}
