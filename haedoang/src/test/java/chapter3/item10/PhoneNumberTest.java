package chapter3.item10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/18
 * description :
 */
class PhoneNumberTest {

    @Test
    @DisplayName("equals 규약 검증")
    public void phoneNumberTest() {
        // given
        final PhoneNumber actual = new PhoneNumber(02, 300, 8282);

        // then
        assertThat(actual.equals(new PhoneNumber(02,300,8282))).isTrue();
        assertThat(new PhoneNumber(02,300,8282).equals(actual)).isTrue();
    }

}