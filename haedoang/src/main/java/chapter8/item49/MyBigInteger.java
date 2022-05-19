package chapter8.item49;

import java.math.BigInteger;

/**
 * packageName : chapter8
 * fileName : MyBigInteger
 * author : haedoang
 * date : 2022-05-18
 * description :
 */
public class MyBigInteger {
    private final BigInteger number;

    private MyBigInteger(BigInteger number) {
        this.number = number;
    }

    public static MyBigInteger valueOf(BigInteger number) {
        return new MyBigInteger(number);
    }


    /**
     *
     * @param m 계수(m) 양수여야 한다
     * @return 현재 값 mod m
     * @Throws ArithmeticException m이 0보다 작거나 같으면 발생한다.
     */
    public BigInteger mod(BigInteger m) {
        if (m.signum() <= 0) {
            throw new ArithmeticException("계수(m)은 양수여야 합니다 : " + m);
        }

        return number.mod(m);
    }
}
