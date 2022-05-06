package study.heejin.chapter7.item44;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;

public class LinkedHashMapCache2<K, V> extends LinkedHashMap<K, V> {

    private final BiPredicate<Map<K, V>, Map.Entry<K, V>> function;

    private LinkedHashMapCache2(BiPredicate<Map<K, V>, Map.Entry<K, V>> function) {
        this.function = function;
    }

    public static <K, V> LinkedHashMapCache2 of(BiPredicate<Map<K, V>, Map.Entry<K, V>> function) {
        return new LinkedHashMapCache2(function);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return function.test(this, eldest);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedHashMapCache2)) {
            return false;
        }
        LinkedHashMapCache2<?, ?> that = (LinkedHashMapCache2<?, ?>) o;
        return super.equals(o) && Objects.equals(function, that.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), function);
    }
}
