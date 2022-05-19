package study.heejin.chapter8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter8.item50.MutablePeriod;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class Item50Test {

    @Test
    @DisplayName("가변 필드로 Period 내부 값을 변경")
    void mutablePeriod() {
        Date start = new Date();
        Date end = new Date();
        int year = end.getYear();

        MutablePeriod period = new MutablePeriod(start, end);
        assertThat(period.end().getYear()).isEqualTo(year);

        end.setYear(78);
        assertThat(period.end().getYear()).isEqualTo(78);
    }

    @Test
    @DisplayName("getter 메서드의 가변 필드로 접근하여 Period 내부 값을 변경")
    void mutablePeriod2() {
        Date start = new Date();
        Date end = new Date();
        int year = end.getYear();

        MutablePeriod period = new MutablePeriod(start, end);
        assertThat(period.end().getYear()).isEqualTo(year);

        period.end().setYear(78);
        assertThat(period.end().getYear()).isEqualTo(78);
    }
}
