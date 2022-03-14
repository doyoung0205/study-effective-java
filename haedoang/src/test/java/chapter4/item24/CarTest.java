package chapter4.item24;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter4.item24
 * fileName : CarTest
 * author : haedoang
 * date : 2022-03-14
 * description :
 */
class CarTest {
    @Test
    @DisplayName("내부 클래스는 외부 객체에서만 접근할 수 있다")
    public void innerClassTest() {
        // given
        CarWithInnerClass 아반테_옵션추가 = new CarWithInnerClass("Avante2.0", "black", 20_000_000);
        CarWithInnerClass 아반테_무옵션 = new CarWithInnerClass("Avante2.0", "black", 20_000_000);
        // 이렇게 생성도 할 수 있으나 객체 의존적이므로 불필요한 메모리를 가지게 된다
        CarWithInnerClass.Option 외부인스턴스와_연결된_옵션 = 아반테_옵션추가.new Option(3L, "스마트키", 200_000);

        // then
        아반테_옵션추가.addOption(1L, "파노라마선루프", 2_000_000);
        아반테_옵션추가.addOption(2L, "보조석에어백", 700_000);

        assertThat(아반테_옵션추가.getPrice()).isGreaterThan(아반테_무옵션.getPrice())
                .as("내부 클래스는 독립젹으로 객체를 생성할 수 없음");
    }

    @Test
    @DisplayName("정적 클래스는 독립적으로 객체를 생성할 수 있다")
    public void useStaticClass() {
        // given
        Car.Option 에어백 = Car.Option.valueOf(1L, "보조석에어백", 700_000);
        Car.Option 파노라마선루프 = Car.Option.valueOf(2L, "파노라마선루프", 2_000_000);

        Car 아반테_무옵션 = Car.valueOf("Avante2.0", "black", 20_000_000, Lists.emptyList());
        Car 아반테_옵션추가 = Car.valueOf("Avante2.0", "black", 20_000_000, Lists.newArrayList(에어백, 파노라마선루프));

        // then
        assertThat(아반테_옵션추가.getPrice()).isGreaterThan(아반테_무옵션.getPrice())
                .as("정적 클래스는 독립적으로 객체를 생성할 수 있다")
                .as("즉, 객체 간의 공유가 가능하다");
    }
}
