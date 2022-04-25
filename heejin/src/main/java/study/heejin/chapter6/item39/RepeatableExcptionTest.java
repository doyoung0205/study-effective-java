package study.heejin.chapter6.item39;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface RepeatableExcptionTest {
    Class<? extends Throwable> value();
}
