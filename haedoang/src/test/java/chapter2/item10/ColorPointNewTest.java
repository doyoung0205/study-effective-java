package chapter2.item10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * author : haedoang
 * date : 2022/02/18
 * description :
 */
@DisplayName("컴포지션을 사용한 객체 테스트")
class ColorPointNewTest {

    @Test
    @DisplayName("컴포지션을 사용하여 동일성을 검증할 수 있다")
    public void compositionEqual() {
        // given
        final ColorPointNew colorPointNew = new ColorPointNew(1, 2, Color.BLACK);
        final Point point = new Point(1, 2);

        // then
        assertThat(colorPointNew.asPoint().equals(point)).isTrue();
        assertThat(point.equals(colorPointNew.asPoint())).isTrue();
    }

}