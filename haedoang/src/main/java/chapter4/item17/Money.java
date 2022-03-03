package chapter4.item17;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/03/03
 * description :
 */
public final class Money {
    private final BigDecimal price;

    private Money(BigDecimal price) {
        this.price = price;
    }

    public static Money valueOf(int price) {
        return new Money(BigDecimal.valueOf(price));
    }

    public Money plus(Money money) {
        return new Money(price.add(money.price));
    }

    public Money minus(Money money) {
        return new Money(price.subtract(money.price));
    }

    public boolean isPositive() {
        return price.intValue() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return Objects.equals(price, money.price);
    }

    @Override
    public int hashCode() {
        return 31 * price.hashCode();
    }

    @Override
    public String toString() {
        return NumberFormat.getCurrencyInstance(Locale.KOREA).format(price);
    }
}
