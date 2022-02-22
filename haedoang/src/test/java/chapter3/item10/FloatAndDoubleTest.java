package chapter3.item10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/18
 * description :
 */
public class FloatAndDoubleTest {

    @Test
    @DisplayName("Float이나 Double 비교하기")
    public void floatAndDoubleTest() {
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
