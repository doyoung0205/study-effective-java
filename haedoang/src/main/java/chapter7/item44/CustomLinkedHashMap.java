package chapter7.item44;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author : haedoang
 * date : 2022/05/02
 * description :
 */
public class CustomLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > 5;
    }
}
