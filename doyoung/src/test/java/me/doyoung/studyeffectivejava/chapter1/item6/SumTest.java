package me.doyoung.studyeffectivejava.chapter1.item6;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class SumTest {

    @Test
    @DisplayName("오토박싱으로 인해 불필요한 객체 생성이 이루어져 성능이 좋지 않은 예시")
    void sumWithAutoBoxing() {
        long x = 0;
        long start = System.nanoTime();
        x += Sum.sumWithAutoBoxing();
        long end = System.nanoTime();
        long result= end - start;
        log.info("sumWithPrimitive 실행 시간 :: {}ms", result);
    }

    @Test
    @DisplayName("불필요한 객체 생성없이 성이 좋은 예시")
    void sumWithPrimitive() {
        long x = 0;
        long start = System.currentTimeMillis();
        x += Sum.sumWithPrimitive();
        long end = System.currentTimeMillis();
        long result= end - start;
        log.info("sumWithPrimitive 실행 시간 :: {}ms", result);
    }
}
