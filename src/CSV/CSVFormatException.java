package CSV;

@SuppressWarnings("serial")
public class CSVFormatException extends Exception{

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
