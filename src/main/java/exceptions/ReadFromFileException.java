package exceptions;

public class ReadFromFileException extends RuntimeException {
    public ReadFromFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
