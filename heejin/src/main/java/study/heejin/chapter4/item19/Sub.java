package study.heejin.chapter4.item19;

import java.time.Instant;

public final class Sub extends Super {
    private final Instant instant;

    public Sub() {
        this.instant = Instant.now();
    }

    @Override
    public void overrideMe() {
        System.out.println(instant);
    }
}
