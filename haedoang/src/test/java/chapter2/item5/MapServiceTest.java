package chapter2.item5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter2.item5
 * fileName : MapUtilTest
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
@DisplayName("의존 객체 주입 테스트")
class MapServiceTest {

    @Test
    @DisplayName("의존 객체 주입은 객체 사용의 유연성을 높혀준다.")
    public void usingDependencyInjection() {
        // given
        MapService naverMapService = MapService.valueOf(new NaverMap());
        MapService kakaoMapService = MapService.valueOf(new KakaoMap());

        // when
        Location actual1 = naverMapService.getCurrentLocation();
        Location actual2 = kakaoMapService.getCurrentLocation();

        // then
        assertThat(actual1).isEqualTo(actual2);
        assertThat(naverMapService.mapClassName()).isEqualTo("NaverMap");
        assertThat(kakaoMapService.mapClassName()).isEqualTo("KakaoMap");
    }
}
