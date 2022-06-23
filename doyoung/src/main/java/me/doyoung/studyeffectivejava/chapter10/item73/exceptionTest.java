bpackage me.doyoung.studyeffectivejava.chapter10.item73;

import lombok.Getter;

public class exceptionTest {


    public static void main(String[] args) {

        var runtimeException = new RuntimeException();
        var throwableException = new Throwable();

        System.out.println("new UserNotFoundException(runtimeException).getCause() = "
                + new UserNotFoundException(runtimeException).getCause());
        System.out.println("new UserNotFoundException(throwableException).getCause() = "
                + new UserNotFoundException(throwableException).getCause());
    }


    @Getter
    static class BaseException extends RuntimeException {
        private final ErrorCode code;

        public BaseException(ErrorCode code) {
            super(code.getMsg());
            this.code = code;
        }
    }


    @Getter
    public enum ErrorCode {
        COMMON_SYSTEM_ERROR("일시적인 오류가 발생하였습니다.");
        private final String msg;

        ErrorCode(String msg) {
            this.msg = msg;
        }
    }

    static class UserNotFoundException extends RuntimeException {
        UserNotFoundException(Throwable cause) {
            super(cause);
        }

        UserNotFoundException(RuntimeException cause) {
            super(cause);
        }
    }

}
