package study.heejin.chapter8.item54;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheeseShop {

    private final List<String> cheeseInStock = new ArrayList<>();

    public void addStock(List<String> cheeses) {
        cheeseInStock.addAll(cheeses);
    }

    // 컬렉션이 비어 있으면 null을 반환한다. 따라하지 말 것!
    public List<String> getNullable() {
        return cheeseInStock.isEmpty() ? null : new ArrayList<>(cheeseInStock);
    }

    // 빈 컬렉션을 반환하는 올바른 방법
    public List<String> getCollections() {
        return new ArrayList<>(cheeseInStock);
    }

    // 빈 컬렉션을 매번 새로 할당하지 않도록 최적화한 방법
    public List<String> getCollectionsOptimization() {
        return cheeseInStock.isEmpty() ? Collections.emptyList()
                                       : new ArrayList<>(cheeseInStock);
    }

    // 길이가 0일 수도 있는 배열을 반환하는 올바른 방법
    public String[] getArray() {
        return cheeseInStock.toArray(new String[0]);
    }

    // 빈 배열을 매번 새로 할당하지 않도록 최적화한 방법
    private static final String[] EMPTY_CHEESE_ARRAY = new String[0];
    public String[] getArrayOptimization() {
        return cheeseInStock.toArray(EMPTY_CHEESE_ARRAY);
    }

    // 배열을 미리 할당하면 성능이 나빠진다.
    public String[] getBadArray() {
        return cheeseInStock.toArray(new String[cheeseInStock.size()]);
    }

    public static void main(String[] args) {
        CheeseShop cheeseShop = new CheeseShop();
//        cheeseShop.addStock(Arrays.asList("Stilton", "Mascarpone", "Ricotta"));

        List<String> cheeses = cheeseShop.getCollectionsOptimization();

        if (cheeses != null && cheeses.contains("Stilton")) {
            System.out.println("스틸턴 치즈가 있다!");
        }

    }
}
