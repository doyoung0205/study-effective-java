package me.doyoung.studyeffectivejava.chapter3.item17;

public final class DoyoungNumber {

    private final int value;

    // 캐시
    private int previousA;
    private DoyoungNumber cacheResult;

    private DoyoungNumber(int value) {
        this.value = value;
    }

    public static DoyoungNumber valueOf(int value) {
        return new DoyoungNumber(value);
    }

    public DoyoungNumber add(int a) {
        if (a == previousA) {
            return cacheResult;
        }

        // 엄청 빡센 연산
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        previousA = a;
        cacheResult = new DoyoungNumber(this.value + a);

        return cacheResult;
    }
}
