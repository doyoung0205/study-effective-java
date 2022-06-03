package study.heejin.chapter9.item61;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ComparatorTest {

    @Test
    @DisplayName("언박싱해서 결함을 해결")
    void naturalOrder_UnBoxing() {
        // given
        Comparator<Integer> naturalOrder = (iBoxed, jBoxed) -> {
            int i = iBoxed;
            int j = jBoxed;
            return i < j ? -1 : (i == j ? 0 : 1);
        };

        // when
        int result = naturalOrder.compare(new Integer(42), new Integer(42));

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("기본 타입을 다루는 비교자")
    void naturalOrder() {
        // given
        Comparator<Integer> naturalOrder = Comparator.naturalOrder();

        // when
        int result = naturalOrder.compare(new Integer(42), new Integer(42));

        // then
        assertThat(result).isEqualTo(0)
                .as("Comparator.naturalOrder()를 사용하여 객체 참조 식별성이 아닌 값을 비교");
    }
}