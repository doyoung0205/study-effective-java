package chapter8.item55;

/**
 * packageName : chapter8.item55
 * fileName : Wine
 * author : haedoang
 * date : 2022-05-24
 * description :
 */
public class Wine implements Comparable<Wine> {
    private final String name;
    private final int price;

    private Wine(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Wine valueOf(String name, int price) {
        return new Wine(name, price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Wine o) {
        return Integer.compare(price, o.price);
    }
}
