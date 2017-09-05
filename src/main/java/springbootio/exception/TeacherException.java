package springbootio.exception;

/**
 * Created by miaomiao on 17-7-19.
 */
public class TeacherException extends StudentManagementException {
    public TeacherException() {
    }

    public TeacherException(String message) {
        super(message);
    }

    public TeacherException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeacherException(Throwable cause) {
        super(cause);
    }

    public TeacherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
