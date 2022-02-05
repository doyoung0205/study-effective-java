package chapter2.item3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item3
 * fileName : SingletonTest
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
@DisplayName("싱글턴 테스트")
public class SingletonTest {
    @Test
    @DisplayName("public static final 필드 방식의 싱글턴")
    public void staticFinalSingleton() {
        // given
        final NumberUtil instance = NumberUtil.INSTANCE;

        // when
        final int actual = instance.sum(3, 5);

        // then
        assertThat(actual).isEqualTo(8);
        assertThat(instance).isSameAs(NumberUtil.INSTANCE);
    }

    @Test
    @DisplayName("정적 팩터리 방식의 싱글턴")
    public void staticFactorySingleton() {
        // given
        final RomanNumeralUtil instance = RomanNumeralUtil.getInstance();

        // when
        final String actual = instance.toRomanNumeral(4);

        // then
        assertThat(actual).isEqualTo("IV");
    }

    @Test
    @DisplayName("열거 타입의 싱글턴")
    public void enumTypeSingleton() {
        // given
        final StringUtil instance = StringUtil.INSTANCE;

        // when
        final String actual = instance.trim("   haedongkim");

        // then
        assertThat(actual).isEqualTo("haedongkim");
        assertThat(instance).isSameAs(StringUtil.INSTANCE);
    }
}
