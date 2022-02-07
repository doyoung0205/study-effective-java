package chapter2.item3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/07
 * description :
 */
@DisplayName("Java Supplier 테스트")
public class SupplierTest {
    @Test
    @DisplayName("Supplier는 반환값을 리턴한다.")
    public void supplierTest() {
        // given
        Supplier<String> stringSupplier = () -> "공급자 테스트";
        Supplier<LocalDateTime> localDateTimeSupplier = () -> LocalDateTime.now();
        IntSupplier intSupplier = () -> 10;

        // then
        assertThat(stringSupplier.get()).isEqualTo("공급자 테스트").isInstanceOf(String.class);
        assertThat(localDateTimeSupplier.get()).isBeforeOrEqualTo(LocalDateTime.now()).isInstanceOf(LocalDateTime.class);
        assertThat(intSupplier.getAsInt()).isEqualTo(10).isInstanceOf(Integer.class);
    }
}
