package chapter10.item69;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class NumberUtils {
    /**
     * 1부터 숫자를 배열의 크기만큼 값을 채웁니다
     *
     * @param arr
     */
    public static void pushNumber(int[] arr) {
        try {
            int i = 0;
            while (true) {
                arr[i++] = i;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public static void smartPushNumber(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            arr[j] = j + 1;
        }
    }
}
