package chapter2.item3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/07
 * description :
 */
@DisplayName("제네릭 싱글턴 팩터리 테스트")
public class GenericSingletonTest {
    private List<String> stringList;
    private List<Integer> integerList;
    private List<Boolean> booleanList;

    @BeforeEach
    void setUp() {
        stringList = GenericFactoryMethod.emptyList();
        integerList = GenericFactoryMethod.emptyList();
        booleanList = GenericFactoryMethod.emptyList();
    }

    @Test
    @DisplayName("제네릭 싱글턴 팩터리는 인스턴스가 한개이다.")
    public void singletonTest() {
        // then
        assertThat(stringList.hashCode())
                .isEqualTo(integerList.hashCode())
                .isEqualTo(booleanList.hashCode());
    }
    @Test
    @DisplayName("제네릭 팩터리 메서드는 반환 타입을 다르게 할 수 있다.")
    public void genericFactoryMethodTest() {
        // given
        stringList.add("유연합니다!");
        integerList.add(8282);
        booleanList.add(true);

        // then
        assertThat(stringList.size())
                .isEqualTo(integerList.size())
                .isEqualTo(booleanList.size())
                .isEqualTo(3);

        assertThat(stringList.get(0) instanceof String).isTrue();
        assertThat(stringList.get(1) instanceof String).isFalse();
        assertThat(stringList.get(2) instanceof String).isFalse();
    }
}
