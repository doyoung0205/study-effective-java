package me.doyoung.studyeffectivejava.chapter5.item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 와일드카드 타입을 실제 타입으로 바꿔주는 private 도우미 메서드 (189쪽)
public class Swap {


    // 와일드카드 타입을 실제 타입으로 바꿔주는 private 도우미 메서드
    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public static void main(String[] args) {
        // 첫 번째와 마지막 인수를 스왑한 후 결과 리스트를 출력한다.
        List<String> argList = Arrays.asList(args);

        List<List<String>> listList = new ArrayList<>();

        swap(listList, 0, argList.size() - 1);

        System.out.println(argList);
    }

    public static void swap(List<?> list, int i, int j) {
        // E VS ?
        // E: 타입이 정해졌음
        // ?: 타입이 아직 정해지지 않았음 (read only)
        // 둘다 한가지 타입이 오는데..


//        list.set(j, list.get(i));

//        list.set(i, list.set(j, list.get(i)));

        swapHelper(list, i, j);
    }

    public static void test(List<?> list) {

    }
}
