package IPLAnalyser;

public class IPLException extends Exception {

	private static final long serialVersionUID = 1L;

	public enum ExceptionType {
		IPl_FILE_PROBLEM, UNABLE_TO_PARSE, CSV_FILE_PROBLEM, NO_DATA
	}

	ExceptionType type;

	public IPLException(String message, String name) {
		super(message);
		this.type = ExceptionType.valueOf(name);
	}

	public IPLException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public IPLException(String message, ExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}
}
