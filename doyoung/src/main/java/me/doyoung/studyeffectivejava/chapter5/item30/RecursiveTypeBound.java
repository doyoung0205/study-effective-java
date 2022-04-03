package me.doyoung.studyeffectivejava.chapter5.item30;

import me.doyoung.studyeffectivejava.chapter3.item19.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

// 재귀적 타입 한정을 이용해 상호 비교할 수 있음을 표현 (179쪽)
public class RecursiveTypeBound {
    // 코드 30-7 컬렉션에서 최댓값을 반환한다. - 재귀적 타입 한정 사용 (179쪽)
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("컬렉션이 비어 있습니다.");

        E result = null;
        for (E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        return result;
    }

    public static void main(String[] args) {
//        List<String> argList = Arrays.asList(args);
//        System.out.println(max(argList));

        final List<Parent> persons =
                Arrays.asList(new Parent(1), new Parent(2));
//        final Doyoung max = Collections.max(persons);



    }

    private static class Parent implements Comparable {
        private int id;

        public Parent(int id) {
            this.id = id;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    private static class Doyoung implements Comparable<Person> {


        @Override
        public int compareTo(Person o) {
            return 0;
        }
    }
}
