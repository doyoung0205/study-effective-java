package chapter7.stream.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/05/22
 * description :
 */
public class SkipAndLimitTest {
    @Test
    @DisplayName("skip 연산은 처음 n개 요소를 버림 처리한다")
    public void skipTest() {
        // when
        final List<Integer> actual = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i % 2 == 0)
                .skip(2)
                .collect(Collectors.toList());

        // then
        assertThat(actual).containsExactly(6, 8, 10)
                .as("처음 skip(n)개의 요소를 버림 처리한다.");
    }

    @Test
    @DisplayName("limit 연산은 연산 결과 이후의 값을 무시한다")
    public void limitTest() {
        // when
        final List<Integer> actual = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i % 2 == 0)
                .limit(2)
                .collect(Collectors.toList());

        // then
        assertThat(actual).containsExactly(2, 4)
                .as("limit(n)개 요소까지만 연산을 수행한다.");
    }

    @Test
    @DisplayName("3번째 결과값부터 10개를 반환하는 짝수 구하기 테스트")
    public void combiningSkipAndLimit() {
        // then
        final List<Integer> actual = getEvenNumbers(3, 10);

        assertThat(actual).containsExactly(6, 8, 10, 12, 14, 16, 18, 20, 22, 24)
                .as("skip과 lmit를 조합한 스트림 사용 테스트")
                .as("페이징 처리 시 유용하게 쓸 수 있음");
    }

    private static List<Integer> getEvenNumbers(int offset, int limit) {
        return Stream.iterate(0, i -> i + 1)
                .filter(i -> i % 2 == 0)
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
