package chapter2.item3;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.apache.commons.lang3.SerializationUtils.deserialize;
import static org.apache.commons.lang3.SerializationUtils.serialize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : chapter2.item3
 * fileName : RomanNumeralUtilTest
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
@DisplayName("정적 팩토리를 사용한 싱글톤")
class RomanNumeralUtilTest {
    @Test
    @DisplayName("public static final 필드 방식의 싱글턴")
    public void staticFinalSingleton() {
        // given
        final RomanNumeralUtil instance = RomanNumeralUtil.getInstance();

        // when
        final String actual = instance.toRomanNumeral(4);

        // then
        assertThat(actual).isEqualTo("IV");
        assertThat(instance).isSameAs(NumberUtil.INSTANCE);
    }

    @Test
    @DisplayName("private 생성자는 리플렉션 사용 시 싱글턴을 보장받을 수 없다.")
    public void singletonFailReflection()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // given
        final RomanNumeralUtil instance = RomanNumeralUtil.getInstance();
        final Class<?> romanNumberUtil = Class.forName("chapter2.item3.RomanNumeralUtil");

        // when
        final Constructor<?> declaredConstructor = romanNumberUtil.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        // then
        assertThat(instance).isNotSameAs(declaredConstructor.newInstance())
                .as("리플렉션으로 생성자의 접근제어자를 변경할 시 싱글톤을 보장받기 어렵다");
    }

    @Test
    @DisplayName("readResolve 메서드로 역직렬화 시 싱글턴을 보장할 수 있다.")
    public void failSingletonWhenDeserializedObject() {
        // given
        RomanNumeralUtil instance = RomanNumeralUtil.getInstance();
        byte[] serialized = serialize(instance);

        // when
        final RomanNumeralUtil actual = SerializationUtils.deserialize(serialized);

        // then
        assertThat(instance).isSameAs(actual);
    }
}