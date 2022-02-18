package study.heejin.chapter3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter3.item10.CaseInsensitiveString;
import study.heejin.chapter3.item10.ColorPoint;
import study.heejin.chapter3.item10.ColorPointComposition;
import study.heejin.chapter3.item10.Point;

import java.awt.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Item10Test {

    @Test
    @DisplayName("equals 대칭성 위배")
    void symmetry() {
        // given
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "Polish";

        // when // then
        assertTrue(cis.equals(s));
        assertFalse(s.equals(cis));
    }

    @Test
    @DisplayName("equals 추이성 위배")
    void transitivity() {
        // given
        Point p1 = new Point(1, 2);
        ColorPoint p2 = new ColorPoint(1, 2, Color.ORANGE);
        ColorPoint p3 = new ColorPoint(1, 2, Color.GREEN);

        // when // then
        assertTrue(p1.equals(p2));
        assertTrue(p1.equals(p3));
        assertFalse(p2.equals(p3));
    }

    @Test
    @DisplayName("equals - 컴포지션 사용")
    void transitivity2() {
        // given
        Point p1 = new Point(1, 2);
        ColorPoint p2 = new ColorPoint(1, 2, Color.ORANGE);
        ColorPointComposition p3 = new ColorPointComposition(1, 2, Color.ORANGE);

        // when // then
        assertTrue(p1.equals(p2));
        assertTrue(p1.equals(p3.asPoint()));
        assertTrue(p2.equals(p3.asPoint()));
    }

    @Test
    @DisplayName("null 아님 테스트")
    void equalsNullFalse() {

        assertFalse(null instanceof String);

    }
}
