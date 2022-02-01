package item1;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : item1
 * fileName : Wines
 * author : haedoang
 * date : 2022/02/01
 * description :
 */
public class Wines {
    private final List<Wine> wines;

    private Wines(List<Wine> wineList) {
        this.wines = new ArrayList<>(wineList);
    }

    public static Wines valueOf(List<Wine> wineList) {
        return new Wines(wineList);
    }

    public boolean has(Wine wine) {
        return wines.contains(wine);
    }

    public int size() {
        return wines.size();
    }
}