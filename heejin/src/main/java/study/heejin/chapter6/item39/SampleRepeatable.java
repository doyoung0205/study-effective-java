package study.heejin.chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class SampleRepeatable {

    @RepeatableExcptionTest(IndexOutOfBoundsException.class)
    @RepeatableExcptionTest(NullPointerException.class)
    public static void doubleBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
}
