package springbootio.exception;

/**
 * Created by miaomiao on 17-7-19.
 */
public class StudentManagementException extends Exception {
    public StudentManagementException() {
    }

    public StudentManagementException(String message) {
        super(message);
    }

    public StudentManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentManagementException(Throwable cause) {
        super(cause);
    }

    public StudentManagementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
