package me.doyoung.studyeffectivejava.chapter6.item38;

import java.util.Arrays;
import java.util.Collection;

// 코드 38-2 확장 가능 열거 타입 (233-235쪽)
public enum ExtendedOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };
    private final String symbol;
    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }
    @Override public String toString() {
        return symbol;
    }

//    // 열거 타입의 Class 객체를 이용해 확장된 열거 타입의 모든 원소를 사용하는 예 (234쪽)
//    public static void main(String[] args) {
//        double x = Double.parseDouble(args[0]);
//        double y = Double.parseDouble(args[1]);
//        test(ExtendedOperation.class, x, y);
//    }
//    private static <T extends Enum<T> & Operation> void test(
//            Class<T> opEnumType, double x, double y) {
//        for (Operation op : opEnumType.getEnumConstants())
//            System.out.printf("%f %s %f = %f%n",
//                    x, op, y, op.apply(x, y));
//    }

    // 컬렉션 인스턴스를 이용해 확장된 열거 타입의 모든 원소를 사용하는 예 (235쪽)
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }
    private static void test(Collection<? extends Operation> opSet,
                             double x, double y) {
        for (Operation op : opSet)
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }
}
