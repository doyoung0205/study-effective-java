package chapter6.item36;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static chapter6.item36.SmartText.Style.*;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * author : haedoang
 * date : 2022/04/18
 * description :
 */
public class Item36Test {

    @Test
    @DisplayName("비트 필드 연산 이해하기")
    public void bitFieldTest() {
        // given
        final Text text = new Text();
        final Text text2 = new Text();

        // when
        /**
         *  해석 : 둘중 하나만 1이면 1 나머지 0
         *  1 | 10  => OR 연산 0000 0001
         *                    0000 0010
         *             RESULT 0000 0011 => 3
         */
        text.addStyles(Text.STYLE_BOLD | Text.STYLE_ITALIC);

        /**
         * 해석 : & => 둘다 1 이면 1 나머지 0
         * 1 & 10  => AND 연산 0000 0001
         *                    0000 0010
         *            RESULT  0000 0000 => 0
         *
         */
        text2.addStyles(Text.STYLE_BOLD & Text.STYLE_ITALIC);

        // then
        assertThat(text.getStyle()).isEqualTo(3);
        assertThat(text2.getStyle()).isEqualTo(0);
    }

    @Test
    @DisplayName("bit field 클래스에 비해 크기 제약이 없다")
    public void enumSet() {
        // given
        final SmartText text = new SmartText();

        // when
        text.applyStyles(EnumSet.of(BOLD, ITALIC, UNDERLINE, STRIKETHROUGH));

        // then
        assertThat(text.getStyles()).contains(BOLD, ITALIC, UNDERLINE, STRIKETHROUGH)
                .as("중복 처리를 위해 Set Collection을 사용한다");
    }
}
