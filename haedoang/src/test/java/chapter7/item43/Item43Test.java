package chapter7.item43;

import chapter7.item42.Item42Test;
import chapter7.item42.MajorCompany;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author : haedoang
 * date : 2022/04/23
 * description :
 */
public class Item43Test extends Item42Test {

    @Test
    @DisplayName("map merge를 사용한 장바구니 테스트")
    public void mapMergeMethodTest() {
        // given
        final Product 바나나 = Product.valueOf(5_000, "바나나");
        final Product 상추 = Product.valueOf(2_000, "상추");

        final HashMap<Product, Integer> cart = new HashMap<>();

        // when
        cart.merge(바나나, 1, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        });
        cart.merge(상추, 1, (a, b) -> a + b);
        cart.merge(바나나, 2, Integer::sum);

        // then
        assertThat(cart.get(바나나)).isEqualTo(3)
                .as("merge는 해당 키가 존재하는 경우에 대한 처리를 할 수 있다");
    }

    @Test
    @DisplayName("정적 메서드 참조 테스트")
    public void staticMethodReference() {
        // given
        final ArrayList<String> lottoStr = Lists.newArrayList("1", "7", "4", "24", "32", "45");

        // when
        final List<Integer> lottoNums = lottoStr.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // then
        assertThat(lottoNums).contains(1, 7, 4, 24, 32, 45);
    }

    @Test
    @DisplayName("한정적 메서드 참조[objectRef::Instance Method]")
    public void boundMethodReference() {
        // given
        UnaryOperator<String> toUpper = String::toUpperCase;
        final ArrayList<String> lowers = Lists.newArrayList("a", "b", "c");

        final List<String> uppers = lowers.stream()
                .map(toUpper::apply)
                .collect(Collectors.toList());
        // when
        uppers.forEach(toUpper::apply);


        // then
        assertThat(uppers).contains("A", "B", "C");
    }

    @Test
    @DisplayName("비한정적 메서드 참조[ClassName::Instance Method]")
    public void unboundMethodReference() {
        // given
        @SuppressWarnings("unchecked") final List<MajorCompany> majorCompanyList = (List<MajorCompany>) majorCompanies.clone();

        // when
        Collections.sort(majorCompanyList, comparing(MajorCompany::getName));

        // then
        assertThat(majorCompanyList).extracting(MajorCompany::getName)
                .containsExactly("쿠팡", "카카오", "배달의민족", "라인", "네이버");
    }

    @Test
    @DisplayName("클래스 생성자 메서드 참조")
    public void classConstructorMethodReference() {
        // given
        final List<Item> items = Arrays.stream(new String[]{"A", "B", "C"})
                .map(Item::new)
                .collect(Collectors.toList());

        // then
        assertThat(items).hasSize(3);
    }

    @Test
    @DisplayName("배열 생성자 메서드 참조")
    public void arrayConstructorReference() {
        // given
        final String[] strings = Lists.newArrayList("A", "B", "C")
                .toArray(String[]::new);

        // then
        assertAll(
                () -> assertThat(strings[0]).isEqualTo("A"),
                () -> assertThat(strings[1]).isEqualTo("B"),
                () -> assertThat(strings[2]).isEqualTo("C")
        );
    }
}
