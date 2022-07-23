package chapter12;

import chapter12.item90.Period;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/07/23
 * description :
 */
public class item90Test {

    @Test
    @DisplayName("serializationProxy 테스트")
    public void serializationProxy() {
        // given
        final Period period = new Period(new Date(), new Date());

        // when
        final byte[] bytes = SerializationUtils.serialize(period);
        final Period actual = SerializationUtils.deserialize(bytes);

        // then
        assertThat(period.toString()).isEqualTo(actual.toString());
    }
}


