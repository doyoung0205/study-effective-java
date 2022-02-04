package chapter2.item2;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * packageName : chapter2.item2
 * fileName : Bike
 * author : haedoang
 * date : 2022/02/04
 * description :
 */
public class Bicycle {
    private String name;
    private BigDecimal price;
    private Color color;

    private String option1;
    private String option2;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bike = (Bicycle) o;
        return Objects.equals(name, bike.name) && Objects.equals(price, bike.price) && Objects.equals(color, bike.color) && Objects.equals(option1, bike.option1) && Objects.equals(option2, bike.option2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, color, option1, option2);
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
