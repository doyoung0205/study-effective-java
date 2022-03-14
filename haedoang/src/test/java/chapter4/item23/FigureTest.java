package chapter4.item23;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter4.item23
 * fileName : FigureTest
 * author : haedoang
 * date : 2022-03-14
 * description :
 */
class FigureTest {

    @Test
    @DisplayName("태그 달린 클래스는 객체 자체로 구분하기 어렵다")
    public void tagClass() {
        // given
        Figure circle = new Figure(5);
        Figure rectangle = new Figure(5, 5);

        // then
        assertThat(circle.area()).isEqualTo(Math.PI * 5 * 5);
        assertThat(circle.width).isEqualTo(0.0);
        assertThat(circle.length).isEqualTo(0.0);
        assertThat(rectangle.area()).isEqualTo(5.0 * 5.0);
        assertThat(rectangle.radius).isEqualTo(0.0);
    }

    @Test
    @DisplayName("계층 구조 클래스")
    public void hierarchyClass() {
        // given
        FigureNew circle = new Circle(5);
        FigureNew rectangle = new Rectangle(5, 5);
        FigureNew square = new Square(5);

        // then
        assertThat(circle.area()).isEqualTo(Math.PI * 5 * 5);
        assertThat(rectangle.area()).isEqualTo(5.0 * 5.0);
        assertThat(square.area()).isEqualTo(5.0 * 5.0);
    }

    @Test
    @DisplayName("계층구조 클래스 시 리스코프 치환 법칙을 위배하는 경우 테스트")
    public void squareAndRectangle() {
        // given
        final Rectangle rectangle = new Rectangle(5, 5);
        final Square square = new Square(5);

        // then
        assertThat(rectangle.area()).isEqualTo(square.area());

        // when
        rectangle.setLength(10);
        square.setLength(10);

        // then
        assertThat(rectangle.area()).isNotEqualTo(square.area())
                .as("정사각형은 너비가 늘어났을 때 높이도 늘어나게 됨으로 상위타입의 정확성을 깨뜨린다");
    }
}
