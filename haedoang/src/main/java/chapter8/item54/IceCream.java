package chapter8.item54;

/**
 * packageName : chapter8.item54
 * fileName : IceCream
 * author : haedoang
 * date : 2022-05-24
 * description :
 */
public class IceCream {
    private String name;

    private IceCream(String name) {
        this.name = name;
    }

    public static IceCream valueOf(String name) {
        return new IceCream(name);
    }

    public String getName() {
        return name;
    }
}
