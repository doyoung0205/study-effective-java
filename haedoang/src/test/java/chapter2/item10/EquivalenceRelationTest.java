package chapter2.item10;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/18
 * description :
 */
public class EquivalenceRelationTest {

    @Test
    @DisplayName("반사성(reflexivity): null이 아닌 모든 참조값 x에 대해 x.equals(x)는 true 이다.")
    public void reflexivity() {
        // given
        String name = "haedong";
        Integer age = 34;
        Boolean isMarried = false;

        // then
        assertThat(name.equals("haedong")).isTrue();
        assertThat(age.equals(34)).isTrue();
        assertThat(isMarried.equals(false)).isTrue();
    }

    @Test
    @DisplayName("대칭성(symmetry) : null이 아닌 모든 참조값 x,y에 대해 x.equals(y)가 true이면 y.equals(x)도 true이다.")
    public void symmetry() {
        // given
        String name = "haedoang";

        boolean actual = name.equals("haedoang");
        boolean actual2 = "haedoang".equals(name);

        // then
        assertThat(Lists.newArrayList(actual, actual2)).containsOnly(true);
    }


    @Test
    @DisplayName("추이성(transitivity) : null 이 아닌 모든 참조값 x,y,z 에 대해 x.equals(y)가 true 이고 y.equals(z)가 true 이면 x.equals(z)도 true 이다")
    public void transitivity() {
        // given
        String firstFavoriteFood = new String("tomato");
        String secondFavoriteFood = new String("tomato");
        String thirdFavoriteFood = new String("tomato");

        // when
        boolean conditionA = firstFavoriteFood.equals(secondFavoriteFood);
        boolean conditionB = secondFavoriteFood.equals(thirdFavoriteFood);

        // then
        assertThat(Lists.newArrayList(conditionA, conditionB)).containsOnly(true);
        assertThat(firstFavoriteFood.equals(thirdFavoriteFood)).isTrue();
    }

    @RepeatedTest(100)
    @DisplayName("일관성(consistency) : null이 아닌 모든 참조 값 x,y에 대해, x.equals(y)를 반복해서 호출하면 항상 true 를 반환하거나 항상 false 를 반환한다. ")
    public void consistency() {
        // given
        String name = "haedoang";

        // when
        boolean actual = name.equals("haedoang");

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("null-아님 : null이 아닌 모든 참조값 x에 대해 x.equals(null)은 false 이다")
    public void notNull() {
        // given
        String pet = "Jerry";

        // then
        assertThat(pet.equals(null)).isFalse();
    }


    @Test
    @DisplayName("대소문자를 구분하지 않는 대칭성 위배 테스트(단방향)")
    public void insensitiveString() {
        // given
        String myFullName = "KimHaeDong";

        // when
        CaseInsensitiveString caseInsensitiveString = new CaseInsensitiveString(myFullName);

        // then
        assertThat(caseInsensitiveString.equals("KIMHAEDONG")).isTrue();
        assertThat("KIMHAEDONG".equals(caseInsensitiveString)).isFalse();
        assertThat(caseInsensitiveString.equals(caseInsensitiveString.toString())).isFalse();
        assertThat(Lists.newArrayList(caseInsensitiveString).contains("KIMHAEDONG")).isFalse();
    }

    @Test
    @DisplayName("대칭성 위배를 개선한 추이성 규약 equals")
    public void transitivityEquals() {
        // given
        String myFullName = "KimHaeDong";
        String myCapitalName = "KIMHAEDONG";

        // when
        CaseInsensitiveString myString = new CaseInsensitiveString(myFullName);
        CaseInsensitiveString myCapitalString = new CaseInsensitiveString(myCapitalName);
        // then
        assertThat(myString.TransitivityEquals(myCapitalString)).isTrue();
        assertThat(myCapitalString.TransitivityEquals(myString)).isTrue();
    }
}
