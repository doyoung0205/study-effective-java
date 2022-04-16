package chapter6.item35;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/16
 * description :
 */
public class item35Test {
    @Test
    @DisplayName("ordinal을 사용하는 테스트")
    public void ordinalTest() {
        // given
        final Characters actual = Characters.찡찡이;

        // then
        assertThat(actual.numberOfCharacters()).isEqualTo(12)
                .as("ordinal()은 순서가 변경되었을 때 원치 않은 결과를 반환할 수 있다");
    }

    @Test
    @DisplayName("ordinal 대신 필드를 사용하면 변경에 안전하다")
    public void useFieldOrder() {
        // given
        final SmartCharacters actual = SmartCharacters.요롱이;

        // then
        assertThat(actual.getZi()).isEqualTo("사");
        assertThat(actual.getOrder()).isEqualTo(6);
    }
}
