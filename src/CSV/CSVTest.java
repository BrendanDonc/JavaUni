package CSV;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import Delivery.DeliveryException;
import Stock.*;

/**
 * Manual test to compare output store capitals to capitals from README supplied
 * 
 * @author Brendan Doncaster
 *
 */
public class CSVTest {

	@Test
	public void test() throws CSVFormatException, StockException, DeliveryException, IOException {
	    
	    //Initialize items then export>import the manifest
		InitializeItems.InitializeItemsFromCSV("testData/item_properties.csv");
		assertEquals("$100,000.00", Store.getInstance().getCapitalString());
		
		ExportManifest.ExportManifestCSV("testData/Manifest.csv");
		LoadManifest.LoadManifestCSV("testData/Manifest.csv");
		assertEquals("$42,717.88" , Store.getInstance().getCapitalString());
		
		//Load sales_log_0 then export>import the manifest
		LoadSales.LoadSalesCSV("testData/sales_log_0.csv");
		assertEquals("$72,047.88", Store.getInstance().getCapitalString());
		
		ExportManifest.ExportManifestCSV("testData/Manifest.csv");
		LoadManifest.LoadManifestCSV("testData/Manifest.csv");
		assertEquals("$27,569.79", Store.getInstance().getCapitalString());
		
		//Load sales_log_1 then export>import the manifest
		LoadSales.LoadSalesCSV("testData/sales_log_1.csv");
		assertEquals("$67,169.79", Store.getInstance().getCapitalString());
		
		ExportManifest.ExportManifestCSV("testData/Manifest.csv");
		LoadManifest.LoadManifestCSV("testData/Manifest.csv");
		assertEquals("$42,069.94", Store.getInstance().getCapitalString());
		
		//Load sales_log_2 then export>import the manifest
		LoadSales.LoadSalesCSV("testData/sales_log_2.csv");
		assertEquals("$81,394.94", Store.getInstance().getCapitalString());
		
		ExportManifest.ExportManifestCSV("testData/Manifest.csv");
		LoadManifest.LoadManifestCSV("testData/Manifest.csv");
		assertEquals("$47,549.04", Store.getInstance().getCapitalString());
		
		//Load sales_log_3 then export>import the manifest
		LoadSales.LoadSalesCSV("testData/sales_log_3.csv");
		assertEquals("$81,967.04",Store.getInstance().getCapitalString());
		
		ExportManifest.ExportManifestCSV("testData/Manifest.csv");
		LoadManifest.LoadManifestCSV("testData/Manifest.csv");
		assertEquals("$51,838.22", Store.getInstance().getCapitalString());
		
		//Load sales_log_4 then export>import the manifest
		LoadSales.LoadSalesCSV("testData/sales_log_4.csv");
		assertEquals("$93,852.22", Store.getInstance().getCapitalString());
		
		ExportManifest.ExportManifestCSV("testData/Manifest.csv");
		LoadManifest.LoadManifestCSV("testData/Manifest.csv");
		assertEquals("$56,140.25", Store.getInstance().getCapitalString());
	}

}
