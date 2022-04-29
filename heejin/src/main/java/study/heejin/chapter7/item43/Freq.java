package study.heejin.chapter7.item43;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Freq {
    public static void main(String[] args) {
        List<String> colors = Arrays.asList("yellow", "white", "green", "black", "white", "blue", "yellow");

        Map<String, Integer> frequencyTable = new TreeMap<>();
        for (String color : colors) {
            frequencyTable.merge(color, 1, (num, incr) -> num + incr);
        }

        System.out.println(frequencyTable);
        frequencyTable.clear();

        for (String color : colors) {
            frequencyTable.merge(color, 1, Integer::sum);
        }

        System.out.println(frequencyTable);
    }
}
