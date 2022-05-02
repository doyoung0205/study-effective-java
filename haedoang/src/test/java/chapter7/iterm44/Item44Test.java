package chapter7.iterm44;

import chapter7.item44.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/05/02
 * description :
 */
public class Item44Test {
    @Test
    @DisplayName("템플릿 메서드 패턴 테스트")
    public void templateMethodPattern() {
        // given
        final ComputerBuilder standardComputerBuilder = new StandardComputerBuilder();
        final ComputerBuilder highEndComputerBuilder = new HighEndComputerBuilder();

        // when
        final Computer standardComputer = standardComputerBuilder.buildComputer();
        final Computer highEndComputer = highEndComputerBuilder.buildComputer();

        // then
        assertThat(standardComputer.getComputerParts().get("Processor")).isEqualTo("Standard Processor");
        assertThat(highEndComputer.getComputerParts().get("Processor")).isEqualTo("High-end Processor");
        assertThat(standardComputer.getComputerParts().get("Motherboard")).isEqualTo("Standard Motherboard");
        assertThat(highEndComputer.getComputerParts().get("Motherboard")).isEqualTo("High-end Motherboard");
    }

    @Test
    @DisplayName("cache를 지원하는 linkedList 테스트")
    public void useMapCache() {
        // given
        final CustomLinkedHashMap<String, String> map = new CustomLinkedHashMap<>();

        // when
        map.put("MON", "월요일");
        map.put("TUE", "화요일");
        map.put("WED", "수요일");
        map.put("THR", "목요일");
        map.put("FRI", "금요일");
        map.put("SAT", "토요일");
        map.put("SUN", "일요일");

        // then
        assertThat(map).hasSize(5)
                .as("removeEldestEntry메서드를 재정의해서 cache 기능으로 사용할 수 있다");
    }

    @Test
    @DisplayName("cache를 지원하는 linkedList 함수형 인터페이스 테스트")
    public void useMapCacheWithFunctionalInterface() {
        // given
        final CustomLinkedHashMap2<String, String> map =
                new CustomLinkedHashMap2<>((map1, eldest) -> map1.size() > 5);

        // when
        map.put("MON", "월요일");
        map.put("TUE", "화요일");
        map.put("WED", "수요일");
        map.put("THR", "목요일");
        map.put("FRI", "금요일");
        map.put("SAT", "토요일");
        map.put("SUN", "일요일");

        // then
        assertThat(map).hasSize(5)
                .as("removeEldestEntry메서드를 재정의해서 cache 기능으로 사용할 수 있다");
    }

    @Test
    @DisplayName("cache를 지원하는 linkedList BiPredicate 테스트")
    public void useMapCacheWithBiPredicate() {
        // given
        final CustomLinkedHashMap3<String, String> map =
                new CustomLinkedHashMap3<>((map1, entry) -> map1.size() > 5);

        // when
        map.put("MON", "월요일");
        map.put("TUE", "화요일");
        map.put("WED", "수요일");
        map.put("THR", "목요일");
        map.put("FRI", "금요일");
        map.put("SAT", "토요일");
        map.put("SUN", "일요일");

        // then
        assertThat(map).hasSize(5)
                .as("removeEldestEntry메서드를 재정의해서 cache 기능으로 사용할 수 있다");
    }

}
