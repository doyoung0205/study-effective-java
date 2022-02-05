package chapter2.item3;

/**
 * packageName : chapter2.item3
 * fileName : NumberUtil
 * author : haedoang
 * date : 2022/02/05
 * description : public static final 필드 방식의 싱글턴
 */
public class NumberUtil {
    public static final NumberUtil INSTANCE = new NumberUtil();

    private NumberUtil() {
    }

    public int sum(int a, int b) {
        return a + b;
    }

}
