package study.heejin.chapter8.item52;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SetList {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        List<Object> list = new ArrayList<>();

        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }

        for (int i = 0; i < 3; i++) {
            set.remove(i);
//            list.remove(i); // E remove(int index);
            list.remove((Integer) i); // int indexOf(Object o);
            System.out.println(set + " " + list);
        }
    }
}
