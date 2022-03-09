package chapter4.item22;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : chapter4.item22
 * fileName : StaticConstantsClassTest
 * author : haedoang
 * date : 2022-03-08
 * description :
 */
public class StaticConstantsClassTest {

    @Test
    @DisplayName("global하게 사용될 상수라면 정적 상수 유틸리티 클래스를 사용하자")
    public void staticConstantsClass() {
        // then
        assertThat(UserConstants.USER_NAME).isEqualTo("haedoang");
        assertThat(UserConstants.USER_AGE).isEqualTo(34);
        assertThat(UserConstants.USER_ADDRESS).isEqualTo("서울시 성동구 송정동");

    }
}
