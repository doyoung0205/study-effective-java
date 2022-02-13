package chapter2.item6;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item6
 * fileName : NumberUtilTest
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
class NumberUtilTest {

    @Test
    @DisplayName("Pattern 객체 성능 테스트")
    public void patterObjectTest() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        IntStream.range(0, 10000000)
                .forEach(i -> NumberUtil.isNumber("1234"));

        stopWatch.stop();

        long result1 = stopWatch.getTime();

        stopWatch.reset();
        stopWatch.start();

        IntStream.range(0, 10000000)
                .forEach(i -> NumberUtil.isNumberRefactoring("1234"));

        stopWatch.stop();

        long result2 = stopWatch.getTime();


        assertThat(result1).isGreaterThan(result2);
    }
}
