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
