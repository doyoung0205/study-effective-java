package chapter2.item1;

import item1.DayOfWeek;
import item1.Wine;
import item1.Wines;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item1
 * fileName : Test
 * author : haedoang
 * date : 2022/01/29
 * description :
 */
@DisplayName("정적 팩토리 메서드 테스트")
public class StaticFactoryMethodTest {
    @Test
    @DisplayName("생성자를 통해 객체 생성하기")
    public void createWineByConstructor() {
        // give
        Wine bottle1 = new Wine(Locale.US, "SUBMISSION");
        Wine bottle2 = new Wine(Locale.US, "SUBMISSION");

        // then
        assertThat(bottle1).isNotNull();
        assertThat(bottle2).isNotNull();
        assertThat(bottle1).isEqualTo(bottle2);
        assertThat(bottle1).isNotSameAs(bottle2);
    }

    @Test
    @DisplayName("from은 매개변수를 하나 받아서 인스턴스를 반환한다")
    public void fromMethodTest() {
        // given
        Wine wine = Wine.from("ISCAY");

        boolean actual = wine.checkName("ISCAY");

        // then
        assertThat(wine).isNotNull();
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("of는 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드")
    public void ofMethodTest() {
        // given
        List<Wine> wines = Wine.of(Locale.US, Locale.KOREA);

        // then
        assertThat(wines).extracting(Wine::madeIn).contains(Locale.US, Locale.KOREA);
    }

    @Test
    @DisplayName("EnumSet을 사용한 of Method 예시")
    public void ofMethodExample() {
        // given
        final EnumSet<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        // then
        assertThat(weekend).hasSize(2);
    }

    @Test
    @DisplayName("valueOf는 매개변수와 값이 같은 인스턴스를 반환한다.")
    public void valueOfTest() {
        // given
        Wine wine = Wine.valueOf(Locale.US, "SUBMISSION");

        // then
        assertThat(wine.checkName("SUBMISSION")).isTrue();
        assertThat(wine.madeIn()).isEqualTo(Locale.US);
    }

    @Test
    @DisplayName("instance, getInstance는 매개변수로 명시한 인스턴스를 반환하지만 같은 인스턴스를 보장하지는 않는다.")
    public void instanceTest() {
        // given
        final Wine bottle1 = Wine.instance(Locale.KOREA, "SOJU");
        final Wine bottle2 = Wine.getInstance(Locale.KOREA, "SOJU");

        // then
        assertThat(bottle1).isEqualTo(bottle2);
        assertThat(bottle1).isSameAs(bottle2)
                .as("코드 상으로는 instance가 null인 경우 instance를 생성해서 반환하도록 작성하였다.")
                .as("따라서 같은 인스턴스를 반환하지만 해당 naming은 같은 인스턴스를 보장하지 않으므로")
                .as("해당 네이밍을 가진 객체를 사용할때는 주의해야 한다");
    }

    @Test
    @DisplayName("create, newInstance")
    public void createTest() {
        // given
        Wine bottle1 = Wine.create(Locale.KOREA, "막걸리");
        Wine bottle2 = Wine.newInstance(Locale.KOREA, "막걸리");

        // then
        assertThat(bottle1).isEqualTo(bottle2);
        assertThat(bottle1).isNotSameAs(bottle2)
                .as("getInstance 와 다르게 반드시 다른 객체를 생성해야 한다.");
    }

    @Test
    @DisplayName("getType은 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 사용한다(getInstance와 같음)")
    public void getType() {
        // given
        final ArrayList<Wine> wineList = Lists.newArrayList(Wine.getInstance(Locale.KOREA, "맥주"), Wine.getInstance(Locale.KOREA, "소주"));

        // when
        Wines wines = Wine.getList(wineList);

        // then
        assertThat(wines.has(Wine.valueOf(Locale.KOREA, "맥주"))).isTrue();
        assertThat(wines.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("newType은 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 사용한다(newInstance와 같음)")
    public void newType() {
        // given
        final ArrayList<Wine> wineList = Lists.newArrayList(Wine.newInstance(Locale.KOREA, "맥주"), Wine.newInstance(Locale.KOREA, "소주"));

        // when
        Wines wines = Wine.newList(wineList);

        // then
        assertThat(wines.has(Wine.valueOf(Locale.KOREA, "맥주"))).isTrue();
        assertThat(wines.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Collections 프레임워크에서 사용한 type 예시 ")
    public void typeMethodExample() {
        // given
        final Vector<DayOfWeek> collect = Arrays.stream(DayOfWeek.values()).
                collect(Collectors.toCollection(Vector::new));

        // when
        Enumeration e = collect.elements();
        final ArrayList list = Collections.list(e);

        // then
        assertThat(list).hasSize(7);
    }
}