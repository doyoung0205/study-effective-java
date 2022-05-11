package me.doyoung.studyeffectivejava.chapter7.item48;

import org.junit.jupiter.api.Test;

import static me.doyoung.studyeffectivejava.utils.SpeedCheckUtils.speedCheck;

class ParallelPrimeCountingTest {

    @Test
    void piWithParallel() {
        speedCheck("piWithParallel", () -> ParallelPrimeCounting.piWithParallel(10_000_000));

    }

    @Test
    void pi() {
        speedCheck("pi", () -> ParallelPrimeCounting.pi(10_000_000));
    }
}
