package chapter3.item10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/18
 * description :
 */
@DisplayName("상속을 사용한 객체 테스트")
public class ColorTest {

    @Test
    @DisplayName("instance 가 다른 경우 false를 반환하며, 대칭성을 위배한다")
    public void equalsTest() {
        // given
        final ColorPoint colorPoint = new ColorPoint(1, 2, Color.BLUE);
        final Point point = new Point(1, 2);

        // then
        assertThat(colorPoint.equals(point)).isTrue();
        assertThat(point.equals(colorPoint)).isTrue();
    }

    @Test
    @DisplayName("instance 가 다르기 추이성도 위배된다")
    public void colorTest() {
        // given
        final ColorPoint caseA = new ColorPoint(1, 2, Color.BLUE);
        final Point caseB = new Point(1, 2);
        final ColorPoint caseC = new ColorPoint(1, 2, Color.RED);
        // when

        // then
        assertThat(caseA).isEqualTo(caseB);
        assertThat(caseB).isEqualTo(caseC);
        assertThat(caseA).isNotEqualTo(caseC);
    }

    @Test
    @DisplayName("상속의 문제를 해결하기 위해 parent 객체만의 비교는 리스코프 치환 원칙을 위배한다.")
    public void lsp() {
        // given
        ColorPoint colorPoint = new ColorPoint(3, 5, Color.RED);
        Point point = new Point(3, 5);

        // then
        assertThat(point.equals(colorPoint)).isTrue();
        assertThat(colorPoint.equalsGetClass(point)).isFalse();
    }
}
