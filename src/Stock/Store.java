package Stock;

import java.text.DecimalFormat;

public final class Store {

	private static final Store INSTANCE = new Store();
	
	//Declare variables
	//String name;
	//double capital;
	//Stock inventory;
	
	String name;
	double capital;
	Stock inventory;
	
	int getCap = 1;

    private Store() {
    	this.name = "SuperMart";
    	this.capital = 100000.00;
    	this.inventory = new Stock();
    		
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

	public void setInventory(Stock inputInv) {
		this.inventory.clearStock();
		this.inventory.addStock(inputInv);		
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
		DecimalFormat formatter = new DecimalFormat("$###,###,###,###,###,###.00");
		return formatter.format(this.getCapital());
	}

	
}
