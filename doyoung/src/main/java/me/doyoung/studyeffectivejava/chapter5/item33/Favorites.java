package me.doyoung.studyeffectivejava.chapter5.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 타입 안전 이종 컨테이너 패턴 (199-202쪽)
public class Favorites {
    // 코드 33-3 타입 안전 이종 컨테이너 패턴 - 구현 (200쪽)
    private Map<Class<?>, Object> favorites = new HashMap<>();


    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

//    // 코드 33-4 동적 형변환으로 런타임 타입 안전성 확보 (202쪽)
//    public <T> void putFavorite(Class<T> type, T instance) {
//        favorites.put(Objects.requireNonNull(type), type.cast(instance));
//    }

    // 코드 33-2 타입 안전 이종 컨테이너 패턴 - 클라이언트 (199쪽)
    public static void main(String[] args) {
        Favorites f = new Favorites();

        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);

        f.putFavorite((Class) Integer.class, "");

        System.out.printf("%s %x %s%n", favoriteString,
                favoriteInteger, favoriteClass.getName());
    }
}
