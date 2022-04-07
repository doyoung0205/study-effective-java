package study.heejin.chapter5.item30;

import java.util.List;

public class Swap {

    /**
     * 와일드카드 타입을 실제 타입으로 바꿔주는 private 도우미 메서드
     */
    public static <E> void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    // 컴파일 에러 발생. List<?> 에는 null 이외에 어떤 값도 넣을 수 없음.
    private static <E> void swapHelperErr(List<?> list, int i, int j) {
        // list.set(i, list.set(j, list.get(i)));
    }

    public static void main(String[] args) {

    }
}
