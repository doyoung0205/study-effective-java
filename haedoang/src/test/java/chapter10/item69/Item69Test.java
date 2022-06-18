package chapter10.item69;

import chapter10.item69.NumberUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class Item69Test {

    @Test
    @DisplayName("예외를 직접 발새시키는 코듵 ㅔ스트")
    public void antiHandlingException() {
        // given
        final int[] nums = new int[5];

        // when
        NumberUtils.pushNumber(nums);

        // then
        assertThat(nums).containsExactly(1, 2, 3, 4, 5)
                .as("강제로 예외를 발생시켜서 컬렉션의 작업을 종료한다");
    }

    @Test
    @DisplayName("켈력선 인덱스 예외는 for문을 통해 안전하게 해결할 수 있다")
    public void handlingException() {
        // given
        final int[] nums = new int[5];

        // when
        NumberUtils.smartPushNumber(nums);

        // then
        assertThat(nums).containsExactly(1, 2, 3, 4, 5);
    }

    @RepeatedTest(50)
    @DisplayName("예외를 사용한 코드가 표준 관용구보다 느리다")
    public void comparingAdNumberPerformance() {
        // given
        StopWatch watch = new StopWatch();
        final int[] numbers = new int[100];

        // when
        watch.start();
        NumberUtils.pushNumber(numbers);
        watch.stop();

        final long result1 = watch.getNanoTime();

        watch.reset();
        watch.start();
        NumberUtils.smartPushNumber(numbers);
        watch.stop();

        final long result2 = watch.getNanoTime();

        // then
        System.out.println("result1 : " + result1);
        System.out.println("result2 : " + result2);
        assertThat(result1).isGreaterThan(result2);
    }

    @Test
    @DisplayName("iterator 반복 테스트")
    public void iteratorTest() {
        // given
        final List<String> players = List.of("헤리케인", "손흥민", "무하마드 살라", "홀랜드");

        // then
        for (Iterator<String> p = players.iterator(); p.hasNext(); ) {
            final String player = p.next();
            System.out.println(player);
        }
    }
}
