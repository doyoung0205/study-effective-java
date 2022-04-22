package chapter6.item39;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class Item39Test {

    @Test
    @DisplayName("marker annotation 테스트")
    public void markerAnnotation() throws ClassNotFoundException {
        // given
        final Class<?> personTypeToken = Class.forName("chapter6.item39.Person");

        // when
        final boolean actual = personTypeToken.isAnnotationPresent(Entity.class);

        // then
        assertThat(actual).isTrue();
    }
}
