package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

class Item3Test {

    @Test
    @DisplayName("공급자 테스트")
    void supplier() {
        // Supplier<T> : 매개변수를 받지 않고 단순히 무엇인가를 반환하는 추상메서드
        Supplier<String> message = () -> "Hello ";

        System.out.println(message.get() + "World");
    }

    @Test
    @DisplayName("불필요한 연산을 수행하는 테스트")
    void lazySupplier() {
        long start = System.currentTimeMillis();

        printIfValidIndex(0, getVeryExpensiveValue());
        printIfValidIndex(-1, getVeryExpensiveValue());
        printIfValidIndex(-2, getVeryExpensiveValue());

        printTime(start);
    }

    @Test
    @DisplayName("불필요한 연산을 수행하지 않는 테스트")
    void lazyEvaluationSupplier() {
        long start = System.currentTimeMillis();

        printIfValidIndex(0, () -> getVeryExpensiveValue());
        printIfValidIndex(-1, () -> getVeryExpensiveValue());
        printIfValidIndex(-2, () -> getVeryExpensiveValue());

        printTime(start);
    }

    private String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Expensive Value";
    }

    private void printIfValidIndex(int index, String value) {
        if (index >= 0) {
            System.out.println("The value is " + value);
        }
        System.out.println("Invalid");
    }

    private void printIfValidIndex(int index, Supplier<String> value) {
        if (index >= 0) {
            System.out.println("The value is " + value.get());
        }
        System.out.println("Invalid");
    }

    private void printTime(long start) {
        System.out.println("It took " + (System.currentTimeMillis() - start) / 1000 + " Second");
    }
}
