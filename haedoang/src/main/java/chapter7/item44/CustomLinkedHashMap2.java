package chapter7.item44;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author : haedoang
 * date : 2022/05/02
 * description :
 */
public class CustomLinkedHashMap2<K, V> extends LinkedHashMap<K, V> {
    private EldestEntryRemovalFunction<K, V> function;

    public CustomLinkedHashMap2(EldestEntryRemovalFunction<K, V> function) {
        this.function = function;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return function.remove(this, eldest);
    }
}
