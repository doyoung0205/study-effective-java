package chapter6.item34;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static chapter6.item34.Calculator.*;
import static chapter6.item34.HealthProgram.ExerciseType.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/15
 * description :
 */
public class Item34Test {

    @Test
    @DisplayName("정수 열거 패턴은 타입 안전을 보장하지 않으며 표현력도 좋지 않다")
    public void intEnumPattern() {
        // given
        int bestFavoriteFruit = Favorites.FRUIT_APPLE;
        int bestFavoriteFood = Favorites.FOOD_HAMBURGER;

        // then
        assertThat(bestFavoriteFruit).isEqualTo(Favorites.FRUIT_APPLE)
                .isEqualTo(bestFavoriteFood)
                .as("접두어로 구분할 수 밖에 없기 때문에 타입 안전을 보장하지 않는다.")
                .as("그리고 값을 변경할 시 상수 값을 전부 변경해야 한다.");
    }

    @Test
    @DisplayName("enum은 싱글턴으로 존재하고, 타입 안전하다")
    public void enumType() {
        // given
        final Fruit myFavoriteFruit = Fruit.APPLE;

        // then
        assertThat(myFavoriteFruit).isEqualTo(Fruit.APPLE)
                .isEqualTo(Fruit.myFavoriteFruit())
                .isSameAs(Fruit.APPLE);

        assertThat(myFavoriteFruit.toString()).isEqualTo("APPLE")
                .as("toString() 출력하기 적합한 문자열로 해준다");
    }

    @Test
    @DisplayName("열거 타입의 메서드와 필드를 추가한 지하철 요금 구현 테스트")
    public void subwayFareTest() {
        // given
        final SubwayFare childFare = SubwayFare.CHILD;
        final SubwayFare teenFare = SubwayFare.TEEN;
        final SubwayFare normalFare = SubwayFare.NORMAL;
        final SubwayFare othersFare = SubwayFare.OTHERS;

        // then
        assertThat(childFare.getFareByCard()).isEqualTo(450);
        assertThat(teenFare.getEarlyBirdFareByCard()).isEqualTo(580);
        assertThat(normalFare.getFareByCash()).isEqualTo(1_350);
        assertThat(othersFare.getEarlyBirdFareByCash())
                .isEqualTo(othersFare.getEarlyBirdFareByCard())
                .isEqualTo(othersFare.getFareByCash())
                .isEqualTo(othersFare.getFareByCard())
                .isEqualTo(0)
                .as("조조할인제 가격은 최적화를 위해 생성자에서 처리");
    }

    @Test
    @DisplayName("열거 타입은 정의된 상수를 배열에 담아 반환하는 기능을 제공한다")
    public void enumValues() {
        // when
        final Set<String> kinds = Arrays.stream(SubwayFare.values()).map(SubwayFare::getDesc).collect(Collectors.toSet());

        // then
        assertThat(kinds).containsExactly("일반", "청소년", "어린이", "노인, 장애인, 국가유공자")
                .as("정의된 순서대로 배열을 만듦");
    }

    @Test
    @DisplayName("상수별로 메서드를 구현할 수 있다")
    public void calculator() {
        // given
        final double addResult = PLUS.apply(1, 9);
        final double timesResult = TIMES.apply(2, 2);

        // then
        assertThat(addResult).isEqualTo(10);
        assertThat(timesResult).isEqualTo(4);

        Arrays.stream(Calculator.values())
                .forEach(it -> System.out.printf("%f %s %f = %f%n", 1d, it, 2d, it.apply(1d, 2d)));
    }

    @Test
    @DisplayName("enum으로 구현한 헬스 스케줄")
    public void enumHealthSchedule() {
        final HealthProgram monday = HealthProgram.MONDAY;
        final HealthProgram tuesday = HealthProgram.TUESDAY;
        final HealthProgram wednesday = HealthProgram.WEDNESDAY;
        final HealthProgram thursday = HealthProgram.THURSDAY;
        final HealthProgram friday = HealthProgram.FRIDAY;
        final HealthProgram saturday = HealthProgram.SATURDAY;
        final HealthProgram sunday = HealthProgram.SUNDAY;


        assertThat(monday.getExerciseType()).isEqualTo(thursday.getExerciseType()).isEqualTo(CHEST);
        assertThat(tuesday.getExerciseType()).isEqualTo(friday.getExerciseType()).isEqualTo(BACK);
        assertThat(wednesday.getExerciseType()).isEqualTo(saturday.getExerciseType()).isEqualTo(LEG);
        assertThat(sunday.getExerciseType()).isEqualTo(REST);

        assertThat(saturday.toString())
                .isEqualTo("오늘 할 운동은 [스쿼트] 입니다.");
    }
}
