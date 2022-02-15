package chapter2.item7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.WeakHashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item7
 * fileName : WeakHashMapTest
 * author : haedoang
 * date : 2022-02-14
 * description :
 */
public class WeakHashMapTest {

    @Test
    @DisplayName("WeakHashMap의 객체 참조 제거는 가비지 콜렉터의 수집 대상이 된다.")
    public void weahHashMapTest() throws Throwable {
        // given
        Person person = new Person("haedoang", 34);

        WeakHashMap<Person, Integer> weakHashMap = new WeakHashMap();
        weakHashMap.put(person, 1);

        // when
        person = null;
        System.gc();

        Thread.sleep(5000);

        // then
        assertThat(weakHashMap.keySet().size()).isEqualTo(0)
                .as("가비지 콜렉터를 직접적으로 테스트하기는 쉽지 않다")
                .as("즉시 수거하지 않음을 대비하여 5초간 sleep을 주었다");
    }
}
