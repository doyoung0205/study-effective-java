package study.heejin.chapter5.item33;

import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeSafeFavorite2 {

    Map<ParameterizedTypeReference<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(ParameterizedTypeReference<T> parameterizedType, T instance) {
        favorites.put(parameterizedType, instance);
    }

    public <T> T getFavorite(ParameterizedTypeReference<T> parameterizedType) {
        if (parameterizedType.getType() instanceof Class<?>) {
            return ((Class<T>) parameterizedType.getType()).cast(favorites.get(parameterizedType));
        } else {
            return ((Class<T>) ((ParameterizedType) parameterizedType.getType()).getRawType()).cast(favorites.get(parameterizedType));
        }
    }

    public static void main(String[] args) {
        TypeSafeFavorite2 f = new TypeSafeFavorite2();

        f.putFavorite(new ParameterizedTypeReference<>() {}, 1);
        f.putFavorite(new ParameterizedTypeReference<>() {}, "haha");
        f.putFavorite(new ParameterizedTypeReference<>() {}, Arrays.asList(1, 2, 3, 4, 5));
        f.putFavorite(new ParameterizedTypeReference<>() {}, Arrays.asList("개", "고양이", "토끼"));

        System.out.println("type Integer = " + f.getFavorite(new ParameterizedTypeReference<Integer>() {}));
        System.out.println("type String = " + f.getFavorite(new ParameterizedTypeReference<String>() {}));
        System.out.println("type List<Integer> = " + f.getFavorite(new ParameterizedTypeReference<List<Integer>>() {}));
        System.out.println("type List<String> = " + f.getFavorite(new ParameterizedTypeReference<List<String>>() {}));
    }
}
