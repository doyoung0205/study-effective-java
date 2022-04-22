package chapter6.item37;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/19
 * description :
 */
public class FunctionalProgrammingTest {

    @Test
    @DisplayName("supplier는 파라미터가 없이 값을 반환하는 함수형 인터페이스이다")
    public void useSupplier() {
        // given
        final Supplier<String> mySupplier = new Supplier<>() {
            @Override
            public String get() {
                return "Hello This is Supplier";
            }
        };

        Supplier<String> lambda = () -> "Hello This is Supplier in Lambda";

        // then
        assertThat(mySupplier.get()).isEqualTo("Hello This is Supplier");
        assertThat(lambda.get()).isEqualTo("Hello This is Supplier in Lambda");
    }

    @Test
    @DisplayName("BiFunction을 사용하는 BinaryOperation")
    public void binaryOperation() {
        // given
        final BinaryOperator<String> myBinaryOperator = new BinaryOperator<>() {
            @Override
            public String apply(String s, String s2) {
                return s + "/" + s2;
            }
        };

        final BinaryOperator<String> lambda = (s1, s2) -> s1 + "/" + s2;

        //when
        final String actual = myBinaryOperator.apply("bifunction은 파라미터를", "두개받아요!");

        // then
        assertThat(actual).isEqualTo("bifunction은 파라미터를/두개받아요!");
        assertThat(lambda.apply("이것은", "람다표현식입니다")).isEqualTo("이것은/람다표현식입니다");
    }
}
