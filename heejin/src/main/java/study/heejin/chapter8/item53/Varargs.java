package study.heejin.chapter8.item53;

import java.util.Arrays;

public class Varargs {

    /***
     * 간단한 가변인수 활용 예
     */
    public static int sum(int...args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    /**
     * 가변인수를 사용하여 인수를 0개만 넣어 호출하면, 컴파일타임이 아닌 런타임에 실패한다.
     */
    public static int min(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
        }
        int min = args[0];
        for (int i = 0; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }

        return min;
    }

    /**
     * 매개변수를 2개 받도록하면, 가변인수를 사용하더라도 위의 문제를 피할 수 있다.
     */
    public static int min2(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs) {
            if (arg < min) {
                min = arg;
            }
        }
        return min;
    }
}
