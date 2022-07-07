package study.heejin.chapter11.item81;

import java.util.concurrent.ConcurrentHashMap;

public class Intern {

    private static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public static String intern(String s) {
        String previousValue = map.putIfAbsent(s, s);
        return previousValue == null ? s : previousValue;
    }

    /**
     * ConcurrentMap으로 구현한 동시성 정규화 맵 - 더 빠르다!
     * @param s
     * @return
     */
    public static String internRefactor(String s) {
        String result = map.get(s);

        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null) {
                result = s;
            }
        }
        return result;
    }

}
