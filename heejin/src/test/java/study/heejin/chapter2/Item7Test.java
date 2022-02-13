package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item7.Stack;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Item7Test {

    @Test
    @DisplayName("stack의 메모리 누수 확인")
    void memoryLeakPop() {
        // given
        Stack stack = new Stack();
        stack.push("first");
        stack.push("second");
        stack.push("third");

        // when
        Object element = stack.memoryLeakPop();

        // then
        assertThat(element).isEqualTo("third");
        assertThat(stack.getSize()).isEqualTo(2);
        assertThat(stack.getElements()[2]).isEqualTo("third");
    }

    @Test
    @DisplayName("제대로 구현한 pop")
    void pop() {
        // given
        Stack stack = new Stack();
        stack.push("first");
        stack.push("second");
        stack.push("third");

        // when
        Object element = stack.pop();

        // then
        assertThat(element).isEqualTo("third");
        assertThat(stack.getSize()).isEqualTo(2);
        assertThat(stack.getElements()[2]).isNull();
    }

    @Test
    @DisplayName("새 요소가 LinkedHashMap에 추가될 때마다 가장 오래된 항목이 맵에서 제거")
    void linkedHashMap_removeEldestEntry() {
        // given
        int max = 5;
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > max;
            }
        };
        linkedHashMap.put(1, "one");
        linkedHashMap.put(2, "two");
        linkedHashMap.put(3, "three");
        linkedHashMap.put(4, "four");
        linkedHashMap.put(5, "five");

        // when
        linkedHashMap.put(6, "six");

        // then
        assertThat(linkedHashMap.size()).isEqualTo(5);
        assertThat(linkedHashMap.get(1)).isNull();
    }
}
