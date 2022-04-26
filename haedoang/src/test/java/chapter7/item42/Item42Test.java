package chapter7.item42;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/23
 * description :
 */
public class Item42Test {
    protected static ArrayList<MajorCompany> majorCompanies;

    @BeforeEach
    void setUp() {
        final MajorCompany 네이버 = MajorCompany.valueOf(BigInteger.valueOf(5_500), "네이버");
        final MajorCompany 카카오 = MajorCompany.valueOf(BigInteger.valueOf(5_700), "카카오");
        final MajorCompany 라인 = MajorCompany.valueOf(BigInteger.valueOf(6_100), "라인");
        final MajorCompany 쿠팡 = MajorCompany.valueOf(BigInteger.valueOf(5_400), "쿠팡");
        final MajorCompany 배달의민족 = MajorCompany.valueOf(BigInteger.valueOf(6_000), "배달의민족");

        majorCompanies = Lists.newArrayList(네이버, 카카오, 라인, 쿠팡, 배달의민족);
    }

    @Test
    @DisplayName("BiFunction 테스트")
    public void lambdaIsBetter() {
        // given
        final BiFunction<Integer, Integer, Integer> plus = new BiFunction<>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };

        // when
        final Integer actual = plus.apply(5, 3);

        // then
        assertThat(actual).isEqualTo(8)
                .as("인터페이스가 추상 전략 역할을 수행한다");

        // when
        final BiFunction<Integer, Integer, Integer> plusLambda = (a, b) -> a + b;

        // then
        assertThat(plusLambda.apply(5, 3)).isEqualTo(8)
                .as("함수형 인터페이스는 추상 메서드 하나를 실행하는 람다 표현식으로 사용할 수 있다");

        // when
        final BiFunction<Integer, Integer, Integer> methodReference = Integer::sum;

        assertThat(methodReference.apply(5, 3)).isEqualTo(8)
                .as("메서드 참조는 코드를 더 간결하게 해준다");
    }

    @Test
    @DisplayName("익명 클래스 비교자 테스트")
    public void comparingTest() {
        // given
        @SuppressWarnings("unchecked") final List<MajorCompany> majorCompanyList = (List<MajorCompany>) majorCompanies.clone();

        // when
        Collections.sort(majorCompanyList, new Comparator<MajorCompany>() {
            @Override
            public int compare(MajorCompany o1, MajorCompany o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });

        // then
        assertThat(majorCompanyList).extracting(MajorCompany::getName)
                .containsExactly("쿠팡", "네이버", "카카오", "배달의민족", "라인")
                .as("연봉 낮은 순서대로");
    }

    @Test
    @DisplayName("람다 비교자 테스트")
    public void comparingTestByLambda() {
        // given
        @SuppressWarnings("unchecked") final List<MajorCompany> majorCompanyList = (List<MajorCompany>) majorCompanies.clone();

        // when
        Collections.sort(majorCompanyList, (c1, c2) -> c2.getSalary().compareTo(c1.getSalary()));

        // then
        assertThat(majorCompanyList).extracting(MajorCompany::getName)
                .containsExactly("라인", "배달의민족", "카카오", "네이버", "쿠팡")
                .as("연봉 높은 순서대로");
    }

    @Test
    @DisplayName("메서드 참조 비교자 테스트")
    public void comparingTestByMethodReference() {
        // given
        @SuppressWarnings("unchecked") final List<MajorCompany> majorCompanyList = (List<MajorCompany>) majorCompanies.clone();

        // when
        Collections.sort(majorCompanyList, comparing(MajorCompany::getName, reverseOrder()));

        // then
        assertThat(majorCompanyList).extracting(MajorCompany::getName)
                .containsExactly("쿠팡", "카카오", "배달의민족", "라인", "네이버")
                .as("메서드 참조")
                .as("회사명 역순 정렬");
    }
}
