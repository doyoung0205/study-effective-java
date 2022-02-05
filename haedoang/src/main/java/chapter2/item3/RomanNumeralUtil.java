package chapter2.item3;

/**
 * packageName : chapter2.item3
 * fileName : RomanNumeralUtil
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
public class RomanNumeralUtil {
    private static final RomanNumeralUtil INSTANCE = new RomanNumeralUtil();
    private RomanNumeralUtil() {
    }

    public static RomanNumeralUtil getInstance() {
        return INSTANCE;
    }

    public String toRomanNumeral(int arabic) {
        return RomanNumeral.getValue(arabic);
    }
}
