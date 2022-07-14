package chapter12;

import chapter12.item85.SerializationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * author : haedoang
 * date : 2022/07/14
 * description :
 */
public class Item85Test {

    @Disabled
    @Test
    @DisplayName("역직렬화 폭탄 예제")
    public void deserializeBombTest() {
        // given
        final byte[] bomb =
                SerializationUtils.bomb();

        // when
        org.springframework.util.SerializationUtils.deserialize(bomb);

        // then
        throw new AssertionError("끝나지 않는다.");
    }


}
