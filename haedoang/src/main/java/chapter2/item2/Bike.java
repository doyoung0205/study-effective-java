package chapter2.item2;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.math.BigDecimal;

/**
 * packageName : chapter2.item2
 * fileName : Bike
 * author : haedoang
 * date : 2022/02/04
 * description :
 */
public class Bike {
    private final String name;
    private final BigDecimal price;
    private final Color color;

    private final String option1;
    private final String option2;

    private Bike(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.color = builder.color;
        this.option1 = builder.option1;
        this.option2 = builder.option2;
    }

    public static class Builder {
        private final String name;
        private final BigDecimal price;
        private final Color color;

        private String option1;
        private String option2;

        public Builder(String name, BigDecimal price, Color color) {
            if (StringUtils.isEmpty(name)) {
                throw new IllegalArgumentException("이름은 필수값입니다.");
            }
            this.name = name;
            this.price = price;
            this.color = color;
        }

        public Builder option1(String option1) {
            this.option1 = option1;
            return this;
        }

        public Builder option2(String option2) {
            this.option2 = option2;
            return this;
        }

        public Bike build() {
            return new Bike(this);
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Color getColor() {
        return color;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }
}
