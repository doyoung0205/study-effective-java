package chapter4.item17;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/03/09

 */
public class LCMTest {
    @Test
    @DisplayName("최소공배수 구하기")
    public void lcm() {
        // then
        assertThat(NumberUtil.lcm(10,16)).isEqualTo(80);

        assertThat(NumberUtil.lcm(0, 10)).isEqualTo(0)
                .as("두 숫자 중 하나라도 0인 경우 0을 반환한다");

        assertThat(NumberUtil.lcm(12,-18)).isEqualTo(36)
                .as("음수는 절대값으로 처리한다");
    }

    @Test
    @DisplayName("최소공배수 구하기 - 큰 숫자(바이너리 GCD 사용)")
    public void lcmBigNumber() {
        // then
        assertThat(NumberUtil.lcm(BigInteger.valueOf(10), BigInteger.valueOf(16))).isEqualTo(BigInteger.valueOf(80));

        assertThat(NumberUtil.lcm(BigInteger.ZERO, BigInteger.TEN)).isEqualTo(BigInteger.ZERO);

        assertThat(NumberUtil.lcm(BigInteger.valueOf(12),BigInteger.valueOf(18))).isEqualTo(BigInteger.valueOf(36));
    }
}
