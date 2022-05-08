package study.heejin.chapter8.item49;

import java.math.BigInteger;

public class ParameterThrowsDoc {
    public static void main(String[] args) {
        CustomBigInteger bi = new CustomBigInteger(10L);

        BigInteger mod = bi.mod(BigInteger.valueOf(3));
        System.out.println("mod = " + mod);

        // 예외 발생 - NullPointException
        // 상위의 BigInteger 메서드에서 예외를 정의하고 있다. - Line 97
        bi.mod(null);
        
        // 예외 발생 - ArithmeticException
        bi.mod(BigInteger.valueOf(-3));
    }

    static class CustomBigInteger {
        private final BigInteger bi;

        public CustomBigInteger(Long number) {
            this.bi = BigInteger.valueOf(number);
        }

        /**
         * 항상 음이 아닌 BigInteger를 반환한다는 점에서 remainer 메서드와 다르다.
         *
         * @param m 계수
         * @return 현재 값 mod m
         * @throws ArithmeticException m이 0보다 작거나 같으면 발생한다.
         */
        public BigInteger mod(BigInteger m) {
            if (m.signum() <= 0)
                throw new ArithmeticException("계수(m)는 양수여야 합니다. " + m);

            BigInteger result = bi.remainder(m);
            return (result.signum() >= 0 ? result : result.add(m));
        }
    }
}
