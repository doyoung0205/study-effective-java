package chapter2.item5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item5
 * fileName : LegacyMapUtilTest
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
@DisplayName("정적 유틸리티 테스트")
class LegacyMapUtilTest {

    @Test
    @DisplayName("정적 유틸리티 사용은 확장에 닫혀있다")
    public void usingStaticUtility() {
        // when
        Location actual = LegacyMapUtil.getCurrentLocation();

        // then
        assertThat(actual).isNotNull();
        assertThat(LegacyMapUtil.mapClassName()).isEqualTo("NaverMap")
                .as("정적 유틸리티는 인스턴스를 재사용하지 않는 장점이 있지만 확장이 어려운 단점이 있다.");
    }

}
