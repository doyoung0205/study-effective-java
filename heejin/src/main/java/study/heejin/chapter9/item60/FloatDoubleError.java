package study.heejin.chapter9.item60;

public class FloatDoubleError {

    public static void main(String[] args) {
        // 1.03 달러 중에서 42센트를 쓰고 남은 돈을 계산해보자.
        System.out.println(1.03 - 0.42);  // 0.61이 나와야 하지만 0.6100000000000001이 나옴

        // 1달러로 10센트짜리 사탕 9개를 사고 남은 돈은 계산해보자.
        System.out.println(1.00 - 9 * 0.10);  // 0.1이 나와야 하지만 0.09999999999999998이 나옴
    }
}
