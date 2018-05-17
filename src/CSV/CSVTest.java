package CSV;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Delivery.DeliveryException;
import Stock.StockException;
import Stock.Store;

public class CSVTest {

	@Test
	public void test() throws CSVFormatException, StockException, DeliveryException, IOException {
		InitializeItems.InitializeItems("item_properties.csv");
		System.out.println(Store.getInstance().getCapitalString());
		ExportManifest.ExportManifestCSV("Manifest.csv");
		LoadManifest.LoadManifest("Manifest.csv");
		System.out.println(Store.getInstance().getCapitalString() + "\n");
		
		
		LoadSales.LoadSales("sales_log_0.csv");
		System.out.println(Store.getInstance().getCapitalString());
		ExportManifest.ExportManifestCSV("Manifest.csv");
		LoadManifest.LoadManifest("Manifest.csv");
		System.out.println(Store.getInstance().getCapitalString() + "\n");
		
		
		LoadSales.LoadSales("sales_log_1.csv");
		System.out.println(Store.getInstance().getCapitalString());
		ExportManifest.ExportManifestCSV("Manifest.csv");
		LoadManifest.LoadManifest("Manifest.csv");
		System.out.println(Store.getInstance().getCapitalString() + "\n");
		
		LoadSales.LoadSales("sales_log_2.csv");
		System.out.println(Store.getInstance().getCapitalString());
		ExportManifest.ExportManifestCSV("Manifest.csv");
		LoadManifest.LoadManifest("Manifest.csv");
		System.out.println(Store.getInstance().getCapitalString() + "\n");
		
		LoadSales.LoadSales("sales_log_3.csv");
		System.out.println(Store.getInstance().getCapitalString());
		ExportManifest.ExportManifestCSV("Manifest.csv");
		LoadManifest.LoadManifest("Manifest.csv");
		System.out.println(Store.getInstance().getCapitalString() + "\n");
		
		LoadSales.LoadSales("sales_log_4.csv");
		System.out.println(Store.getInstance().getCapitalString());
		ExportManifest.ExportManifestCSV("Manifest.csv");
		LoadManifest.LoadManifest("Manifest.csv");
		System.out.println(Store.getInstance().getCapitalString());
	}

}
