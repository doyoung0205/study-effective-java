package chapter2.item3;

import java.io.Serializable;

/**
 * packageName : chapter2.item3
 * fileName : RomanNumeralUtilStrealer
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
public class RomanNumeralUtilStealer implements Serializable {
    static RomanNumeralUtil impersonator;
    private RomanNumeralUtil payload;

    private Object readResolve() {
        impersonator = payload;

        return new String[] {"change!!"};
    }


}
