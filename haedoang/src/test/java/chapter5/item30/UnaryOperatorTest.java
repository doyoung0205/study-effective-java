package chapter5.item30;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/01
 * description :
 */
public class UnaryOperatorTest {
    @Test
    @DisplayName("unaryOperater 는 입력 인수와 같은 타입의 결과를 반환한다")
    public void unaryOperatorTest() {
        // given
        UnaryOperator<Integer> operator = n -> n;
        UnaryOperator<Integer> squareOperator = n -> n * n;
        UnaryOperator<Integer> multiplyTo = n -> n * 2;

        // when
        final Integer actual = operator.apply(10);
        final Integer actual2 = squareOperator.apply(10);


        // then
        assertThat(actual).isEqualTo(10);
        assertThat(actual2).isEqualTo(100);

        // when
        final Integer actual3 = operator.andThen(multiplyTo).apply(10);

        // then
        assertThat(actual3).isEqualTo(20).as("operator의 결과가 andThen() 메서드 내 오퍼레이터에 전달된다");
    }
}
