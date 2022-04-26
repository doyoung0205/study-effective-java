package study.heejin.chapter5.item33;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class PrintAnnotation {

    public static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
        Class<?> annotationType = null; // 비한정적 타입 토큰

        try {
            annotationType = Class.forName(annotationTypeName);

        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }

        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("사용법: java PrintAnnotation <class> <annotation>");
            System.exit(1);
        }

        String className = args[0];
        String annotationTypeName = args[1];
        Class<?> clazz = Class.forName(className);
        System.out.println(getAnnotation(clazz, annotationTypeName));
    }
}
