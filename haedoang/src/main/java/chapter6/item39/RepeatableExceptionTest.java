package chapter6.item39;

import java.lang.annotation.*;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface RepeatableExceptionTest {
    Class<? extends Throwable> value();
}
