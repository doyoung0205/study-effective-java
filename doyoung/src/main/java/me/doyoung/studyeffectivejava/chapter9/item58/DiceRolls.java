package me.doyoung.studyeffectivejava.chapter9.item58;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

// 코드 58-5 같은 버그, 다른 증상! (349쪽)
public class DiceRolls {
    enum Face { ONE, TWO, THREE, FOUR, FIVE, SIX }

    public static void main(String[] args) {
        // 같은 버그, 다른 증상!
        Collection<Face> faces = EnumSet.allOf(Face.class);

        for (Iterator<Face> i = faces.iterator(); i.hasNext(); )
            for (Iterator<Face> j = faces.iterator(); j.hasNext(); )
                System.out.println(i.next() + " " + j.next());

        System.out.println("***************************");

        // 컬렉션이나 배열의 중첩 반복을 위한 권장 관용구
        for (Face f1 : faces)
            for (Face f2 : faces)
                System.out.println(f1 + " " + f2);
    }
}
