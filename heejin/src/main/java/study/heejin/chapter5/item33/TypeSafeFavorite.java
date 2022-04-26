package study.heejin.chapter5.item33;

import java.lang.reflect.ParameterizedType;
import java.util.*;

public class TypeSafeFavorite {

    Map<TypeReference<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(TypeReference<T> typeReference, T instance) {
        favorites.put(typeReference, instance);
    }

    public <T> T getFavorite(TypeReference<T> typeReference) {
        if (typeReference.type instanceof Class<?>) {
            return ((Class<T>) typeReference.type).cast(favorites.get(typeReference));
        } else {
            return ((Class<T>) ((ParameterizedType) typeReference.type).getRawType()).cast(favorites.get(typeReference));
        }
    }

    public static void main(String[] args) {
        TypeSafeFavorite f = new TypeSafeFavorite();

        f.putFavorite(new TypeReference<Integer>() {}, 1);
        f.putFavorite(new TypeReference<String>() {}, "haha");
        f.putFavorite(new TypeReference<List<Integer>>() {}, Arrays.asList(1, 2, 3, 4, 5));
        f.putFavorite(new TypeReference<List<String>>() {}, Arrays.asList("개", "고양이", "토끼"));

        System.out.println("type Integer = " + f.getFavorite(new TypeReference<Integer>() {}));
        System.out.println("type String = " + f.getFavorite(new TypeReference<String>() {}));
        System.out.println("type List<Integer> = " + f.getFavorite(new TypeReference<List<Integer>>() {}));
        System.out.println("type List<String> = " + f.getFavorite(new TypeReference<List<String>>() {}));
    }
}
