package chapter8.item50;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter8.item50
 * fileName : Item50Test
 * author : haedoang
 * date : 2022-05-18
 * description :
 */
@SuppressWarnings("deprecation")
public class Item50Test {

    @Test
    @DisplayName("기간 테스트")
    public void period() {
        // given
        Date start = new Date(121, 01, 01);
        Date end = new Date();
        Period period = Period.valueOf(start, end);

        // then
        assertThat(period.getStart().before(period.getEnd())).isTrue();

        // when
        start.setYear(123);

        // then
        assertThat(period.getStart().before(period.getEnd())).isFalse()
                .as("불변 객체를 지키지 못했다");
    }

    @Test
    @DisplayName("방어적 복사본을 사용한 기간 테스트(생성자)")
    public void periodWithDefensiveCopy() {
        // given
        Date start = new Date(121, 01, 01);
        Date end = new Date();
        Period period = Period.defensiveCopyValueOf(start, end);

        // then
        assertThat(period.getStart().before(period.getEnd())).isTrue();

        // when
        start.setYear(123);

        // then
        assertThat(period.getStart().before(period.getEnd())).isTrue()
                .as("TOCTOU(time-of-check/time-of-use) 공격을 피할 수 있다");

        // when
        period.getStart().setYear(123);

        // then
        assertThat(period.getStart().before(period.getEnd())).isFalse()
                .as("객체 내부의 값을 변경하는 경우 불변성을 지키지 못한다");
    }

    @Test
    @DisplayName("방어적 복사본을 사용한 기간 테스트2(생성자, 접근자)")
    public void periodWithDefensiveCopyWithGetter() {
        // given
        Date start = new Date(121, 01, 01);
        Date end = new Date();
        Period period = Period.defensiveCopyValueOf(start, end);

        // then
        assertThat(period.getStart().before(period.getEnd())).isTrue();

        // when
        start.setYear(123);

        // then
        assertThat(period.getStart().before(period.getEnd())).isTrue();

        // when
        period.start().setYear(123);

        // then
        assertThat(period.getStart().before(period.getEnd())).isTrue();
    }


    @Test
    @DisplayName("LocalDateTime을 사용한 불변 객체 테스트")
    public void unmodifiedPeriodTest() {
        // given
        LocalDateTime start = LocalDateTime.now().minusYears(1);
        LocalDateTime end = LocalDateTime.now();

        AdvancePeriod period = AdvancePeriod.valueOf(start, end);

        // then
        assertThat(period.getStart().isBefore(period.getEnd())).isTrue();

        // when
        start.plusYears(3);

        // then
        assertThat(period.getStart().isBefore(period.getEnd())).isTrue();

        // when
        period.getStart().plusYears(3);

        // then
        assertThat(period.getStart().isBefore(period.getEnd())).isTrue();
    }
}
