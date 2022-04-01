package chapter5.item30;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/01
 * description :
 */
public class Item30Test {
    @Test
    @DisplayName("로 타입은 타입 세이프티하지 못함")
    public void rawTypeUnion() {
        // given
        final ArrayList<String> nuts = Lists.newArrayList("호두", "땅콩", "마카다미아");
        final ArrayList<Integer> lottoNumbers = Lists.newArrayList(1, 6, 9, 11, 34, 42);

        // when
        final List actual = CollectionUtil.union(nuts, lottoNumbers);

        // then
        assertThat(actual.get(0) instanceof String).isTrue();
        assertThat(actual.get(3) instanceof Integer).isTrue();
    }

    @Test
    @DisplayName("제네릭을 사용해서, 아규먼트와 반환값의 타입을 지정할 수 있다")
    public void genericUnionMethod() {
        // given
        final ArrayList<String> nuts = Lists.newArrayList("호두", "땅콩", "마카다미아");
        final ArrayList<Integer> lottoNumbers = Lists.newArrayList(1, 6, 9, 11, 34, 42);
        final ArrayList<String> fruits = Lists.newArrayList("딸기", "수박", "참외");

        // when
//        CollectionUtil.smartUnion(nuts, lottoNumbers); //compile err
        final List<String> foods = CollectionUtil.smartUnion(nuts, fruits);

        // then
        assertThat(foods).hasSize(6);
    }

    @Test
    @DisplayName("제네릭 싱글턴 팩터리 테스트(Collection.reverseOrder())")
    public void useGenericSingletonFactory() {
        // given
        final ArrayList<String> fruits = Lists.newArrayList("딸기", "포도", "바나나", "키위");

        // when
        fruits.sort(String::compareTo);

        // then
        assertThat(fruits).containsExactly("딸기", "바나나", "키위", "포도");

        // when
        fruits.sort(Collections.reverseOrder());

        // then
        assertThat(fruits).containsExactly("포도", "키위", "바나나", "딸기")
                .as("제네릭 싱글턴 팩터리를 사용하여 형 변환없이 사용이 가능하다");
    }

    @Test
    @DisplayName("제네릭 싱글턴 팩터리 테스트(UnaryOperator) - Function.apply()")
    public void useGenericSingletonFactory2() {
        // given
        String[] songs = {"야망", "GONE", "OUT OF MY SIGHT"};
        Number[] numbers = {1, 2.0, 3L};

        final UnaryOperator<String> stringOperator = CollectionUtil.identityFunction();
        final UnaryOperator<Integer> integerOperator = CollectionUtil.identityFunction();

        // when
        Arrays.stream(songs).map(stringOperator::apply)
                .forEach(System.out::println);
        //Arrays.stream(numbers).forEach(integerOperator::apply); compile err
    }

    @Test
    @DisplayName("제네릭을 이용한 재귀적 타입 한정 사용")
    public void max() {
        // given
        final ArrayList<String> nuts = Lists.newArrayList("호두", "땅콩", "마카다미아");
        final ArrayList<Integer> lottoNumbers = Lists.newArrayList(1, 6, 9, 11, 34, 42);

        // when
        final String actual = CollectionUtil.max(nuts);
        final Integer actual2 = CollectionUtil.max(lottoNumbers);

        // then
        assertThat(actual).isEqualTo("호두");
        assertThat(actual2).isEqualTo(42);
    }
}
