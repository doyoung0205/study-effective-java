package me.doyoung.studyeffectivejava.chapter9.item59;

import java.util.Random;

// 무작위 수 생성은 쉽지 않다.
public class RandomBug {
    // 코드 59-1 흔하지만 문제가 심각한 코드! (351쪽)
    static Random rnd = new Random();

    static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }

    // 무작위 수 1백만 개 생성 후, 중간 값보다 작은 수의 개수 출력 (351쪽)
    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 1000000; i++)
            if (random(n) < n/2)
                low++;
        System.out.println(low);
    }
}
