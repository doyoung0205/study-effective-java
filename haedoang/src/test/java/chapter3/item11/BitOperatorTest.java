package chapter3.item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/22
 * description :
 */
public class BitOperatorTest {

    @Test
    @DisplayName("비트 연산자한 값은 2를 곱하는 것과 같음을 증명한다")
    public void bitOperatorPositiveNumberPositive() {
        // given
        int i = 2;

        // when
        int actual = i << 1;
        int expected = i * 2;

        // then
        assertThat(actual).isEqualTo(4);
        assertThat(expected).isEqualTo(4);
        assertThat(actual).isEqualTo(expected);
    }
}
