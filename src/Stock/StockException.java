package Stock;

@SuppressWarnings("serial")
public class StockException extends Exception{

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
