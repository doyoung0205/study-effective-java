package chapter2.item7;

import chapter2.item7.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2
 * fileName : StackTest
 * author : haedoang
 * date : 2022-02-14
 * description :
 */
class StackTest {

    @Test
    @DisplayName("스택 기능 구현 테스트")
    public void stack() {
        // given
        Stack stack = new Stack();

        // when
        IntStream.range(0, 20)
                .forEach(stack::push);

        // then
        assertThat(stack.elementsLength()).isEqualTo(33);

        // when
        IntStream.range(0, 5).forEach(i -> stack.pop());

        // then
        assertThat(stack.nonNullElementsSize()).isEqualTo(15)
                .as("stack은 비활성 영역에 대한 부분을 가비지 컬렉터가 처리하기 위해서 null 처리를 해주어야 한다");
    }
}

