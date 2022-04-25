package me.doyoung.studyeffectivejava.chapter7.item42;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparingInt;

// 함수 객체로 정렬하기 (254-255쪽)
public class SortFourWays {
    public static void main(String[] args) {
        {
            List<String> words = Arrays.asList(args);

            // 코드 42-1 익명 클래스의 인스턴스를 함수 객체로 사용 - 낡은 기법이다! (254쪽)
            Collections.sort(words, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return Integer.compare(s1.length(), s2.length());
                }
            });
            System.out.println(words);
            Collections.shuffle(words);

            // 코드 42-2 람다식을 함수 객체로 사용 - 익명 클래스 대체 (255쪽)
            Collections.sort(words,
                    (s1, s2) -> Integer.compare(s1.length(), s2.length()));
            System.out.println(words);
            Collections.shuffle(words);

            // 람다 자리에 비교자 생성 메서드(메서드 참조와 함께)를 사용 (255쪽)
            Collections.sort(words, comparingInt(String::length));
            System.out.println(words);
            Collections.shuffle(words);

            // 비교자 생성 메서드와 List.sort를 사용 (255쪽)
            words.sort(comparingInt(String::length));
            System.out.println(words);
        }
    }
}
