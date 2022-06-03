package study.heejin.chapter9.item62;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalKey {
    private static final Map<Key, Object> THREADLOCAL = new HashMap<>();

    private ThreadLocalKey() {  // 객체 생성 불가
    }

    public static void set(Key key, Object value) {
        THREADLOCAL.put(key, value);
    }

    public static Object get(Key key) {
        return THREADLOCAL.get(key);
    }

    public static class Key { // (권한)
        Key () { }

        // 위조 불가능한 고유 키를 생성한다.
        public static Key getKey() {
            return new Key();
        }
    }
}
