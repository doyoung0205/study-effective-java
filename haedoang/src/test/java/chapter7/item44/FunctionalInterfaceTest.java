package chapter7.item44;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/05/02
 * description :
 */
public class FunctionalInterfaceTest {
    @Test
    @DisplayName("UnaryOperator 테스트")
    public void UnaryOperatorTest() {
        // given
        final UnaryOperator<String> unaryOperator = new UnaryOperator<>() {
            @Override
            public String apply(String o) {
                return o.toUpperCase();
            }
        };

        // when
        final String actual = unaryOperator.apply("nike");

        // then
        assertThat(actual).isEqualTo("NIKE")
                .as("피연산자와 동일한 형식의 결과를 생성하는 단일 피연산자에 대한 연산을 나타냅니다.")
                .as("이것은 피연산자와 결과가 같은 유형인 경우에 대한 Function의 특수화입니다.");

        // when
        final UnaryOperator<String> lambda = s -> s.toUpperCase();

        // then
        assertThat(lambda.apply("nike")).isEqualTo("NIKE");

        // when
        UnaryOperator<String> methodRef = String::toUpperCase;

        // then
        assertThat(methodRef.apply("nike")).isEqualTo("NIKE");
    }

    @Test
    @DisplayName("BinaryOperator 테스트")
    public void BinaryOperatorTest() {
        // given
        final BinaryOperator<String> binaryOperator = new BinaryOperator<>() {
            @Override
            public String apply(String o1, String o2) {
                return o1 + o2;
            }
        };

        // when
        final String actual = binaryOperator.apply("안녕", "하세요");

        // then
        assertThat(actual).isEqualTo("안녕하세요")
                .as("동일한 유형의 두 피연산자에 대한 연산을 나타내며 피연산자와 동일한 유형의 결과를 생성합니다.")
                .as("이것은 피연산자와 결과가 모두 같은 유형인 경우에 대한 BiFunction의 전문화입니다.");

        // when
        final BinaryOperator<String> lambda = (s1, s2) -> s1 + s2;

        // then
        assertThat(lambda.apply("안녕", "하세요")).isEqualTo("안녕하세요");

        // when
        BinaryOperator<String> methodRef = String::concat;

        // then
        assertThat(methodRef.apply("안녕", "하세요")).isEqualTo("안녕하세요");
    }

    @Test
    @DisplayName("Predicate 테스트")
    public void PredicateTest() {
        // given
        final Predicate<Integer> predicate = new Predicate<>() {
            @Override
            public boolean test(Integer o) {
                return o % 2 == 0;
            }
        };

        // when
        final boolean actual = predicate.test(5);

        // then
        assertThat(actual).isFalse()
                .as("한 인수의 술어(부울 값 함수)를 나타냅니다.")
                .as("기능 메서드가 test(Object)인 기능 인터페이스입니다.");

        // when
        final Predicate<Integer> lambda = (o) -> o % 2 == 0;

        // then
        assertThat(lambda.test(5)).isFalse();
    }

    @Test
    @DisplayName("Function 테스트")
    public void FunctionTest() {
        // given
        final Function<Enum, String> function = new Function<>() {
            @Override
            public String apply(Enum e) {
                return e.name();
            }
        };

        // when
        final String actual = function.apply(Animal.DOG);

        // then
        assertThat(actual).isEqualTo("DOG")
                .as("하나의 인수를 받아 결과를 생성하는 함수를 나타냅니다.")
                .as("기능적 메소드가 apply(Object)인 기능적 인터페이스입니다.");

        // when
        final Function<Enum, String> lambda = (s1) -> s1.name();

        // then
        assertThat(lambda.apply(Animal.DOG)).isEqualTo("DOG");

        // when
        Function<Enum, String> methodRef = Enum::name;

        assertThat(methodRef.apply(Animal.HORSE)).isEqualTo("HORSE");
    }

    @Test
    @DisplayName("Supplier 테스트")
    public void SupplierTest() {
        // given
        final Supplier<String> supplier = new Supplier<>() {
            @Override
            public String get() {
                return "은총알은 없다";
            }
        };

        // when
        final String actual = supplier.get();

        // then
        assertThat(actual).isEqualTo("은총알은 없다")
                .as("결과 공급자를 나타냅니다.")
                .as("공급자가 호출될 때마다 새 결과나 고유한 결과가 반환되어야 한다는 요구 사항은 없습니다.");

        // when
        final Supplier<String> lambda = () -> "행복은 습관이다,그것을 몸에 지니라";

        // then
        assertThat(lambda.get()).isEqualTo("행복은 습관이다,그것을 몸에 지니라");
    }

    @Test
    @DisplayName("consumer 테스트")
    public void ConsumerTest() {
        // given
        final Consumer<String> consumer = new Consumer<>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        // then
        consumer.accept("고개 숙이지 마십시오. 세상을 똑바로 정면으로 바라보십시오");

        // when
        Consumer<String> lambda = (String s) -> System.out.println(s);

        // then
        lambda.accept("일하여 얻으라 . 그러면 운명의 바퀴를 붙들어 잡은것이다 ");

        // when
        Consumer<String> methodRef = System.out::println;

        // then
        methodRef.accept("길을 잃는 다는 것은 곧 길을 알게 된다는 것이다.");

        //단일 입력 인수를 허용하고 결과를 반환하지 않는 작업을 나타냅니다. 대부분의 다른 기능적 인터페이스와 달리 소비자는 부작용을 통해 작동할 것으로 예상됩니다
    }

}
