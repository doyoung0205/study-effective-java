package chapter10.item73;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class HigherException extends RuntimeException {
    public HigherException(String message) {
        super(message);
    }

    public HigherException(Throwable cause) {
        super(cause);
    }

    public HigherException(String message, Throwable cause) {
        super(message, cause);
    }
}
