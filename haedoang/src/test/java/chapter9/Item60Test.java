package chapter9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/06/04
 * description :
 */
public class Item60Test {
    @Test
    @DisplayName("double은 정확한 결과를 표기하기 어렵다")
    public void minus() {
        // given
        double funds = 1.00;
        int itemBought = 0;

        // when
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemBought++;
        }

        // then
        assertThat(funds).isNotEqualTo(0.40);
    }

    @Test
    @DisplayName("BigDecimal의 정확한 값 표현")
    public void minusWithBigDecimal() {
        // given
        final BigDecimal TEN_CENTS = BigDecimal.valueOf(.10);
        int itemBought = 0;
        BigDecimal funds = BigDecimal.ONE;

        // when
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemBought++;
        }

        // then
        assertThat(funds.equals(BigDecimal.valueOf(.40)))
                .as("정확한 값 표현이 필요한 경우 사용된다");

    }
}
