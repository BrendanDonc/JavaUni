package Stock;

import java.text.DecimalFormat;

/**
 * The 'Store' class holds an instanced version of the client's store, which essentially keeps the contents of the store universal. 
 * 
 * It contains information regarding the store's stock, current capital, and name. 
 * 
 * 
 * @author Mary Millar
 *
 */
public final class Store {

	private static final Store INSTANCE = new Store();
	
	//Declare variables
	String name;
	double capital;
	Stock inventory;

    /**
     * A constructor that holds the store's name, starting capital, and an empty stock.
     */
    private Store() {
	    	this.name = "SuperMart";
	    	this.capital = 100000.00;
	    	this.inventory = new Stock();	
    }

    /**
     * This method retrieves the current instance of the store.
     * 
     * @return  Returns the current instance of the store.
     */
    public static Store getInstance() {
        return INSTANCE;
    }

	/**
	 * This method retrieves the name of the store.
	 * 
	 * @return A string that represents the store name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method retrieves the current capital of the store.
	 * 
	 * @return A double representing the store capital.
	 */
	public double getCapital() {
		return this.capital;
	}

	/**
	 * This method sets the inputed stock as the current stock of the store.
	 * 
	 * @param inputInv The stock to be set as the current stock.
	 * @throws StockException The exception inherited from addStock().
	 */
	public void setInventory(Stock inputInv) throws StockException{
		this.inventory.clearStock();
		
		try {
			this.inventory.addStock(inputInv);	
		} catch (StockException e) {
			throw e;
		}
	}

	/**
	 * This method retrieves the current inventory of the store.
	 * 
	 * @return A custom object of type Stock that holds the inventory of the store.
	 */
	public Stock getInventory() {
		return this.inventory;
	}

	/**
	 * This method raises the capital of the store by the given amount.
	 * 
	 * @param raiseAmount The amount that capital of the store is to be raised by.
	 */
	public void raiseCapital(double raiseAmount) {
		double oldCapital = getCapital();
		
		this.capital = oldCapital + raiseAmount;	
	}

	/**
	 * This method lowers the capital of the store by the given amount.
	 * 
	 * @param lowerAmount The amount that the capital of the store is to be lowered by.
	 */
	public void lowerCapital(double lowerAmount) {
		double oldCapital = getCapital();
		
		this.capital = oldCapital - lowerAmount;	
	}

	/**
	 * This method retrieves the current capital amount from its integer form and turns it into a 'dollar' format (eg. $100,000.00)
	 * 
	 * @return A string that represents the formatted capital amount.
	 */
	public String getCapitalString() {
		DecimalFormat formatter = new DecimalFormat("$###,###,###,###,###,###.00");
		return formatter.format(this.getCapital());
	}
}
