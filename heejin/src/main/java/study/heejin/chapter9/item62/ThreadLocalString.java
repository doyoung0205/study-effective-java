package study.heejin.chapter9.item62;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalString {
    private static final Map<String, Object> THREADLOCAL = new HashMap<>();

    private ThreadLocalString() {  // 객체 생성 불가
    }

    public static void set(String key, Object value) {
        THREADLOCAL.put(key, value);
    }

    public static Object get(String key) {
        return THREADLOCAL.get(key);
    }
}
