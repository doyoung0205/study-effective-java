package me.doyoung.studyeffectivejava.chapter1.item1;

public class LottoTest {
    public static void main(String[] args) {
        Lotto.getInstance(12); // 객체 생성
        Lotto.getInstance(12); // 이전 생성 객체 반환
        Lotto.getInstance(12); // 이전 생성 객체 반환
    }
}
