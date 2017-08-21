package springbootio.exception;

/**
 * Created by miaomiao on 17-7-19.
 */
public class SpringbootioException extends Exception {
    public SpringbootioException() {
    }

    public SpringbootioException(String message) {
        super(message);
    }

    public SpringbootioException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringbootioException(Throwable cause) {
        super(cause);
    }

    public SpringbootioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
