package uno.supermercado.exception;

public class EliminarException extends RuntimeException {

    public EliminarException(String message) {
        super(message);
    }

    public EliminarException(String message, Throwable cause) {
        super(message, cause);
    }

    public EliminarException(Throwable cause) {
        super(cause);
    }

    public EliminarException(String message, Long id) {
        super(String.format("%s (ID: %d)", message, id));
    }

}
