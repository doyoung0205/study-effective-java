package chapter2.item8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/14
 * description :
 */
class FinalizableTest {

    @Test
    @DisplayName("클래스 패스 파일 읽기 테스트")
    public void readFile() throws IOException {
        // given
        final Finalizable finalizable = new Finalizable();

        // when
        final String actual = finalizable.readFirstLine();

        // then
        assertThat(actual).isEqualTo("두아리파");
        assertThat(finalizable.isClose()).isFalse()
                .as("자동으로 객체가 소멸되지 않음을 테스트한다.");
    }

    @Test
    @DisplayName("finalize 테스트")
    public void finalizeTest() {
        // given
        final Finalizable finalizable = new Finalizable();

        // when
        finalizable.finalize();

        // then
        assertThat(finalizable.isClose()).isTrue();
    }

    @Test
    @DisplayName("finalize 가비지 컬렉터 수거 대상 테스트")
    public void finalizeAutoCloseTest() throws IOException {
        // when
        final String actual = new Finalizable().readFirstLine();

        // then
        assertThat(actual).isEqualTo("두아리파");

        // then
        System.gc();
    }
}