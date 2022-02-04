package chapter2.item2;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * packageName : chapter2.item2
 * fileName : Car
 * author : haedoang
 * date : 2022/02/04
 * description :
 */
public class Car {
    private final String name;
    private final BigDecimal price;
    private final Color color;

    private String option1;
    private String option2;

    public Car(String name, BigDecimal price, Color color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    public Car(String name, BigDecimal price, Color color, String option1) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.option1 = option1;
    }

    public Car(String name, BigDecimal price, Color color, String option1, String option2) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.option1 = option1;
        this.option2 = option2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(price, car.price) && Objects.equals(color, car.color) && Objects.equals(option1, car.option1) && Objects.equals(option2, car.option2);
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
