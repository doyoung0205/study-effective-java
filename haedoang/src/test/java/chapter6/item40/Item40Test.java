package chapter6.item40;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class Item40Test {

    @Test
    @DisplayName("equals 재정의 잘못한 경우 테스트")
    public void equalsNGTest() {
        // given
        final Wine 복서_2019 = Wine.valueOf("복서_2019", BigDecimal.valueOf(80_000));

        // then
        assertThat(복서_2019).isNotEqualTo(Wine.valueOf("복서_2019", BigDecimal.valueOf(80_000)));
    }

    @Test
    @DisplayName("equals 재정의 시 Object 타입의 메서드를 재정의해야 한다")
    public void equalsOKTest() {
        // given
        final Sports 펜싱 = Sports.valueOf("펜싱");

        // then
        assertThat(펜싱).isEqualTo(Sports.valueOf("펜싱"));
    }
}
