/**
 * This file forms part of the Inventory Management Application Project
 * Assignment Two - CAB302 2018
 * 
 * Developed by Brendan Doncaster and Mary Millar
 * 
 */

package CSV;

import java.io.*;
import Delivery.*;
import Stock.StockException;

/**
 * Class that contains static methods used for creating a manifest and
 * outputting it as a .csv file
 * 
 * @author Brendan Doncaster
 *
 */
public class ExportManifest {

    /**
     * 
     * @param file
     *            String of file directory to output the csv as
     * @throws DeliveryException
     *             when manifestToExport throws a DeliveryException
     * @throws StockException
     *             when manifestToExport throws a StockException
     * @throws CSVFormatException
     *             when directory does not exist
     */
    public static void ExportManifestCSV(String file) throws DeliveryException, StockException, CSVFormatException {
        String exportDir = file;
        Manifest toExport;
        try {
            toExport = Manifest.manifestToExport();
        } catch (DeliveryException | StockException e) {
            throw e;
        }
        try {
            export(toExport, exportDir);
        } catch (CSVFormatException e) {
            throw e;
        }
    }

    /**
     * Prints a manifest out to a selected csv directory
     * 
     * @param manifest
     *            Manifest to be output
     * @param exportDir
     *            String of file directory to output the csv as
     * @throws CSVFormatException
     *             when directory does not exist
     */
    public static void export(Manifest manifest, String exportDir) throws CSVFormatException {
        new File(exportDir).delete();
        PrintWriter pw;
        try {
            pw = new PrintWriter(new File(exportDir));
        } catch (FileNotFoundException e) {
            throw new CSVFormatException("File not found");
        }
        pw.write(manifest.printManifest());
        pw.close();
    }
}