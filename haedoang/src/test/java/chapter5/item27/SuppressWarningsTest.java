package chapter5.item27;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * author : haedoang
 * date : 2022/03/19
 * description :
 */
public class SuppressWarningsTest {

    @Test
    @DisplayName("")
    public void asd() {
        // given
        String[] strArr = {"a","b","c"};

        // when
        final String[] actual = ArrayUtil.arrayCopy(strArr);

        // then
    }

}
