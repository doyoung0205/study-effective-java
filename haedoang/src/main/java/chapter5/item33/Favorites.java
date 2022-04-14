package chapter5.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/04/08
 * description :
 */
@Cool
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    public <T> void putFavoriteCast(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public int size() {
        return favorites.size();
    }
}
