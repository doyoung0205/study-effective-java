package me.doyoung.studyeffectivejava.chapter12.item89.enumsingleton;

import java.util.Arrays;

// 코드 89-4 열거 타입 싱글턴(선호 방식) - 전통적인 싱글턴보다 우수하다. (478쪽)
public enum Elvis {
    INSTANCE;
    private String[] favoriteSongs =
        { "Hound Dog", "Heartbreak Hotel" };
    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }
}
