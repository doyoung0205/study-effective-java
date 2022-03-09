package chapter4.item17;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/03/09
 * description :
 */
class MyBigIntegerTest {
    @Test
    @DisplayName("BigInteger 객체 주의할점 테스트")
    public void myBigIntegerTest() {
        // given
        final MyBigInteger myBigInteger = new MyBigInteger("5000");

        // when
        myBigInteger.addNumber(5);

        // then
        assertThat(myBigInteger.intValue()).isEqualTo(5005)
                .as("BigInteger, BigDecimal은 가변 객체 클래스(final이 아님)이기 때문에")
                .as("재정의할 수가 있다. 즉, 불변 객체가 아니게 동작할 수 있다")
                .as("신뢰하지 않은 클라이언트의 BigInteger, BigDecimal의 사용은 객체 검증이 필요하다");

        assertThat(InstanceUtil.isBigInteger(myBigInteger)).isFalse()
                .as("BigInteger 클래스 검증");
    }
}