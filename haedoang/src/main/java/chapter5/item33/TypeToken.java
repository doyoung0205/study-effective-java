package chapter5.item33;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : haedoang
 * date : 2022/04/09
 * description :
 */
public class TypeToken {

    static class TypesafeMap {
        Map<Class<?>, Object> map = new HashMap<>();

        <T> void put(Class<T> clazz, T value) {
            map.put(clazz, value);
        }

        <T> T get(Class<T> clazz) {
            return clazz.cast(map.get(clazz));
        }
    }

    public static void main(String[] args) {
        final TypesafeMap m = new TypesafeMap();

        m.put(String.class, "hello");
        m.put(Integer.class, 1);
//        m.put(List<String>.class, Arrays.asList(1, 2, 3));
        m.put(List.class, Arrays.asList("A", "B", "C"));

        System.out.println(m.get(String.class));
        System.out.println(m.get(Integer.class));
        System.out.println(m.get(List.class));

    }
}
