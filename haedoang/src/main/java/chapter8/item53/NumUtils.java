package chapter8.item53;

/**
 * author : haedoang
 * date : 2022/05/21
 * description :
 */
public class NumUtils {
    public static int sum(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }

        return sum;
    }

    public static int min(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
        }
        int min = args[0];

        for (int i = 1; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }

        return min;
    }

    public static int max(int firstArgs, int... remainingArgs) {
        int max = firstArgs;
        for (int arg : remainingArgs) {
            if (arg > max) {
                max = arg;
            }
        }

        return max;
    }
}
