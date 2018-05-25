/**
 * This file forms part of the Inventory Management Application Project
 * Assignment Two - CAB302 2018
 * 
 * Developed by Brendan Doncaster and Mary Millar
 * 
 */

package Delivery;

@SuppressWarnings("serial")
/**
 * Custom exception for any errors that occur during the delivery process
 * 
 * @author Brendan Doncaster
 *
 */
public class DeliveryException extends Exception {

    public DeliveryException() {
    }

    public DeliveryException(String message) {
        super(message);
    }

    public DeliveryException(Throwable cause) {
        super(cause);
    }

    public DeliveryException(String message, Throwable cause) {
        super(message, cause);
    }

}
