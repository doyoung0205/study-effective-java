package chapter2.item1;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * packageName : chapter2.chapter2.item1
 * fileName : KoreanWine
 * author : haedoang
 * date : 2022/01/29
 * description :
 */
public enum KoreaWine {
    SOJU("소주"),
    MAEKJU("맥주");

    private Locale locale = Locale.KOREA;
    private String name;

    KoreaWine(String name) {
        this.name = name;
    }

    public static List<Wine> list() {
        return Arrays.stream(KoreaWine.values())
                .map(wine -> new Wine(wine.locale, wine.name))
                .collect(Collectors.toList());
    }
}