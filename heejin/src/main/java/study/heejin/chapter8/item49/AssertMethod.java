package study.heejin.chapter8.item49;

import java.util.List;

public class AssertMethod {

    // java -ea ./heejin/src/main/java/study/heejin/chapter8/item49/AssertMethod.java
    public static void main(String[] args) {
        List<String> books = List.of("이펙티브 자바", "토비의 스프링", "오브젝트", "알고리즘");
        System.out.println("book : " + getBook(books, 0));

        // 단언 실패
//        getBook(books, -1);
//        getBook(null, 0);
//        getBook(List.of(), 0);
        getBook(books, 5);
    }

    static private String getBook(List<String> books, int index) {
        assert index >= 0 : "index 값이 음수입니다.";
        assert books != null && !books.isEmpty() : "책장 목록이 null 이거나 비어있습니다.";
        assert books.size() >= index : "index가 책장 목록보다 큽니다.";

        return books.get(index);
    }
}
