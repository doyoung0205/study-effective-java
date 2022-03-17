package study.heejin.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter4.item24.Calculator;
import study.heejin.chapter4.item24.LocalClass;

import static org.assertj.core.api.Assertions.assertThat;

class Item24Test {

    @Test
    @DisplayName("정적 멤버 클래스")
    void nestedClass_static() {
        Calculator.Operation operation = new Calculator.Operation();
        operation.test1();

        Calculator.Operation.test2();

        int plus = Calculator.Operation.PLUS(5, 2);
        int minus = Calculator.Operation.MINUS(5, 2);

        assertThat(plus).isEqualTo(7);
        assertThat(minus).isEqualTo(3);
    }

    @Test
    @DisplayName("비정적 멤버 클래스")
    void nestedClass_none_static() {
        Calculator calculator = new Calculator();
        Calculator.Operation2 operation2 = calculator.new Operation2();

        operation2.test1();

        int plus = operation2.PLUS(5, 2);
        int minus = operation2.MINUS(5, 2);

        assertThat(plus).isEqualTo(7);
        assertThat(minus).isEqualTo(3);
    }
    
    @Test
    @DisplayName("익명 클래스")
    void anonymousClass() {
        new LocalClass() {
            public void anonymous() {
                System.out.println("익명클래스 입니다.");
            }
        }.anonymous();
    }

    @Test
    @DisplayName("내부 클래스")
    void localClass() {
        LocalClass localClass = new LocalClass();
        localClass.greeting();
    }
}