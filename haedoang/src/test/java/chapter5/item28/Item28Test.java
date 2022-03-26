package chapter5.item28;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName : chapter5.item28
 * fileName : Item28Test
 * author : haedoang
 * date : 2022-03-23
 * description :
 */
public class Item28Test {
    @Test
    @DisplayName("타입이 다른 오류를 array는 런타임에서  list는 컴파일 시점에서 알 수 있다")
    public void arrayCompareToList() {
        Object[] objectArray = new Long[1]; // 배열은 공변이기때문에 가능함
//        List<Object> objectList = new ArrayList<Long>();
//        objectList.add("컴파일 시점에서 오류를 파악할 수 있습니다");

        assertThat(objectArray.getClass()).isEqualTo(Long[].class);
        assertThatThrownBy(() -> {
            objectArray[0] = "하이하이^^";
        })
                .isInstanceOf(ArrayStoreException.class)
                .as("Long[]으로 초기화되었기 때문에 arrayStoreException이 발생한다");
    }

    @Test
    @DisplayName("힙 오염을 발생하는 예제")
    public void varargsWithGeneric() {
        assertThatThrownBy(() -> {
            String[] arr = CollectionUtil.broken("두아리파");
        }).isInstanceOf(ClassCastException.class)
                .as("String[] 대신 Object[]로 힙 오염이 발생한다");
    }

    @Test
    @DisplayName("@SafeVarargs 사용하는 예제")
    public void useSafeVarargs() {
        // given
        Machine<String> machine = new Machine<>();

        // when
        machine.safe("스트링", "타입만", "넣을 수", "있기에", "안전하다");

        // then
        assertThat(machine.hasSize()).isEqualTo(5);
    }

    @Test
    @DisplayName("List 제네릭으로 타입 안정성을 보장할 수 있다")
    public void chooserTest() {
        // given
        Chooser<String> stringChooser = new Chooser<>(Arrays.asList("에라토스테네스", "체"));

        // when
        Object selected = stringChooser.choose();

        // then
        assertThat(selected).isEqualTo("에라토스테네스");
    }
}
