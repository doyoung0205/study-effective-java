package chapter5.item31;

import chapter5.item30.CollectionUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/01
 * description :
 */
public class Item31Test {
    @Test
    @DisplayName("제네릭은 어떻게 보면 유연하지 못해 보일 수 있다")
    public void badPushAll() {
        // given
        final Stack<Number> stack = new Stack<>();

        // when
        Iterable<Integer> ints = Lists.newArrayList(1, 2, 3, 4);
        Iterable<Number> numbers = Lists.newArrayList(1, 2, 3, 4);

        //stack.pushAll(ints); compile err
        stack.pushAll(numbers);

        // then
        assertThat(stack.size()).isEqualTo(4)
                .as("와일드카드 타입을 사용하지 않았기 때문에 하위 타입도 다른 타입으로 구분되었다");
    }

    @Test
    @DisplayName("한정적 와일드 카드 사용으로 하위타입도 컴파일 시점에서 동작할 수 있게 한다")
    public void goodPushAll() {
        // given
        final Stack<Number> stack = new Stack<>();
        Iterable<Integer> ints = Lists.newArrayList(1, 2, 3, 4);
        Iterable<Number> numbers = Lists.newArrayList(1, 2, 3, 4);

        // when
        stack.smartPushAll(ints);
        stack.smartPushAll(numbers);

        // then
        assertThat(stack.size()).isEqualTo(8);
    }

    @Test
    @DisplayName("제네릭 타입으로 구현된 타입만 허용한다")
    public void badPopAll() {
        // given
        final Stack<Integer> stack = new Stack<>();
        Iterable<Integer> ints = Lists.newArrayList(1, 2, 3, 4);
        stack.smartPushAll(ints);

        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        // when
        stack.popAll(integerList);
//        stack.popAll(numberList);//compile err

        // then
        assertThat(integerList).hasSize(4);
    }

    @Test
    @DisplayName("한정적 와일드카드를 사용한 popAll")
    public void goodPopAll() {
        // given
        final Stack<Integer> stack = new Stack<>();
        Iterable<Integer> ints = Lists.newArrayList(1, 2, 3, 4);
        stack.smartPushAll(ints);

        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        // when
        stack.smartPopAll(integerList);
        stack.smartPopAll(numberList);

        // then
        assertThat(integerList).hasSize(4);
        assertThat(numberList).hasSize(4);
    }

    @Test
    @DisplayName("producer 는 제공만하기 때문에 확장하는 와일드카드를 사용한다 <? extends T>")
    public void advanceUnion() {
        // given
        List<Integer> ints = Lists.newArrayList(1, 2, 3, 4);
        List<Number> numbers = Lists.newArrayList(1, 2, 3, 4);

        // when
        //final List<Number> number = CollectionUtil.smartUnion(ints, numbers);//compile err

        final List<Number> actual = CollectionUtil.verySmartUnion(ints, numbers);

        // then
        assertThat(actual).hasSize(8);
    }

    @Test
    @DisplayName("")
    public void smartMax() throws ExecutionException, InterruptedException {
        // given
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        List<ScheduledFuture<String>> scheduledFutures = Lists.newArrayList(
                service.schedule(
                        () -> "자바는", 1, TimeUnit.SECONDS),

                service.schedule(
                        () -> "정말로", 7, TimeUnit.SECONDS),
                service.schedule(
                        () -> "어려워!!", 3, TimeUnit.SECONDS)
        );

        // when
//        CollectionUtil.max(scheduledFutures); //compile err
        final ScheduledFuture<String> scheduledFuture = CollectionUtil.smartMax(scheduledFutures);

        // then
        assertThat(scheduledFuture.get()).isEqualTo("정말로")
                .as("comparable을 ScheduledFuture가 구현하지 않고 Delayed 인터페이스에 있기 때문에 기존의 max()로는 동작하지 않는다 ");
    }

    @Test
    @DisplayName("")
    public void swapHelper() {
        // given
        final ArrayList<String> character = Lists.newArrayList("A", "B", "C", "D");

        // when
        CollectionUtil.swap(character, 0, 3);

        // then
        assertThat(character).containsExactly("D", "B", "C", "A");

        // when
        CollectionUtil.swap(character, 1, 2);

        // then
        assertThat(character).containsExactly("D", "C", "B", "A");
    }
}
