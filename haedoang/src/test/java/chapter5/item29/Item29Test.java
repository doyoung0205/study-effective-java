package chapter5.item29;

import chapter2.item7.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName : chapter5.item29
 * fileName : Item29Test
 * author : haedoang
 * date : 2022-03-24
 * description :
 */
public class Item29Test {

    @Test
    @DisplayName("Object[] 스택은 객체 반환 시 타입 변환에 주의하여야 한다")
    public void test() {
        // given
        Stack stack = new Stack();

        stack.push("안녕");
        stack.push(false);
        stack.push(BigDecimal.valueOf(5_000));

        // then
        assertThatThrownBy(() -> {
            String pop1 = (String) stack.pop();
            String pop2 = (String) stack.pop();
        }).isInstanceOf(ClassCastException.class);
    }


    @Test
    @DisplayName("제네릭 타입을 변환 예제 1")
    public void genericStack1() {
        // given
        chapter5.item29.Stack<String> stack = new chapter5.item29.Stack<>();

        // when
        stack.push("안녕");
        stack.push("이제");
        stack.push("안전해");

        // then
        assertThat(
                IntStream.range(0, (int) stack.nonNullElementsSize())
                        .mapToObj(i -> stack.pop())
                        .collect(Collectors.toList())).contains("안녕", "이제", "안전해");
    }

    @Test
    @DisplayName("제네릭 타입을 변환 예제 2")
    public void genericStack2() {
        // given
        chapter5.item29.OtherStack<String> stack = new chapter5.item29.OtherStack<>();

        // when
        stack.push("나도");
        stack.push("이제");
        stack.push("안전해");

        // then
        assertThat(
                IntStream.range(0, (int) stack.nonNullElementsSize())
                        .mapToObj(i -> stack.pop())
                        .collect(Collectors.toList())).contains("나도", "이제", "안전해");
    }
}
