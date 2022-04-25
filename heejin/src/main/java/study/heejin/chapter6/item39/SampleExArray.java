package study.heejin.chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class SampleExArray {

    @ExceptionsTest({IndexOutOfBoundsException.class, NullPointerException.class})
    public static void doubleBad() { // 성공
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
}
