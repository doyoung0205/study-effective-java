package chapter4.item15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.module.ModuleDescriptor;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/03/01
 * description :
 * <a href="https://www.baeldung.com/java-9-module-api">참고 링크</a>
 */
public class ModuleAPITest {

    @Test
    @DisplayName("named 모듈")
    public void namedModule() {
        // given
        Module javaBaseModule = HashMap.class.getModule(); // java --list-modules

        // then
        assertThat(javaBaseModule.isNamed()).isTrue();
        assertThat(javaBaseModule.getName()).isEqualTo("java.base");
    }

    @Test
    @DisplayName("이름이 지정되지 않은 모듈")
    public void unnamedModule() {
        // given
        Module module = Person.class.getModule();

        // then
        assertThat(module.isNamed()).isFalse();
        assertThat(module.getName()).isNull();
    }

    @Test
    @DisplayName("모듈 내에서 사용 가능한 패키지 확인하기")
    public void baseModuleAccessPackages() {
        // then
        Module javaBaseModule = HashMap.class.getModule();
        assertThat(javaBaseModule.getPackages()).contains("java.lang.annotation");
        assertThat(javaBaseModule.getPackages()).doesNotContain("java.sql");
    }
}


class Person {
    String name;
}