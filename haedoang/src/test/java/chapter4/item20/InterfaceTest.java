package chapter4.item20;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/03/07
 * description :
 */
public class InterfaceTest {

    @Test
    @DisplayName("default Method를 활용하면 메서드 구현 방법을 줄일 수 있다")
    public void useDefaultMethod() {
        // given
        MyCircle allowCircle = new MyCircle(Circle.allowsColors.get(0));
        MyCircle notAllowCircle = new MyCircle("PURPLE");

        // then
        assertThat(allowCircle.isValid()).isTrue();
        assertThat(notAllowCircle.isValid()).isFalse();
    }

    @Test
    @DisplayName("abstractList 사용한 구체클래스 테스트")
    public void arrayToListTest() {
        // given
        final List<Integer> numbers = ListUtil.intArrayAsList(new int[]{1, 7, 4, 3, 5, 9, 2, 8, 6, 10});

        // then
        assertThat(numbers.get(0)).isEqualTo(1);

        // when
        final Integer oldValue = numbers.set(0, 0);
        assertThat(numbers.get(0)).isEqualTo(0);
        assertThat(oldValue).isEqualTo(1)
                .as("");

    }

    @Test
    @DisplayName("골격구현 클래스를 이용한 Queue 구현 테스트")
    public void customQueueTest() {
        // given
        final MyQueue<String> todos = new MyQueue<>();
        todos.add("빨래 돌리기");
        todos.add("청소기 돌리기");
        todos.add("운동 가기");

        // when
        final String firstJob = todos.poll();
        final String secondJob = todos.poll();

        // then
        assertThat(firstJob).isEqualTo("빨래 돌리기");
        assertThat(secondJob).isEqualTo("청소기 돌리기");
        assertThat(todos.peek()).isEqualTo("운동 가기");
        assertThat(todos.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("단순구현 테스트")
    public void simpleEntryTest() {
        // given
        final AbstractMap.SimpleEntry<String, String> myEntry
                = new AbstractMap.SimpleEntry<>("name", "haedoang");

        // then
        assertThat(myEntry.getKey()).isEqualTo("name");
        assertThat(myEntry.getValue()).isEqualTo("haedoang")
                .as("추상 클래스가 아니기 때문에 직접 객체화할 수 있다");
    }
}


