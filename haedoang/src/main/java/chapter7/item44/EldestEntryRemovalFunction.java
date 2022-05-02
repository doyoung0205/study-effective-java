package chapter7.item44;

import java.util.Map;

/**
 * author : haedoang
 * date : 2022/05/02
 * description :
 */
@FunctionalInterface
public interface EldestEntryRemovalFunction<K, V> {
    boolean remove(Map<K, V> map, Map.Entry<K, V> eldest);
}
