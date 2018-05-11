package Stock;

import java.util.Objects;

public class Item {
	
	//Initialise variables
	String name;
	double cost;
	double price;
	int rePoint;
	int reAmount;
	Double temp;
	
	public Item(String itemName, double itemCost, double itemPrice, int itemReorderPoint, int itemReorderAmount) {
		this.name = itemName;
		this.cost = itemCost;
		this.price = itemPrice;
		this.rePoint = itemReorderPoint;
		this.reAmount = itemReorderAmount;
	}
	
	public Item(String itemName, double itemCost, double itemPrice, int itemReorderPoint, int itemReorderAmount, Double itemTemp) {
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
	
	public Double getTemp() {
		return temp;
	}
	
	public boolean tempRequired() {
		if (temp != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	public boolean equals(Item item) {
		boolean equal = true;
		if (this.name != item.getName()) {
			equal = false;
		}
		
		if (this.cost != item.getCost()) {
			equal = false;
		}
		
		if (this.price != item.getPrice()) {
			equal = false;
		}
		
		if (this.rePoint != item.getRePoint()) {
			equal = false;
		}
		
		if (this.reAmount != item.getReAmount()) {
			equal = false;
		}
		
		if (this.temp != item.getTemp()) {
			equal = false;
		}
		return equal;
	}

}
