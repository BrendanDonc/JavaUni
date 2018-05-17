package CSV;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class CSVTest {

	@Test
	public void test() throws FileNotFoundException, CSVFormatException {
		InitializeItems.InitializeItems();
		ExportManifest.ExportManifestCSV();
	}

}
