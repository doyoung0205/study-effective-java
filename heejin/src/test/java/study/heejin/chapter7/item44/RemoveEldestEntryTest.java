package study.heejin.chapter7.item44;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RemoveEldestEntryTest {
    public static final int INITIAL_CAPACITY = 3;

    @Test
    @DisplayName("LinkedHashMap의 protected 메서드 재정의")
    void removeEldestEntryOverride() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > INITIAL_CAPACITY;
            }
        };

        put(map);

        assertThat(map).hasSize(INITIAL_CAPACITY);
    }

    @Test
    @DisplayName("함수형 인터페이스를 사용")
    void removeEldestEntryFunctionalInterface() {
        LinkedHashMapCache map = LinkedHashMapCache.of((m, eldest) -> m.size() > INITIAL_CAPACITY);

        put(map);

        assertThat(map).hasSize(INITIAL_CAPACITY);
    }

    @Test
    @DisplayName("표준 함수형 인터페이스 사용")
    void removeEldestEntryBiPredicate() {
        LinkedHashMapCache2 map = LinkedHashMapCache2.of((m, eldest) -> m.size() > INITIAL_CAPACITY);

        put(map);

        assertThat(map).hasSize(INITIAL_CAPACITY);
    }

    private void put(LinkedHashMap<String, Integer> map) {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
    }
}