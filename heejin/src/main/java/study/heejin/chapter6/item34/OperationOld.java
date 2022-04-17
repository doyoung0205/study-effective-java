package study.heejin.chapter6.item34;

public enum OperationOld {
//    PLUS, MINUS, TIMES, DIVIDE;

    /**
     * 깨지기 쉬운 코드
     */
//    public double apply(double x, double y) {
//        switch (this) {
//            case PLUS: return x + y;
//            case MINUS: return x - y;
//            case TIMES: return x * y;
//            case DIVIDE: return x / y;
//        }
//        throw new AssertionError("알 수 없는 연산:" + this);
//    }

    PLUS{public double apply(double x, double y) {return x + y;}},
    MINUS{public double apply(double x, double y) {return x - y;}},
    TIMES{public double apply(double x, double y) {return x * y;}},
    DIVIDE{public double apply(double x, double y) {return x / y;}};

    public abstract double apply(double x, double y);
}
