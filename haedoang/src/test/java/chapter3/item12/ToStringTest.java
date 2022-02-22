package chapter3.item12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * author : haedoang
 * date : 2022/02/19
 * description :
 */
public class ToStringTest {

    @Test
    @DisplayName("BigInteger toString")
    public void toStringTest() {
        // given
        final BigInteger bigInteger = new BigInteger("11");

        System.out.println(bigInteger.toString());
        System.out.println(bigInteger.toString(2)); //2진수표기
        System.out.println(bigInteger.toString(8)); //8진수표기
        System.out.println(bigInteger.toString(16)); //16진수표기

    }
}

