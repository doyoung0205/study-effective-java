package chapter7.item43;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/04/26
 * description :
 */
public class Product {
    private int price;
    private String name;

    private Product(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public static Product valueOf(int price, String name) {
        return new Product(price, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
