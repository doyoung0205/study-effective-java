package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item4.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Item4Test {

    @Test
    @DisplayName("private 생성자 인스턴스화 불가 테스트")
    void privateConstraint() throws Exception {
        Constructor<UtilityClass> constructor = UtilityClass.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        assertThatThrownBy(constructor::newInstance)
                .isInstanceOf(InvocationTargetException.class)
                .getCause()
                .isInstanceOf(AssertionError.class)
                .withFailMessage("유틸리티 클래스이므로 인스턴스화 할 수 없습니다.");
    }
}
