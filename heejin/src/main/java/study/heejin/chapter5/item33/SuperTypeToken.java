package study.heejin.chapter5.item33;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeToken {

    public static void main(String[] args) throws NoSuchFieldException {
        Super<String> s = new Super<>();
        Class<?> type = s.getClass().getDeclaredField("value").getType();
        System.out.println("super type = " + type); // 런타임 시점에 타입 정보가 사라져서 타입이 Object가 나옴

        // Nested Static Class
        SubSuper sub = new SubSuper();
        Type t = sub.getClass().getGenericSuperclass();
        ParameterizedType pType = (ParameterizedType) t;
        System.out.println("nested static class pType = " + pType.getActualTypeArguments()[0]);

        // Local Class
        class SubLocal extends Super<List<Integer>> {
        }
        SubLocal subLocal = new SubLocal();
        Type subLocalType = subLocal.getClass().getGenericSuperclass();
        ParameterizedType pType2 = (ParameterizedType) subLocalType;
        System.out.println("local class pType = " + pType2.getActualTypeArguments()[0]);

        // Anonymous Class
        Super anonymousSub = new Super<List<Double>>() {
        };
        Type anonymousType = anonymousSub.getClass().getGenericSuperclass();
        ParameterizedType pType3 = (ParameterizedType) anonymousType;
        System.out.println("anonymous class pType = " + pType3.getActualTypeArguments()[0]);
    }

    public static class Super<T> {
        T value;
    }

    public static class SubSuper extends Super<List<String>> {

    }
}
