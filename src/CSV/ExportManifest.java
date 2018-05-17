package CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import Delivery.*;
import Stock.StockException;

public class ExportManifest {
	
	public static void ExportManifestCSV() throws DeliveryException, StockException{
		String exportDir = "Manifest.csv";
		Manifest toExport;
		try {
			toExport = Manifest.manifestToExport();
		} catch (DeliveryException | StockException e) {
			throw e;
		}
		export(toExport, exportDir);
	}
	
	public static void export(Manifest manifest, String exportDir) throws DeliveryException {
		boolean result = new File(exportDir).delete();
		PrintWriter pw;
		try {
			pw = new PrintWriter(new File(exportDir));
		} catch (FileNotFoundException e) {
			throw new DeliveryException("File not found");
		}
		pw.write(manifest.printManifest());
		pw.close();
	}
}