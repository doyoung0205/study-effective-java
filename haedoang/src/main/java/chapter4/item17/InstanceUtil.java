package chapter4.item17;

import java.math.BigInteger;

/**
 * author : haedoang
 * date : 2022/03/09
 * description :
 */
public class InstanceUtil {
    private InstanceUtil() {
    }

    public static boolean isBigInteger(BigInteger val) {
        return val.getClass() == BigInteger.class;
    }
}
