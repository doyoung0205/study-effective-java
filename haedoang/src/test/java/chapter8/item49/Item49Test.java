package chapter8.item49;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName : chapter8
 * fileName : Item49Test
 * author : haedoang
 * date : 2022-05-18
 * description :
 */
public class Item49Test {

    @Test
    @DisplayName("나머지 구하기")
    public void mod() {
        // given
        MyBigInteger number = MyBigInteger.valueOf(BigInteger.TEN);

        // when
        BigInteger actual = number.mod(BigInteger.TWO);

        // then
        assertThat(actual.intValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("나머지 구하기 유효하지 매개변수 처리하기")
    public void modValidateParam() {
        // given
        MyBigInteger number = MyBigInteger.valueOf(BigInteger.TEN);

        // then
        assertThatThrownBy(() -> number.mod(BigInteger.ZERO))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("양수여야 합니다");
    }

    @Test
    @DisplayName("requireNonNull 테스트")
    public void personValidateParam() {
        // then
        assertThatThrownBy(() -> Person.valueOf(null, 32))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("단언문 유효성 검증 테스트1")
    public void wineValidatePrivateMethod() {
        // given
        Wine actual = Wine.valueOf("ISCAY", BigInteger.valueOf(60_000));

        // then
        assertThat(actual.getName()).isEqualTo("ISCAY");
    }

    @Test
    @DisplayName("단언문 유효성 검증 테스트2")
    public void wineValidatePrivateMethod2() {
        // then
        assertThatThrownBy(() -> Wine.valueOf(null, BigInteger.TEN))
                .isInstanceOf(AssertionError.class);

        // then
        assertThatThrownBy(() -> Wine.valueOf(null, BigInteger.valueOf(-50_000)))
                .isInstanceOf(AssertionError.class)
                .as("런타임 시 성능 저하가 없다")
                .as("예외를 명확하게 표시하지 못하는 단점이 있다");
    }
}
