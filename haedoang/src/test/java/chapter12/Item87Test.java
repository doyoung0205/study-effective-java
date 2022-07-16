package chapter12;

import chapter12.item87.Name;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/07/16
 * description :
 */
public class Item87Test {
    @Test
    @DisplayName("직렬화 객체 테스트하기")
    public void doSerialize() {
        // given
        final Name name = Name.valueOf("김", "해동", null);

        // when
        final byte[] serialized
                = SerializationUtils.serialize(name);

        Name deserialized = SerializationUtils.deserialize(serialized);

        // then
        assertThat(name).isNotSameAs(deserialized);
        assertThat(name).isEqualTo(deserialized);
    }
}
