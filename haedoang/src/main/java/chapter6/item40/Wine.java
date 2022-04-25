package chapter6.item40;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/04/22
 * description :
 */
public class Wine {
    private String name;
    private BigDecimal price;

    private Wine(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public static Wine valueOf(String name, BigDecimal price) {
        return new Wine(name, price);
    }

    public boolean equals(Wine w) {
        return this.name.equals(w.name) && this.price.equals(w.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
