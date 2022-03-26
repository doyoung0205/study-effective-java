package study.heejin.chapter5.item26;

import java.util.ArrayList;
import java.util.List;

public class RawType {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));

        // 컴파일 되지 않는다.
        // unsafeAdd2(strings, Integer.valueOf(42));

        // 컴파일러가 자동으로 형변환 코드를 넣어준다.
        String s = strings.get(0);
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }

    private static void unsafeAdd2(List<Object> list, Object o) {
        list.add(o);
    }
}
