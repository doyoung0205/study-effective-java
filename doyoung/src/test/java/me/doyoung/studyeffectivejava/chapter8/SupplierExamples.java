package me.doyoung.studyeffectivejava.chapter8;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplierExamples {

    public static void main(String[] args) {

        // Java8에 Supplier을 활용하여 lazy evaluation을 사용하는 사례

        // 시나리오
        // 컴퓨팅 자원이 많이 필요한 작업이 있다.
        // 이 작업은 한번 실행될때마다 3초가 걸린다.
        // num >= 0보다 클때만 이 작업이 실행되어야 한다.

        long start = System.currentTimeMillis();

        printIfValidIndex(0, getVeryExpensiveValue());
        printIfValidIndex(-1, getVeryExpensiveValue()); // invalid
        printIfValidIndex(-2, getVeryExpensiveValue()); // invalid

        // 총 9초가 걸린다.

        // num이 -1, -2일때는 getVeryExpensiveValue()을 실행할 필요가 없는데 실행이 되어서 총 9초가 소요된다.
        // 이렇게 되면 6초의 낭비가 발생한다.
        System.out.println("It took " + (System.currentTimeMillis() - start) / 1000 + " seconds");

        long startSupplier = System.currentTimeMillis();
        printIfValidIndex(0, () -> getVeryExpensiveValue());
        printIfValidIndex(-1, () -> getVeryExpensiveValue());
        printIfValidIndex(-2, () -> getVeryExpensiveValue());

        // Supplier를 활용하여 lazy evaluation이 되어서
        // 총 3초가 걸린다.
        System.out.println("It took " + (System.currentTimeMillis() - startSupplier) / 1000 + " seconds");
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Let's just say it has very expensive calculation here!
        return "Leo";
    }

    private static void printIfValidIndex(int num, String value) {
        if (num >= 0) {
            System.out.println("The value is " + value + ".");
        } else {
            System.out.println("invalid");
        }
    }

    private static void printIfValidIndex(int num, Supplier<String> valueSupplier) {
        if (num >= 0) {
            System.out.println("The value is " + valueSupplier.get() + ".");
        } else {
            System.out.println("Invalid");
        }
    }
}
