package me.doyoung.studyeffectivejava.chapter6.item34;

import static me.doyoung.studyeffectivejava.chapter6.item34.PayrollDay.PayType.WEEKDAY;
import static me.doyoung.studyeffectivejava.chapter6.item34.PayrollDay.PayType.WEEKEND;

// 코드 34-9 전략 열거 타입 패턴 (218-219쪽)
enum PayrollDay {
    MONDAY(WEEKDAY), TUESDAY(WEEKDAY), WEDNESDAY(WEEKDAY),
    THURSDAY(WEEKDAY), FRIDAY(WEEKDAY),
    SATURDAY(WEEKEND), SUNDAY(WEEKEND);
    // (역자 노트) 원서 1~3쇄와 한국어판 1쇄에는 위의 3줄이 아래처럼 인쇄돼 있습니다.
    // 
    // MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    // SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
    //
    // 저자가 코드를 간결하게 하기 위해 매개변수 없는 기본 생성자를 추가했기 때문인데,
    // 열거 타입에 새로운 값을 추가할 때마다 적절한 전략 열거 타입을 선택하도록 프로그래머에게 강제하겠다는
    // 이 패턴의 의도를 잘못 전달할 수 있어서 원서 4쇄부터 코드를 수정할 계획입니다.

    private final PayType payType;

    PayrollDay(PayType payType) { this.payType = payType; }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    // 전략 열거 타입
    enum PayType {
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 :
                        (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);
        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }

    public static void main(String[] args) {
        for (PayrollDay day : values())
            System.out.printf("%-10s%d%n", day, day.pay(8 * 60, 1));
    }
}
