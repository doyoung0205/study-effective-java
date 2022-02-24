package study.heejin.chapter3.itme10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FloatAndDoubleTest {

    @Test
    @DisplayName("Float와 Double에서 equals와 compare 비교")
    void floatAndDoubleTest() {
        // then
        assertThat(-0.0f == 0.0f).isTrue();
        assertThat(Float.compare(-0.0f, 0.0f) == 0).isFalse();

        assertThat(Float.NaN == Float.NaN).isFalse();
        assertThat(Float.compare(Float.NaN, Float.NaN) == 0).isTrue();


        assertThat(-0.0d == 0.0d).isTrue();
        assertThat(Double.compare(-0.0d, 0.0d) == 0).isFalse();

        assertThat(Double.NaN == Double.NaN).isFalse();
        assertThat(Double.compare(Double.NaN, Double.NaN) == 0).isTrue();
    }
}
