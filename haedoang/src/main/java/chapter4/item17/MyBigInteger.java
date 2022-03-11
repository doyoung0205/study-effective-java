package chapter4.item17;

import java.math.BigInteger;

/**
 * author : haedoang
 * date : 2022/03/09
 * description :
 */
public class MyBigInteger extends BigInteger {
    private BigInteger value;

    public MyBigInteger(String val) {
        super(val);
        this.value = this;
    }

    public void addNumber(int number) {
        this.value = value.add(BigInteger.valueOf(number));
    }

    @Override
    public int intValue() {
        return value.intValue();
    }
}
