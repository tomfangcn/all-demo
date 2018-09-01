package exception;

public class AvroException extends Exception {

	private static final long serialVersionUID = 1L;

	public AvroException() {
		super();
	}

	public AvroException(String msg) {
		super(msg);
	}

	public AvroException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AvroException(Throwable cause) {
		super(cause);
	}

}
