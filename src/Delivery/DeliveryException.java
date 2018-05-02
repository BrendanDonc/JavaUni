package Delivery;

@SuppressWarnings("serial")
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
