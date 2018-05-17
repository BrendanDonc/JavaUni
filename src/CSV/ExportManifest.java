package CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import Delivery.*;

public class ExportManifest {
	
	public static void ExportManifestCSV() throws FileNotFoundException {
		export(Manifest.manifestToExport());
	}
	
	public static void export(Manifest manifest) throws FileNotFoundException {
		boolean result = new File("Manifest.csv").delete();
		PrintWriter pw = new PrintWriter(new File("Manifest.csv"));
		pw.write(manifest.printManifest());
		pw.close();
	}
}