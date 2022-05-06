package chapter7.item45;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter7.item45
 * fileName : Item45Test
 * author : haedoang
 * date : 2022-05-03
 * description :
 */
public class Item45Test {

    @Test
    @DisplayName("다양한 종단 연산")
    public void variousTerminalOperation() {
        // given
        int[] array = {1, 2, 2, 3, 4, 5};

        // when
        List<Integer> numList = Stream.iterate(1, n -> n + 1)
                .limit(5).collect(Collectors.toList());

        // then
        assertThat(numList).hasSize(5);

        Set<Integer> numSet = Stream.iterate(1, n -> n + 1)
                .limit(5).collect(Collectors.toSet());

        // then
        assertThat(numSet).hasSize(5);

        Map<Integer, Long> numMap = Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        // then
        assertThat(numMap.get(1)).isEqualTo(1);
        assertThat(numMap.get(2)).isEqualTo(2);
    }
}
