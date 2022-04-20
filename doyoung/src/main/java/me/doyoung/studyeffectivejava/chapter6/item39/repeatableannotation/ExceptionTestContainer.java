package me.doyoung.studyeffectivejava.chapter6.item39.repeatableannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 반복 가능한 애너테이션의 컨테이너 애너테이션 (244쪽)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTestContainer {
    ExceptionTest[] value();
}
