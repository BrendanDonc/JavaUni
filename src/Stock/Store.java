package Stock;

public final class Store {

	private static final Store INSTANCE = new Store();
	
	//Declare variables
	String name = "SuperMart";
	double capital = 100000.00;
	Stock inventory;


    private Store() {}

    public static Store getInstance() {
        return INSTANCE;
    }

	public String getName() {
		return this.name;
	}

	public double getCapital() {
		return this.capital;
	}

	public void setInventory(Stock inputInv) {
		inventory.addStock(inputInv);		
	}

	public Object getInventory() {
		return this.inventory;
	}

	public void raiseCapital(double raiseAmount) {
			this.capital =+ raiseAmount;		
	}

	
}
