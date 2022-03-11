package study.heejin.chapter4.item20;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
    @Override
    public V setValue(V value) {
        // 변경 가능한 엔트리는 이 메서드를 반드시 재정의해야 한다.
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) { // Map.Entry.equals의 일반 규약을 구현한다.
        if (o == this) return true;
        if (!(o instanceof Map.Entry)) return false;
        Map.Entry<?,?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(),   getKey())
                && Objects.equals(e.getValue(), getValue());
    }

    @Override
    public int hashCode() { // Map.Entry.hashCode의 일반 규약을 구현한다.
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
