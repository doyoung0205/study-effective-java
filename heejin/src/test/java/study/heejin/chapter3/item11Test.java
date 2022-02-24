package study.heejin.chapter3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter3.item11.DiffHashCodeNumber;
import study.heejin.chapter3.item11.PhoneNumber;
import study.heejin.chapter3.item11.SameHashCodeNumber;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class item11Test {

    @Test
    @DisplayName("해시코드는 다른 엔트리끼리 동치성 비교를 하지 않음")
    void hashCodeNul() {
        // given
        Map<PhoneNumber, String> map = new HashMap<>();
        map.put(new PhoneNumber(707, 867, 5309), "제니");

        // when
        String Jenny = map.get(new PhoneNumber(707, 867, 5309));

        // then
        assertThat(Jenny).isNull();
    }

    @Test
    @DisplayName("hashCode가 동일한 값을 리턴할 때 - 1만")
    void sameHashCodeNumber() {
        // given
        long start = System.currentTimeMillis();
        Map<SameHashCodeNumber, Integer> sameHashCodeNumbers = IntStream.range(1, 10_000)
                .mapToObj(SameHashCodeNumber::new)
                .collect(Collectors.toMap(sameHashCodeNumber -> sameHashCodeNumber, SameHashCodeNumber::getNumber));

        // when
        Integer actual = sameHashCodeNumbers.get(new SameHashCodeNumber(100));
        long end = System.currentTimeMillis();

        // then
        System.out.println(
                String.format("총 실행 시간: %d", end - start)
        );
        assertThat(actual).isEqualTo(100);
    }

    @Test
    @DisplayName("hashCode가 다른 값을 리턴할 때 - 500만")
    void diffHashCodeNumber() {
        // given
        long start = System.currentTimeMillis();
        Map<DiffHashCodeNumber, Integer> diffHashCodeNumbers = IntStream.range(1, 5_000_000)
                .mapToObj(DiffHashCodeNumber::new)
                .collect(Collectors.toMap(diffHashCodeNumber -> diffHashCodeNumber, DiffHashCodeNumber::getNumber));

        // when
        Integer actual = diffHashCodeNumbers.get(new DiffHashCodeNumber(5_000));
        long end = System.currentTimeMillis();

        // then
        System.out.println(
                String.format("총 실행 시간: %d", end - start)
        );
        assertThat(actual).isEqualTo(5000);
    }
}
