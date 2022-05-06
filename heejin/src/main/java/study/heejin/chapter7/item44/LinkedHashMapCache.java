package study.heejin.chapter7.item44;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LinkedHashMapCache extends LinkedHashMap<String, Integer> {

    private final EldestEntryRemovalFunction<String, Integer> function;

    private LinkedHashMapCache(EldestEntryRemovalFunction<String, Integer> function) {
        this.function = function;
    }

    public static LinkedHashMapCache of(EldestEntryRemovalFunction<String, Integer> function) {
        return new LinkedHashMapCache(function);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
        return function.remove(this, eldest);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedHashMapCache)) {
            return false;
        }
        LinkedHashMapCache that = (LinkedHashMapCache) o;
        return super.equals(o) && Objects.equals(function, that.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), function);
    }
}
