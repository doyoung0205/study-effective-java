package me.doyoung.studyeffectivejava.chapter7.item47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 리스트의 모든 부분리스트를 원소를 갖는 스트림을 생성하는 두 가지 방법 (288-289쪽)
public class SubLists {
    // 코드 47-6 입력 리스트의 모든 부분리스트를 스트림으로 반환한다. (288-289쪽)
    public static <E> Stream<List<E>> of(List<E> list) {

        return Stream.concat(Stream.of(Collections.emptyList()),
//                prefixes(list).flatMap(SubLists::suffixes))
//                prefixes(list).map(SubLists::suffixes))
                suffixes(list).flatMap(SubLists::prefixes))
                ;
    }

    private static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size())
                .mapToObj(end -> {
                    final List<E> subList = new ArrayList<>(list).subList(0, end);
//                    System.out.println("prefixes :: = " + subList);
                    return subList;
                });
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start -> {
                    final List<E> subList = list.subList(start, list.size());
//                    System.out.println("suffixes :: = " + subList);
                    return subList;
                });
    }

//    // 코드 47-7 입력 리스트의 모든 부분리스트를 스트림으로 반환한다(빈 리스트는 제외). (289쪽)
//    // 289쪽의 명확한 반복 코드의 변형이다.
//    public static <E> Stream<List<E>> of(List<E> list) {
//        return IntStream.range(0, list.size())
//                .mapToObj(start ->
//                        IntStream.rangeClosed(start + 1, list.size())
//                                .mapToObj(end -> list.subList(start, end)))
//                .flatMap(x -> x);
//    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        SubLists.of(list).forEach(System.out::println);
    }
}
