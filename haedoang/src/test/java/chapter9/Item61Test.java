package chapter9;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/06/05
 * description :
 */
public class Item61Test {

    @SuppressWarnings("deprecation")
    @Test
    @DisplayName("BoxType 비교 시 == 로 비교하면 안 된다")
    public void compareNG() {
        // given
        Comparator<Integer> order =
                (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);

        // when
        final int actual = order.compare(new Integer(5), new Integer(5));

        // then
        assertThat(actual).isEqualTo(1)
                .as("Box Type은 <, >, <=, >= 연산자에서 기본 타입으로 비교한다");
    }

    @SuppressWarnings("deprecation")
    @Test
    @DisplayName("BoxType 비교 시 오토박싱으로 처리하자")
    public void compareWithAutoBoxing() {
        // given
        Comparator<Integer> order =
                (iBoxed, jBoxed) -> {
                    int i = iBoxed;
                    int j = jBoxed;
                    return (i < j) ? -1 : (i == j ? 0 : 1);
                };

        // when
        final int actual = order.compare(new Integer(5), new Integer(5));

        // then
        assertThat(actual).isEqualTo(0);
    }

    @Test
    @DisplayName("박싱 타입과 기본 타입의 혼용 시 박싱이 자동으로 풀려 NullPointerException이 발생할 수 있다")
    public void boxTypeWithPrimitiveType() {
        // then
        assertThatThrownBy(() -> Unbelievable.run())
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("boxType과 시간 비교하기")
    public void boxTypePerformance() {
        // given
        StopWatch stopWatch = new StopWatch();

        Long result1 = 0L;
        long result2 = 0;

        // when
        stopWatch.start();

        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            result1 += i;
        }

        stopWatch.stop();

        final long time1 = stopWatch.getTime();

        stopWatch.reset();
        stopWatch.start();

        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            result2 += i;
        }

        final long time2 = stopWatch.getTime();

        // then
        System.out.println(time1);
        System.out.println(time2);
        assertThat(time1).isGreaterThan(time2);
    }
}
