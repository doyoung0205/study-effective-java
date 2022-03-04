package chapter4.item19;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/03/04
 * description :
 */
class CalculatorTest {

    @Test
    @DisplayName("계산기 오브젝트는 정수 계산만 가능하다")
    public void calculatorTest() {
        // given
        final Calculator calculator = new Calculator();

        // when
        final Object actual = calculator.plus("5", "9");

        // then
        assertThat(actual).isEqualTo(14);

        // then
        assertThatThrownBy(() -> calculator.plus("5.4", "9.1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("개선된 계산기 오브젝트는 소수점 계산도 가능하다")
    public void expensiveCalculatorTest() {
        // given
        final ExpensiveCalculator calculator = new ExpensiveCalculator();

        // when
        final Object actual = calculator.plus("5", "9");

        // then
        assertThat(actual).isEqualTo(14.0);

        // when
        final Object actual2 = calculator.plus("5.4", "9.1");

        // then
        assertThat(actual2).isEqualTo(14.5);

        // when
        assertThatThrownBy(() -> calculator.minus("5.4", "9.1"))
                .isInstanceOf(IllegalArgumentException.class)
                .as("재정의하지 않은 calcuator.minus는 정수이외의 아규먼틑에는 예외를 반환한다")
                .as("이런 부부에는 API문서에 설명을 남겨주어야 한다");
    }
}