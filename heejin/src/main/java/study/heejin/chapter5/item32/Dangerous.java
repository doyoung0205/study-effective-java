package study.heejin.chapter5.item32;

import java.util.List;

public class Dangerous {

    public static void dangerous(List<String> ... stringLists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList;               // 힙 오염 발생
        String s = stringLists[0].get(0);   // ClassCastException
    }

    public static void main(String[] args) {
        dangerous(List.of("There be dragons!"));
    }
}
