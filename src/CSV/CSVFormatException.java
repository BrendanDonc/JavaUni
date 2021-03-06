/**
 * This file forms part of the Inventory Management Application Project
 * Assignment Two - CAB302 2018
 * 
 * Developed by Brendan Doncaster and Mary Millar
 * 
 */

package CSV;

@SuppressWarnings("serial")
/**
 * Custom exception for any errors that occur during the CSV reading and parsing
 * process
 * 
 * @author Brendan Doncaster
 *
 */
public class CSVFormatException extends Exception {

    public CSVFormatException() {
        super();
    }

    public CSVFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVFormatException(String message) {
        super(message);
    }

    public CSVFormatException(Throwable cause) {
        super(cause);
    }

}
