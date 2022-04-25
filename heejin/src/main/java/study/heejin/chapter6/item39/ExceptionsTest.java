package study.heejin.chapter6.item39;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 배열 매개변수를 받는 애너테이션
 * 명시한 예외중 한 가지 이상을 던져야만 성공하는 테스트 메서드용 애너테이션
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionsTest {
    Class<? extends Throwable>[] value();
}
