package study.heejin.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter4.item18.InstrumentedHashSet;
import study.heejin.chapter4.item18.InstrumentedSet;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class Item18Test {

    @Test
    @DisplayName("상속으로 재정의한 메서드 오작동")
    void hashSetAdd() {
        // given
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();

        // when
        s.addAll(List.of("틱", "탁탁", "펑"));

        // then
        assertThat(s.getAddCount()).isEqualTo(6);   // 3 을 반환할 것을 기대하지만 6 을 반환
    }

    @Test
    @DisplayName("컴포지션으로 수정한 메서드")
    void hashSetCompositon() {
        // given
        InstrumentedSet<String> s = new InstrumentedSet<>(new HashSet<>());

        // when
        s.addAll(List.of("틱", "탁탁", "펑"));

        // then
        assertThat(s.getAddCount()).isEqualTo(3);
    }
}
