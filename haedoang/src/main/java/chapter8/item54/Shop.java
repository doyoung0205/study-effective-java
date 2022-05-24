package chapter8.item54;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : chapter8.item54
 * fileName : Shop
 * author : haedoang
 * date : 2022-05-24
 * description :
 */
public class Shop {
    private static final String[] EMPTY_ICE_CREAM_NAMES = new String[0];
    private final String shopName;
    private final List<IceCream> iceCreams;

    private Shop(String shopName) {
        this.shopName = shopName;
        this.iceCreams = new ArrayList<>();
    }

    public static Shop valueOf(String shopName) {
        return new Shop(shopName);
    }

    public void addIceCreams(List<IceCream> iceCreamList) {
        this.iceCreams.addAll(iceCreamList);
    }

    public String[] getIceCreamNames() {
        return iceCreams.stream()
                .map(IceCream::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    public String[] getIceCreamNamesRefactoring() {
        return iceCreams.stream()
                .map(IceCream::getName)
                .collect(Collectors.toList())
                .toArray(EMPTY_ICE_CREAM_NAMES);
    }
}
