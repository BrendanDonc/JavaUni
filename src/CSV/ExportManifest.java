package CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import Delivery.*;

public class ExportManifest {
	
	public static void ExportManifestCSV() throws FileNotFoundException {
		String exportDir = "Manifest.csv";
		export(Manifest.manifestToExport(), exportDir);
	}
	
	public static void export(Manifest manifest, String exportDir) throws FileNotFoundException {
		boolean result = new File(exportDir).delete();
		PrintWriter pw = new PrintWriter(new File(exportDir));
		pw.write(manifest.printManifest());
		pw.close();
	}
}