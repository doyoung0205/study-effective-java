package chapter2.item3;

import java.util.Arrays;

/**
 * packageName : chapter2.item3
 * fileName : RomanNumeral
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
public enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int arabic;

    RomanNumeral(int arabic) {
        this.arabic = arabic;
    }

    public static String getValue(int arabic) {
        return Arrays.stream(RomanNumeral.values())
                .filter(romanNumeral -> romanNumeral.arabic == arabic)
                .findFirst()
                .orElseThrow()
                .name();
    }
}
