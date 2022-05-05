package chapter7.item44;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * author : haedoang
 * date : 2022/05/02
 * description :
 */
public class CustomLinkedHashMap3<K, V> extends LinkedHashMap<K, V> {
    private BiPredicate<Map<K, V>, Map.Entry<K, V>> biPredicate;

    public CustomLinkedHashMap3(BiPredicate<Map<K, V>, Map.Entry<K, V>> biPredicate) {
        this.biPredicate = biPredicate;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return biPredicate.test(this, eldest);
    }
}
