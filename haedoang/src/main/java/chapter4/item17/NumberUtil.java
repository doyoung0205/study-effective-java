package chapter4.item17;

import java.math.BigInteger;

/**
 * author : haedoang
 * date : 2022/03/09
 * description :
 */
public class NumberUtil {
    private NumberUtil() {
    }

    /**
     * @param number1
     * @param number2
     * @return description : LCM 이란 ? <br/>
     * Least Common Multiple은 최소 공배수이다 <br/>
     * 표기법 : lcm(2,3,6) => 12 <br/>
     * <br/>
     * 알고리즘 : <br/>
     * step1. a = 0 또는 b = 0 이면, lcm(a,b) = 0이다. 그렇지 않으면 step2로 이동한다 <br/>
     * step2. 두 숫자의 절대값을 계산한다 <br/>
     * step3. step2에서 계산된 두 값 중 더 큰값으로 lcm을 초기화한다 ex) lcm(12,-18) => lcm(12,18) => lcm(12,18) = 18 <br/>
     * step4. lcm을 더 작은 절대값을 나눌 수 있으면 반환한다 ex) lcm(3,6) => 6 이면 6을 3으로 나눌수 있기 때문에 반환  <br/>
     * step5. 둘 중 더 높은 절대값만큼 lcm을 증가시키고 step4 로 이동한다 <br/>
     */
    public static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }

        int absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    /**
     * @param number1
     * @param number2
     * @return description: Binary GCD 알고리즘 <br/>
     * 이진 GCD(Binary Greatest Common Divisor)알고리즘(슈타인 알고리즘 또는 이진 유클리드 알고리즘)은 음이 아닌 두 정수의 최대 공약수를 계산하는 알고리즘 <br/>
     * 기존의 유클리드 알고리즘보다 간단한 산술 연산을 사용한다. 나눗셈을 산술 이동, 비교 및 빼기로 대체함 <br/>
     * 표기법 : gcd(3,6) => 3 <br/>
     * <br/>
     * 알고리즘 : <br/>
     * step1. gcd(0,v) = v 이다. 0은 모든 수로 나누어떨어지고, v는 v로 나누어지는 가장 큰 값이기 때문 <br/>
     * step2. u,v가 짝수인 경우, gcd(u,v) = 2*gcd(u/2, v/2) 이다. 2가 공약수이기 때문 <br/>
     * step3. u는 짝수, v는 홀수이면, gcd(u,v) = gcd(u/2,v) 이다. 2가 공약수가 아니기 때문. 마찬가지로 u는 홀수, v가 짝수 인경우 gcd(u,v) = gcd(u,v/2) <br/>
     * step4. u,v가 홀수이고, u >= v이면, gcd(u,v) = gcd((u-v)/2, v), <br/>
     *        u < v이면, gcd(u,v) = gcd((v-u)/2, u) 이다(유클리드 호제법과 제 3단계의 조합) <br/>
     * step5. u = v 가 될 때까지나 u = 0이 될 때까지 step2 에서 step4까지 반복한다. 그 결과는 2^k * v 꼴이다 <br/>
     * <br/>
     * 최대공약수로 최소공배수 구하는 법 <br/>
     *  |a * b | / 최대공약수 = 최소 공배수 <br/>
     *
     *  ex) 48, 56  <br/>
     *  bcd(48, 56)  <br/>
     *  2*bcd(24,28) <br/>
     *  2^2 *bcd(12,14) <br/>
     *  2^3 *bcd(6,7) <br/>
     *  2^3 *bcd(3,7) => step3 <br/>
     *  2^3 *bcd(2,3) => step4 <br/>
     *  2^3 *bcd(1,3) => step3 <br/>
     *  2^3 *bcd(1,1) => step4 <br/>
     *  u = v ==> 2^3 * v =>  8 <br/>
     */
    public static BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2); //최대 공약수
        final BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }
}
