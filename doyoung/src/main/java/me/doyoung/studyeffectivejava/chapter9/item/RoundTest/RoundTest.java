package me.doyoung.studyeffectivejava.chapter9.item.RoundTest;

public class RoundTest {
    public static void main(String[] args) {
        double r = 0.1 + 1.9;
        int result = (int) r;
        System.out.println("result = " + result);


        Long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += 1;
        }
        System.out.println(sum);
    }
}
