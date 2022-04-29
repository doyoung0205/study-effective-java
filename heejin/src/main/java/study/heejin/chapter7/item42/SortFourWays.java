package study.heejin.chapter7.item42;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class SortFourWays {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("가", "마", "나", "바", "다", "사", "라");
        System.out.println("origin = " + words);

        // 1. 익명 클래스 사용
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Character.compare(o1.charAt(0), o2.charAt(0));
            }
        });
        System.out.println("1. words = " + words);
        shuffle(words);

        // 2. 람다 사용
        Collections.sort(words, (o1, o2) -> Character.compare(o1.charAt(0), o2.charAt(0)));
        System.out.println("2. words = " + words);
        shuffle(words);

        // 3. 람다 + 메서드 참조 사용
        Collections.sort(words, comparingInt(o -> o.charAt(0)));
        System.out.println("3. words = " + words);
        shuffle(words);

        //4.  람다 + 메서드 참조 + List.sort 사용
        words.sort(comparingInt(o -> o.charAt(0)));
        System.out.println("4. words = " + words);
    }

    private static void shuffle(List<String> words) {
        Collections.shuffle(words);
        System.out.println("shuffle = " + words);
    }
}
