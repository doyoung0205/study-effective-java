package chapter4.item18;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter4.item18
 * fileName : InstrumentedHashSetTest
 * author : haedoang
 * date : 2022-03-04
 * description :
 */
class InstrumentedHashSetTest {

    @Test
    @DisplayName("HashSet 상속 addAll Override 주의 테스트")
    public void inheritanceHashSet() {
        // given
        InstrumentedHashSet<String> strings = new InstrumentedHashSet<>();

        // when
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        strings.add("E");

        // then
        assertThat(strings.getAddCount()).isEqualTo(5);

        // when
        strings.addAll(Sets.newHashSet("F", "U"));

        // then
        assertThat(strings.getAddCount()).isEqualTo(9)
                .as("addAll() 메서드는 내부적으로 add() 메서드를 iterate 한다")
                .as("자기사용(self-use)은 하위 클래스가 깨지기 쉽다");
    }

    @Test
    @DisplayName("컴포지션을 이용한 HashSet")
    public void compositionHashSet() {
        // given
        InstrumentedHashSetNew<String> strings = new InstrumentedHashSetNew<>(Sets.newHashSet());

        // when
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        strings.add("E");

        // then
        assertThat(strings.getAddCount()).isEqualTo(5);

        // when
        strings.addAll(Sets.newHashSet("F", "U"));

        // then
        assertThat(strings.getAddCount()).isEqualTo(7)
                .as("전달 메서드를 사용하여 위와 같은 재정의된 메서드가 호출되지 않는다");
    }

    @Test
    @DisplayName("컬렉션 래퍼 클래스를 제공하는 구아바 테스트")
    public void compositionHashSetWithGuava() {
        // given
        InstrumentedHashSetGuava<String> strings = new InstrumentedHashSetGuava<>(Sets.newHashSet());

        // when
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        strings.add("E");

        // then
        assertThat(strings.getAddCount()).isEqualTo(5);

        // when
        strings.addAll(Sets.newHashSet("F", "U"));

        // then
        assertThat(strings.getAddCount()).isEqualTo(7)
                .as("전달 메서드를 사용하여 위와 같은 재정의된 메서드가 호출되지 않는다");
    }
}
