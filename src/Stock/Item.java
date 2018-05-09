package Stock;

public class Item {
	
	//Figure out how to differentiate between 2 different 'Item' methods - different .java files for different kinds of items? (unless eclipse knows how to do that already?)
	
	
	//Initialise variables
	String name;
	double cost;
	double price;
	int rePoint;
	int reAmount;
	double temp;
	
	public Item(String itemName, double itemCost, double itemPrice, int itemReorderPoint, int itemReorderAmount) {
		this.name = itemName;
		this.cost = itemCost;
		this.price = itemPrice;
		this.rePoint = itemReorderPoint;
		this.reAmount = itemReorderAmount;
	}
	
	public Item(String itemName, double itemCost, double itemPrice, int itemReorderPoint, int itemReorderAmount, double itemTemp) {
		this.name = itemName;
		this.cost = itemCost;
		this.price = itemPrice;
		this.rePoint = itemReorderPoint;
		this.reAmount = itemReorderAmount;
		this.temp = itemTemp;
	}
	
	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getRePoint() {
		return rePoint;
	}
	
	public int getReAmount() {
		return reAmount;
	}
	
	public double getTemp() {
		return temp;
	}
	
	public boolean tempRequired() {
		// TODO check if way to differentiate between constructors
	}

}
