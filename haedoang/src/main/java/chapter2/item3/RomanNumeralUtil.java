package chapter2.item3;

import java.io.Serializable;

/**
 * packageName : chapter2.item3
 * fileName : RomanNumeralUtil
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
public class RomanNumeralUtil implements Serializable {
    private static final RomanNumeralUtil INSTANCE = new RomanNumeralUtil();
    private String[] kind = {"UPPERCASE", "LOWERCASE"};
    private RomanNumeralUtil() {
    }

    public static RomanNumeralUtil getInstance() {
        return INSTANCE;
    }

    public String toRomanNumeral(int arabic) {
        return RomanNumeral.getValue(arabic);
    }

    /**
     * 싱글턴을 보장해주는 메서드
     * => 진짜 인스턴스를 반환하고 가짜 인스턴스를 가비지 컬렉터에 맡긴다.
     * @return
     */
    private Object readResolve() {
        return INSTANCE;
    }

    public String[] kinds() {
        return kind;
    }
}
