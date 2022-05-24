package chapter8.item55;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * packageName : chapter8.item55
 * fileName : Item55Test
 * author : haedoang
 * date : 2022-05-24
 * description :
 */
public class Item55Test {

    @Test
    @DisplayName("빈 컬렉션을 반환하는 대신 Optional.empty()를 반환하는 메서드 테스트")
    public void getMaxPriceWine() {
        // given
        ArrayList<Wine> wines = new ArrayList<>();
        Wine iscay = Wine.valueOf("ISCAY", 60_000);
        Wine decoy = Wine.valueOf("DECOY", 40_000);
        Wine f = Wine.valueOf("F", 40_000);

        // when
        Optional<Wine> emptyValue = CollectionUtil.max(wines);

        // then
        assertThat(emptyValue.isPresent()).isFalse().as("존재하지 않는 경우 Optional.empty()를 반환한다");

        // when
        wines.addAll(Arrays.asList(iscay, decoy, f));
        Optional<Wine> actual = CollectionUtil.max(wines);

        // then
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getName()).isEqualTo("ISCAY");
    }

    @Test
    @DisplayName("옵셔널을 사용한 기본값 테스트")
    public void optionalWithDefaultValue() {
        // given
        String nullValue = null;

        // when
        String actual = (String) Optional.empty().orElse("null");

        // then
        assertThat(actual).isEqualTo("null");
    }

    @Test
    @DisplayName("옵셔널을 사용하면 값이 없는 경우 원하는 예외를 던질 수 있음")
    public void optionalThrowCustomException() {
        // then
        assertThatThrownBy(() -> Optional.empty()
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(IllegalArgumentException.class)
                .as("supplier 사용으로 인해 예외 생성 비용이 들지 않는다");
    }

    @Test
    @DisplayName("옵셔널 get은 값을 보장한다")
    public void optionalGet() {
        // given
        String actual = Optional.of("안녕").get();

        // then
        assertThat(actual).isEqualTo("안녕")
                .as("값이 존재한다고 가정할 경우 get()으로 가져올 수 있다");
    }
}
