package study.heejin.chapter9.item61;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BrokenComparatorTest {

    @Test
    @DisplayName("잘 동작하지만 결함이 있는 코드")
    void naturalOrder_defect() {
        // given
        List<Integer> numbers = generateNumbers();
        System.out.println("first = " + numbers.get(0));
        System.out.println("last = " + numbers.get(numbers.size() - 1));

        // when
        Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);
        Collections.sort(numbers, naturalOrder);

        // then
        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(numbers.size() - 1)).isEqualTo(1000000);
    }

    @Test
    @DisplayName("결함을 드러낸 코드")
    void naturalOrder_broken() {
        // given
        Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);

        // when
        int result = naturalOrder.compare(new Integer(42), new Integer(42));

        // then
        assertThat(result).isEqualTo(1)
                .as("첫번째 new Integer(42)가 두번째 new Integer(42) 보다 크다는 잘못된 결과")
                .as("비교 연산자에서는 컴파일러에서 언박싱");
    }

    @Test
    @DisplayName("비교 연산자 언박싱")
    void comparisonOperator() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        assertThat(a.intValue() == b.intValue()).isTrue();
        assertThat(a == b).isFalse()
                .as("값이 같지만 객체의 참조값으로 비교하여 결과값이 틀림");

        Integer c = new Integer(1);
        Integer d = new Integer(0);
        assertThat(c.intValue() > d.intValue()).isTrue();
        assertThat(c > d).isTrue().isTrue()
                .as("비교 연산자에서는 자동으로 언박싱 되어 값을 비교");

        assertThat(c.intValue() < d.intValue()).isFalse();
        assertThat(c < d).isFalse()
                .as("비교 연산자에서는 자동으로 언박싱 되어 값을 비교");
    }

    private List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 1000000; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        return numbers;
    }

}