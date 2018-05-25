/**
 * This file forms part of the Inventory Management Application Project
 * Assignment Two - CAB302 2018
 * 
 * Developed by Brendan Doncaster and Mary Millar
 * 
 */

package Stock;

@SuppressWarnings("serial")
/**
 * Custom exception for any errors that occur during the stock process
 * 
 * @author Brendan Doncaster
 *
 */
public class StockException extends Exception {

    public StockException() {
        super();
    }

    public StockException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockException(String message) {
        super(message);
    }

    public StockException(Throwable cause) {
        super(cause);
    }

}
