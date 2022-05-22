package chapter8.item53;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/05/21
 * description :
 */
public class Item53Test {
    @Test
    @DisplayName("가변인수 테스트")
    public void varargsTest() {
        // when
        final int actual = NumUtils.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // then
        assertThat(actual).isEqualTo(55);
    }

    @Test
    @DisplayName("가변인수 예외처리 테스트:anti")
    public void minTest() {
        // then
        assertThatThrownBy(NumUtils::min)
                .isInstanceOf(IllegalArgumentException.class)
                .as("가변인수 런타임 시점에서 예외를 체크하는 테스트");
    }

    @Test
    @DisplayName("가변인수 예외처리 테스트")
    public void maxTest() {
        // when
        final int actual = NumUtils.max(7);

        // then
        assertThat(actual).isEqualTo(7)
                .as("초기값을 설정하여 가변인자 validate를 생략할 수 있다");
    }
}
