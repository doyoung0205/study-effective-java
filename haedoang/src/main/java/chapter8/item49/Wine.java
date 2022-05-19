package chapter8.item49;

import java.math.BigInteger;

/**
 * packageName : chapter8
 * fileName : Wine
 * author : haedoang
 * date : 2022-05-18
 * description :
 */
public class Wine {
    private final String name;
    private final BigInteger price;

    private Wine(String name, BigInteger price) {
        assert name != null;
        assert price.signum() >= 0;

        this.name = name;
        this.price = price;
    }

    public static Wine valueOf(String name, BigInteger price) {
        return new Wine(name, price);
    }

    public String getName() {
        return name;
    }

    public BigInteger getPrice() {
        return price;
    }
}
