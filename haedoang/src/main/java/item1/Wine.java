

package item1;


import java.util.*;
import java.util.stream.Collectors;

/**
 * packageName : chapter2.item1
 * fileName : Wine
 * author : haedoang
 * date : 2022/01/29
 * description :
 */
public class Wine {
    private static Wine instance;
    private Locale locale;
    private final String name;

    private Wine(String name) {
        this.name = name;
    }

    public Wine(Locale locale, String name) {
        this.locale = locale;
        this.name = name;
    }

    public static Wine from(String name) {
        return new Wine(name);
    }

    public static List<Wine> of(Locale locale1, Locale locale2) {
        return getWineList(Arrays.asList(locale1, locale2));
    }

    public static Wine valueOf(Locale locale, String name) {
        return new Wine(locale, name);
    }

    public static Wine instance(Locale locale, String name) {
        if (Objects.isNull(instance)) {
            instance = new Wine(locale, name);
        }
        return instance;
    }

    public static Wine getInstance(Locale locale, String name) {
        if (Objects.isNull(instance)) {
            instance = new Wine(locale, name);
        }
        return instance;
    }

    private static List<Wine> getWineList(List<Locale> locales) {
        return locales.stream()
                .flatMap(it ->
                        it.getCountry().equals("US") ? USWine.list().stream() :
                                it.getCountry().equals("KR") ? KoreaWine.list().stream() : null
                )
                .collect(Collectors.toList());
    }

    public static Wine create(Locale locale, String name) {
        return new Wine(locale, name);
    }

    public static Wine newInstance(Locale locale, String name) {
        return new Wine(locale, name);
    }

    public static Wines getList(ArrayList<Wine> wineList) {
        return Wines.valueOf(wineList);
    }

    public static Wines newList(ArrayList<Wine> wineList) {
        return Wines.valueOf(wineList);
    }

    public boolean checkName(String checkName) {
        return name.equals(checkName);
    }

    public Locale madeIn() {
        return this.locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return Objects.equals(locale, wine.locale) && Objects.equals(name, wine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locale, name);
    }
}