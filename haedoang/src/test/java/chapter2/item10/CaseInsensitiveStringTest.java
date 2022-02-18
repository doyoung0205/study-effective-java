package chapter2.item10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/18
 * description :
 */
class CaseInsensitiveStringTest {

    @Test
    @DisplayName("대칭성이 위배된다")
    public void equals() {
        // given
        String name = "Jax Jones";

        // when
        final CaseInsensitiveString actual = new CaseInsensitiveString(name);

        // then
        assertThat(actual.equals("jax jones")).isTrue();
        assertThat("jax jones".equals(actual)).isFalse();
    }
}