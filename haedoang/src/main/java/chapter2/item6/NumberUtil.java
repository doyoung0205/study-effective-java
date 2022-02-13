package chapter2.item6;

import java.util.regex.Pattern;

/**
 * packageName : chapter2.item6
 * fileName : NumberUtil
 * author : haedoang
 * date : 2022-02-11
 * description :
 */
public class NumberUtil {
    private static final Pattern NUM_PATTERN = Pattern.compile("\\d+");

    private NumberUtil() {
    }

    public static boolean isNumber(String str) {
        return str.matches("\\d+");
    }

    public static boolean isNumberRefactoring(String str) {
        return NUM_PATTERN.matcher(str).matches();
    }
}
