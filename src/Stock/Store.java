package Stock;

public final class Store {

	private static final Store INSTANCE = new Store();
	
	//Declare variables
	//String name;
	//double capital;
	//Stock inventory;
	
	String name = "SuperMart";
	String startCapital = "$100,000.00";
	double capital = 100000.00;
	Stock inventory = new Stock();
	
	int getCap = 1;

    private Store() {
    		
    }

    public static Store getInstance() {
        return INSTANCE;
    }

	public String getName() {
		return this.name;
	}

	public double getCapital() {
		System.out.println("The capital is currently " + this.capital);
		System.out.println("This function has been run " + this.getCap + " times.");
		this.getCap++;
		
		return this.capital;
	}

	public void setInventory(Stock inputInv) throws StockException {
		inventory.addStock(inputInv);		
	}

	public Stock getInventory() {
		return this.inventory;
	}

	public void raiseCapital(double raiseAmount) {
		double oldCapital = getCapital();
		
		this.capital = oldCapital + raiseAmount;
		
	}

	public void lowerCapital(double lowerAmount) {
		double oldCapital = getCapital();
		
		this.capital = oldCapital - lowerAmount;	
	}

	public String getCapitalString() {
		return this.startCapital;
	}

	
}
