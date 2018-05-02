package Stock;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class StockTest {
	
	//Setup items to be used in stock tests
	Item rice = new Item("rice", 2, 3, 225, 300);
	Item bread = new Item("bread", 1, 2, 185, 224);
	Item mushroom = new Item("mushroom", 2, 4, 200, 325, 10);
	Item icecream = new Item("ice cream", 3, 4, 74, 221, -5);
	
	//Setup store inventory for addItemName
//	Stock inventory = new Stock();
//	inventory.addItem(rice, 0);
//	inventory.addItem(bread, 0);
//	inventory.addItem(mushroom, 0);
//	inventory.addItem(icecream, 0);
//	Store.getInstance().setItems(inventory);
	
	/*
	 * Test 0 Declaring Stock object
	 * 
	 */
	Stock stock;
	
	//Clear stock before each test
	@Before
	public void setupItem() {
		stock = null;
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
		assertequals(null, stock.getItems());
	}
	
	/*
	 * Test 3 Adding an item
	 * 
	 */
	@Test
	public void testAddItem() {
		Item[] resultArray = {rice};
		stock = new Stock();
		stock.addStock(rice, 40);
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
//		stock.addStock("rice", 40);
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
		assertequals(result, stock.sumCosts());
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
		assertequals(result, stock.sumPrices());
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
		assertequals(-5, stock.getColdestTemp);
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
		assetequals(result, stock.getManifestPrintStyle());
	}
}
