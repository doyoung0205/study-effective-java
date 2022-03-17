package study.heejin.chapter4.item24;

public class Calculator {
    private int origin = 1;
    static int result = 2;

    private static void calc(int origin, int result) {
        System.out.println("계산합니다. origin: " + origin + ", result:" + result);
    }

    // 정적 내부 크래스
    public static class Operation {
        int origin = 3;
        int result = 4;

        public void test1() {
            Calculator.calc(origin, result);
            System.out.println("[정적 내부 클래스 public] origin = " + origin);
            System.out.println("[정적 내부 클래스 public] result = " + result);
            //Calculator calculator = Calculator.this; // 컴파일 오류
            System.out.println("[정적 내부 클래스 public] Calculator.result = " + Calculator.result);
        }

        public static void test2() {
            int origin = 5;
            int result = 6;

            Calculator.calc(origin, result);
            System.out.println("[정적 내부 클래스 public static] origin = " + origin);
            System.out.println("[정적 내부 클래스 public static] result = " + result);
            //Calculator calculator = Calculator.this; // 컴파일 오류
            System.out.println("[정적 내부 클래스 public static] Calculator.result = " + Calculator.result);
        }

        public static int PLUS(int a, int b) {
            return a + b;
        }

        public static int MINUS(int a, int b) {
            return a - b;
        }
    }

    // 비정적 내부 클래스
    public class Operation2 {
        int origin = 3;
        int result = 4;

        public void test1() {
            Calculator.calc(origin, result);
            System.out.println("[비정적 내부 클래스 public] origin = " + origin);
            System.out.println("[비정적 내부 클래스 public] result = " + result);
            System.out.println("[비정적 내부 클래스 public] Calculator.this.origin = " + Calculator.this.origin);
            System.out.println("[비정적 내부 클래스 public] Calculator.result = " + Calculator.result);
        }

        // static 메서드 불가 - public static void test2() {}

        public int PLUS(int a, int b) {
            return a + b;
        }

        public int MINUS(int a, int b) {
            return a - b;
        }
    }
}
