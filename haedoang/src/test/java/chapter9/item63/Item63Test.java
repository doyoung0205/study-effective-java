package chapter9.item63;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/06/05
 * description :
 */
public class Item63Test {
    @Test
    @DisplayName("문자열 연결 성능 비교")
    public void compareLinkString() {
        // given
        String str = "";
        final StringBuilder sb = new StringBuilder();
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        for (int i = 0; i < 100_000; i++) {
            str += RandomStringUtils.randomAlphabetic(4);
        }

        stopWatch.stop();

        final long result1 = stopWatch.getTime();

        stopWatch.reset();

        stopWatch.start();
        for (int i = 0; i < 100_000; i++) {
            sb.append(RandomStringUtils.randomAlphabetic(4));
        }

        stopWatch.stop();
        final long result2 = stopWatch.getTime();

        // then
        assertThat(str.length()).isEqualTo(sb.length());
        assertThat(result1).isGreaterThan(result2);
    }
}
