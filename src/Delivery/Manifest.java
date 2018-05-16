package Delivery;

import java.util.ArrayList;

public class Manifest {
	private ArrayList<Truck> manifest;

	public Manifest() {
		manifest = new ArrayList<Truck>();
	}
	
	public void addTruck(Truck truck) {
		manifest.add(truck);
	}
	
	public ArrayList<Truck> returnManifest(){
		return manifest;
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
		
		if (string.substring(0,1) == ">") {
			string = string.substring(1);			
		}
		
		if(string == "ordinary") {
			importTruck = new Ordinary_Truck();
		}
		
		else if(string == "refrigerated") {
			importTruck = new Refrigerated_Truck();
		}
		else {
			throw new DeliveryException();
		}
		manifest.add(importTruck);
		return importTruck;
	}
	

}
