package chapter2.item4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/02/07
 * description :
 */
@DisplayName("private 생성자 테스트")
public class CommonUtilTest {
    @Test
    @DisplayName("인스턴스화를 방지하기 위해 private constructor를 사용한다.")
    public void privateConstructor() {
        // given
        final Class<CommonUtil> commonUtil = CommonUtil.class;
        // when
        final Constructor<?>[] constructors = commonUtil.getDeclaredConstructors();

        // then
        assertThat(constructors.length).isEqualTo(1);

        // when
        final Constructor<?> privateConstructor = constructors[0];
        privateConstructor.setAccessible(true);

        // then
        assertThatThrownBy(() -> privateConstructor.newInstance())
                .isInstanceOfAny(InvocationTargetException.class, AssertionError.class)
                .as("reflection 에서 메소드 내에서 예외가 발생했을 경우 InvocationTargetException으로 Wrapping 됩니다.");
    }
}
