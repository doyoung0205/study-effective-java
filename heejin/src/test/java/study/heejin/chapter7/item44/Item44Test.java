package study.heejin.chapter7.item44;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToLongFunction;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class Item44Test {

    @Test
    @DisplayName("Function 인터페이스의 변형 - 반환 타입 매개변수화")
    void longFunction() {
        LongFunction<int[]> function = new LongFunction<int[]>() {
            @Override
            public int[] apply(long value) {
                String binary = Long.toBinaryString(value);
                int[] arr = new int[binary.length()];

                for (int i = 0; i < arr.length; i++) {
                    arr[i] = (binary.charAt(i) == '1') ? 1 : 0;
                }
                return arr;
            }
        };

        int[] binary = function.apply(10L);
        assertThat(binary).containsExactly(1, 0, 1, 0);
    }

    @Test
    @DisplayName("Function 인터페이스의 변형 - 입력(매개변수)을 매개변수화")
    void toLongFunction() {
        ToLongFunction<int[]> function = new ToLongFunction<int[]>() {
            @Override
            public long applyAsLong(int[] value) {
                int sum = Arrays.stream(value).sum();
                return (long) sum;
            }
        };

        int[] arr = new int[]{1, 2, 3, 4, 5};
        long result = function.applyAsLong(arr);

        assertThat(result).isEqualTo(15L);
    }

    @Test
    @DisplayName("Function 인터페이스의 변형")
    void longToIntFunction() {
        LongToIntFunction function = value -> Long.valueOf(value).intValue();

        int result = function.applyAsInt(10L);
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("ExecutorSevice의 sumit() - Callback<T>, Runnable 다중정의")
    void sumit() {
        ExecutorService exec = Executors.newFixedThreadPool(5);

        // Callable<T> or Runnable ?
        exec.submit(() -> System.out.println("Task execution"));
        exec.submit(() -> "ok");

        // Callable<T>
        exec.submit((Callable<?>) () -> {
            System.out.println("Callback Task execution");
            return "ok";
        });

        // Runnable
        exec.submit((Runnable) () -> System.out.println("Runnable Task execution"));
    }
}
