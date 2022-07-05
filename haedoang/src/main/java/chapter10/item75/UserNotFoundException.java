package chapter10.item75;

/**
 * author : haedoang
 * date : 2022/06/25
 * description :
 */
public class UserNotFoundException extends RuntimeException {
    public static final String MESSAGE = "회원을 찾을 수 없습니다.";

    public UserNotFoundException() {
    }

    public UserNotFoundException(Long userId) {
        super(String.format(MESSAGE + " %d", userId));
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
