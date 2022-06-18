package chapter10.item71;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class Item71Test {
    @Test
    @DisplayName("필요 없는 검사 예외는 불편한 API를 만든다")
    public void checkedExceptionNG() {
        // then
        assertThatThrownBy(() -> User.valueOf("", 34))
                .isInstanceOf(AssertionError.class)
                .as("불필요한 검사 예외는 지양해야하며 비검사 예외를 사용하는 것이 좋다");
    }

    @Test
    @DisplayName("비검사 예외를 사용하자")
    public void uncheckedException() {
        // then
        assertThatThrownBy(() -> Team.valueOf(""))
                .isInstanceOfAny(MyRuntimeException.class, RuntimeException.class);
    }
}
