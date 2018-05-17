package CSV;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Delivery.DeliveryException;
import Stock.StockException;

public class CSVTest {

	@Test
	public void test() throws CSVFormatException, StockException, DeliveryException, IOException {
		InitializeItems.InitializeItems("item_properties.csv");
		ExportManifest.ExportManifestCSV("Manifest.csv");
		//LoadSales.LoadSales("sales_log_0.csv");
	}

}
