package chapter2.item3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item3
 * fileName : StringUtilTest
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
@DisplayName("열거 타입의 싱글턴 테스트")
class StringUtilTest {
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