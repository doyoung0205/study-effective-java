package chapter6.item39;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}
