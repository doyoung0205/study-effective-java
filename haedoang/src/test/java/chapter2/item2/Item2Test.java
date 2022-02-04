package chapter2.item2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.math.BigDecimal;

import static chapter2.item2.KoreanPasta.*;
import static chapter2.item2.NewYorkPasta.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * packageName : chapter2.item2
 * fileName : Item2Test
 * author : haedoang
 * date : 2022/02/04
 * description :
 */
@DisplayName("Builder 테스트")
public class Item2Test {
    @Test
    @DisplayName("점층적 생성자 패턴")
    public void telescopingConstructorPatternTest() {
        // given
        Car 옵션이_없는_차량 = new Car("Avante", BigDecimal.valueOf(20_000_000), Color.BLACK);
        Car 옵션이_하나인_차량 = new Car("Morning", BigDecimal.valueOf(18_000_000), Color.WHITE, "네비게이션");
        Car 옵션이_두개인_차량 = new Car("Tuscon", BigDecimal.valueOf(29_000_000), Color.GRAY, "네비게이션", "통풍시트");

        // then
        assertThat(옵션이_없는_차량.getOption1()).isNull();
        assertThat(옵션이_하나인_차량.getOption1()).isNotNull();
        assertThat(옵션이_두개인_차량.getOption2()).isNotNull()
                .as("사용할 생성자를 구분하기 어려운 단점이 있다.");
    }

    @Test
    @DisplayName("자바빈즈 패턴")
    public void javaBeansPattern() {
        // given
        Bicycle 옵션이_없는_자전거 = new Bicycle();
        옵션이_없는_자전거.setName("삼천리자전거");
        옵션이_없는_자전거.setPrice(BigDecimal.valueOf(1_000_000));
        옵션이_없는_자전거.setColor(Color.GREEN);

        Bicycle 옵션이_하나인_자전거 = new Bicycle();
        옵션이_하나인_자전거.setName("사천리자전거");
        옵션이_하나인_자전거.setPrice(BigDecimal.valueOf(2_000_000));
        옵션이_하나인_자전거.setColor(Color.BLUE);
        옵션이_하나인_자전거.setOption1("후미등");

        Bicycle 옵션이_두개인_자전거 = new Bicycle();
        옵션이_두개인_자전거.setName("제일좋은자전거");
        옵션이_두개인_자전거.setPrice(BigDecimal.valueOf(5_000_000));
        옵션이_두개인_자전거.setColor(Color.GREEN);
        옵션이_두개인_자전거.setOption1("스마트폰거치대");
        옵션이_두개인_자전거.setOption2("후미등");

        // then
        assertThat(옵션이_없는_자전거.getOption1()).isNull();
        assertThat(옵션이_하나인_자전거.getOption1()).isNotNull();
        assertThat(옵션이_두개인_자전거.getOption2()).isNotNull()
                .as("불완전한 객체 상태가 존재하게 된다.");
    }

    @Test
    @DisplayName("Builder 패턴")
    public void builderPattern () {
        // given
        Bike 옵션이_두개인_바이크 = new Bike.Builder("ELITE125", BigDecimal.valueOf(2_800_000), Color.WHITE)
                .option1("열선핸들")
                .option2("스마트폰거치대")
                .build();
        // then
        assertThat(옵션이_두개인_바이크.getOption1()).isNotNull();
        assertThat(옵션이_두개인_바이크.getOption2()).isNotNull();
    }

    @Test
    @DisplayName("Builder 패턴 불변식 검증")
    public void builderPatternValidation() {
        assertThatThrownBy(() -> new Bike.Builder(null, BigDecimal.valueOf(2_800_000), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 필수값입니다.");
    }

    @Test
    @DisplayName("계층적으로 설계된 클래스에서의 Builder 패턴")
    public void BuilderInHierarchicalClasses() {
        // given
        final KoreanPasta koreanPasta = new KoreanPasta.Builder(Size.곱배기)
                .addNoodle(Noodle.라자냐)
                .addNoodle(Noodle.마카로니)
                .build();

        final NewYorkPasta newYorkPasta = new NewYorkPasta.Builder(Bread.마늘빵)
                .addNoodle(Noodle.라비올리)
                .build();

        // then
        assertThat(koreanPasta.noodleSize()).isEqualTo(2);
        assertThat(koreanPasta.equalSize(Size.곱배기)).isTrue();
        assertThat(newYorkPasta.noodleSize()).isEqualTo(1);
        assertThat(newYorkPasta.equalBread(Bread.마늘빵)).isTrue();
    }
}
