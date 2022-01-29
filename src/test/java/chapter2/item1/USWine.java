package chapter2.item1;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * packageName : chapter2.item1
 * fileName : USWine
 * author : haedoang
 * date : 2022/01/29
 * description :
 */
public enum USWine {
    SUBMISSION("SUBMISSION"),
    BREAD_AND_BUTTER("BREAD & BUTTER");

    private Locale locale = Locale.US;
    private String name;

    USWine(String name) {
        this.name = name;
    }

    public static List<Wine> list() {
        return Arrays.stream(USWine.values())
                .map(wine -> new Wine(wine.locale, wine.name))
                .collect(Collectors.toList());
    }
}
