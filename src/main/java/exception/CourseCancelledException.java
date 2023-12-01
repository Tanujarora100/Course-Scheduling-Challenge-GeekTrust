package exception;

public class CourseCancelledException extends RuntimeException {

    public CourseCancelledException(String message) {
        super(message);
    }
}
