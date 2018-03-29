package errors;

public class LogEntryNotFoundException extends RuntimeException {

    public LogEntryNotFoundException() {
    }

    public LogEntryNotFoundException(String message) {
        super(message);
    }

    public LogEntryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
