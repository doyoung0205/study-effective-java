package chapter2.item6;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item6
 * fileName : AutoboxingTest
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
public class AutoboxingTest {

    @Test
    @DisplayName("불필요한 autoboxing은 성능을 저하시킨다.")
    public void autoboxingTest() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        sum(1, 1000000);

        stopWatch.stop();

        long result1 = stopWatch.getTime();

        stopWatch.reset();

        stopWatch.start();

        sumRefactoring(1, 1000000);

        stopWatch.stop();

        long result2 = stopWatch.getTime();

        assertThat(result1).isGreaterThan(result2);
    }


    private Long sum(int start, int end) {
        Long sum = 0L;
        for (int i = start; i < end; i++) {
            sum += i;
        }
        return sum;
    }

    private long sumRefactoring(int start, int end) {
        long sum = 0L;
        for (int i = start; i < end; i++) {
            sum += i;
        }
        return sum;
    }
}
