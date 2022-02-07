package chapter2.item3;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item3
 * fileName : NumberUtilTest
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
@DisplayName("정적 필드를 사용한 싱글톤 테스트")
class NumberUtilTest {
    @Test
    @DisplayName("public static final 필드 방식의 싱글턴")
    public void staticFinalSingleton() {
        // given
        final NumberUtil instance = NumberUtil.INSTANCE;

        // when
        final int actual = instance.sum(3, 5);

        // then
        assertThat(actual).isEqualTo(8);
        assertThat(instance).isSameAs(NumberUtil.INSTANCE);
    }

    @Test
    @DisplayName("private 생성자는 리플렉션 사용 시 싱글턴을 보장받을 수 없다.")
    public void singletonFailReflection()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // given
        final NumberUtil instance = NumberUtil.INSTANCE;
        final Class<?> numberUtil = Class.forName("chapter2.item3.NumberUtil");

        // when
        final Constructor<?> declaredConstructor = numberUtil.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        // then
        assertThat(instance).isNotSameAs(declaredConstructor.newInstance())
                .as("리플렉션으로 생성자의 접근제어자를 변경할 시 싱글톤을 보장받기 어렵다");
    }

    @Test
    @DisplayName("싱글턴 클래스는 역직렬화 시 싱글턴을 보장받지 못한다.")
    public void failSingletonWhenDeserializedObject() {
        // given
        NumberUtil instance = NumberUtil.INSTANCE;
        byte[] serialized = serialize(instance);

        // when
        final NumberUtil actual = SerializationUtils.deserialize(serialized);

        // then
        assertThat(instance).isNotSameAs(actual);
    }
}