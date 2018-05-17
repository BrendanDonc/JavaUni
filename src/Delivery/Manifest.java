package Delivery;

import java.util.ArrayList;

import Stock.Item;
import Stock.Stock;
import Stock.StockException;
import Stock.Store;

public class Manifest {
	private ArrayList<Truck> manifest;

	public Manifest() {
		manifest = new ArrayList<Truck>();
	}
	
	public void addTruck(Truck truck) {
		manifest.add(truck);
	}
	
	public Truck[] returnManifest(){
		Truck[] manifestArray = new Truck[manifest.size()];
		this.manifest.toArray(manifestArray);
		return manifestArray;
	}
	
	public String printManifest() {
		String print = "";
		if (manifest.isEmpty()) {
			return print;
		}
		else {
			
			for (Truck truck : manifest) {
				print = print + ">" + truck.getTruckType() + "\n";
				print = print + truck.getCargo().getManifestPrintStyle();
			}
			return print;
		}
	}
	
	public Truck importTruck(String string) throws DeliveryException {
		Truck importTruck;

		if (string.substring(0,1).equals(">")) {
			string = string.substring(1);
		}
		
		if(string.equals("Ordinary")) {
			importTruck = new Ordinary_Truck();
		}
		
		else if(string.equals("Refrigerated")) {
			importTruck = new Refrigerated_Truck();
		}
		else {
			throw new DeliveryException();
		}
		manifest.add(importTruck);
		return importTruck;
	}
	
	

	/*
	 * 
	 */
	public static Manifest manifestToExport() throws DeliveryException, StockException {
		Item[] exportItems = Store.getInstance().getInventory().needsReorder();
		Stock exportStock = new Stock();
		for(Item item : exportItems) {
			exportStock.addItem(item, item.getReAmount());
		}
		Item[] cold = exportStock.getListCold();
		Item[] ordinary = exportStock.getListOrdinary();
		
		Manifest exportManifest = new Manifest();
		boolean coldsLeft = true;
		boolean singleCold = true;
		int currentRefTruck = 0;
		int amount;
		
		//Adding in the cold items
		while(coldsLeft){
			
			singleCold = false;
			
			for(Item item: cold) {
				if (exportStock.getAmount(item) > 0) {
					singleCold = true;
				}
			}
			
			coldsLeft = singleCold;

			
			//If current truck does not exist, create a new one
			if(exportManifest.returnManifest().length == currentRefTruck) {
				if(coldsLeft) {
					exportManifest.addTruck(new Refrigerated_Truck());
				}
			}
			if(coldsLeft) {
				Truck currentTruck = exportManifest.returnManifest()[currentRefTruck];
				
				//If truck has space, start adding items
				if(currentTruck.getRemainingCapacity() > 0) {
					
					//Go through all cold items
					for(Item item: cold) {
						//Check if truck still has space for items at this point
						if(currentTruck.getRemainingCapacity() > 0) {
							
							//Check if item still has an amount to add
							if(exportStock.getAmount(item) > 0) {
								
								//If truck has space for all of the item, add it all in
								if(exportStock.getAmount(item) < currentTruck.getRemainingCapacity()) {
									amount = exportStock.getAmount(item);
									try {
										currentTruck.addItem(item, amount);
									} catch (DeliveryException e1) {
										throw e1;
									}
									try {
										exportStock.subtractItem(item, amount);
									} catch (StockException e) {
										throw e;
									}
									
								}
								//If not enough space for all of item, just fill remainder of truck
								else{
									amount = currentTruck.getRemainingCapacity();
									try {
										currentTruck.addItem(item, amount);
									} catch (DeliveryException e1) {
										throw e1;
									}
									try {
										exportStock.subtractItem(item, amount);
									} catch (StockException e) {
										throw e;
									}
									
								}
							}
						}
					}
				}
				
				//If truck does not have space, move on to the next truck
				else {
					currentRefTruck++;
				}
			}
			
		}//Finish adding cold items
		
		boolean ordsLeft = true;
		boolean singleOrdinary = true;
		
		//Adding in the ordinary items
		while(ordsLeft){
			
			singleOrdinary = false;
			
			for(Item item: ordinary) {
				if (exportStock.getAmount(item) > 0) {
					singleOrdinary = true;
				}
			}
					
			ordsLeft = singleOrdinary;
					
			//If current truck does not exist, create a new one
			if(exportManifest.returnManifest().length == currentRefTruck) {
				if(ordsLeft) {
					exportManifest.addTruck(new Ordinary_Truck());
				}
			}
			if(ordsLeft) {
				Truck currentTruck = exportManifest.returnManifest()[currentRefTruck];
				
				//If truck has space, start adding items
				if(currentTruck.getRemainingCapacity() > 0) {
					
					//Go through all ordinary items
					for(Item item: ordinary) {
						
						//Check if truck still has space for items at this point
						if(currentTruck.getRemainingCapacity() > 0) {
							
							//Check if item still has an amount to add
							if(exportStock.getAmount(item) > 0) {
								
								//If truck has space for all of the item, add it all in
								if(exportStock.getAmount(item) < currentTruck.getRemainingCapacity()) {
									amount = exportStock.getAmount(item);
									try {
										currentTruck.addItem(item, amount);
									} catch (DeliveryException e1) {
										throw e1;
									}
									try {
										exportStock.subtractItem(item, amount);
									} catch (StockException e) {
										throw e;
									}
									
								}
								//If not enough space for all of item, just fill remainder of truck
								else{
									amount = currentTruck.getRemainingCapacity();
									try {
										currentTruck.addItem(item, amount);
									} catch (DeliveryException e1) {
										throw e1;
									}
									try {
										exportStock.subtractItem(item, amount);
									} catch (StockException e) {
										throw e;
									}
								}
								
							}
						}
					}
				}
				//If truck does not have space, move on to the next truck
				else {
					currentRefTruck++;
				}
			}
		}
		return exportManifest;
		
	}
	
	
	public double sumManifestCost() throws DeliveryException {
		double sumAmount = 0;
		for(Truck truck : this.returnManifest()) {
			try {
				sumAmount += truck.truckCost();
			} catch (DeliveryException e) {
				throw e;
			}
			sumAmount += truck.getCargo().sumCosts();
		}
		
		return sumAmount;
	}
	
	
	

}
