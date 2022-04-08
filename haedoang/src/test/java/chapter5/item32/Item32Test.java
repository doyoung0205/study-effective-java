package chapter5.item32;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/04/08
 * description :
 */
public class Item32Test {

    //bad expression
    static <T> T[] toArray(T... args) {
        return args;
    }

    static String[] toStringArray(String... args) {
        return args;
    }


    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }

        return result;
    }

    static <T> List<T> flattenList(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }

        return result;
    }

    //bad expression
    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return toArray(a, c);
            case 1:
                return toArray(a, b);
            case 2:
                return toArray(b, c);
        }
        throw new AssertionError();
    }

    static <T> List<T> pickTwoList(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return List.of(a, c);
            case 1:
                return List.of(a, b);
            case 2:
                return List.of(b, c);
        }
        throw new ArithmeticException();
    }


    @Test
    @DisplayName("varargs 제네릭 타입은 안전하지 못하다")
    public void varargsNotSafety() {
        // when & then
        assertThatThrownBy(() -> {
            final String[] strings = pickTwo("오늘", "날씨", "어때요?");
        }).isInstanceOf(ClassCastException.class)
                .as("자바는 모든 타입을 받을 수 있는 가변인자 타입을 Object[] 타입으로 매핑하기 떄문에 string[] 타입으로 다시 형변환 시 castException이 발생한다");

        final String[] strings = toStringArray("이건", "될것", "같죠?");
        assertThat(strings).containsExactly("이건", "될것", "같죠?")
                .as("타입을 명시해줘서 컴파일러에게 해당타입을 주는 방법을 사용하면 된다. 그렇지 않은 경우 사용을 주의할 것");
    }

    @Test
    @DisplayName("varargs 타입을 리스트 타입으로 변경하면 classCastException으로부터 안전하다")
    public void varargsNotSafetyButListIsSafe() {
        // when
        final List<String> flatten = flatten(List.of("밥먹기", "양치하기", "잠자기", "공부하기", "책읽기", "운동하기"));


        // then
        assertThat(flatten.size()).isEqualTo(6)
                .as("varargs List 타입으로 주었을 경우 컴파일 시점에 타입이 제거되기 때문에 타입안전하지 못하다 @SafeVarags를 통해 타입 안정성을 직접 보장해주어야 한다");


        // when
        final List<String> members = flattenList(List.of(List.of("아빠", "엄마"), List.of("누나", "나")));

        // then
        assertThat(members).hasSize(4)
                .as("제네릭 타입으로받는 경우 더욱 더 타입을 보장할 수 있다");


        // when
        final List<String> todosTomorrow = pickTwoList("내일", "스케줄은", "뭔가요?");

        // when
        assertThat(todosTomorrow).hasSize(2);
    }
}
