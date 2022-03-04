package study.heejin.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter4.item17.Address;
import study.heejin.chapter4.item17.BigIntegerExtends;
import study.heejin.chapter4.item17.Member;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class Item17Test {
    
    @Test
    @DisplayName("불변 클래스에서 가변 객체를 참조하면 안되는 이유")
    void vafiableObject() {
        // given
        Address address = new Address("지번주소1", "도로명주소1", "우편번호1");

        // when
        Member student = new Member("학생1", address);
        address.setJibun("변경");

        // then
        assertThat(student.getAddress().getJibun()).isEqualTo("변경");
    }
    
    @Test
    @DisplayName("다단계 연산이 예측 되는 경우 package-private 가변 동반 클래스")
    void multiLevelOperation() {
        // given
        BigInteger bi = BigInteger.valueOf(7);
        BigInteger pow = BigInteger.valueOf(2);
        BigInteger mod = BigInteger.valueOf(20);

        // when
        BigInteger modPow = bi.modPow(pow, mod); // BigInteger 내부에서 MutableBigInteger 가변 동반 클래스 사용

        // then
        assertThat(modPow.intValue()).isEqualTo(9);
        System.out.println(bi + "^" + pow + " mod " + mod + " is " + modPow);
    }

    @Test
    @DisplayName("다단계 연산들이 예측이 안되는 경우 public 가변 동반 클래스")
    void stringBuilder() {
        // given
        StringBuilder sb = new StringBuilder();

        // when
        StringBuilder result = sb.append(1).append(2).append(3);

        // then
        assertThat(result.toString()).isEqualTo("123");
        result.append(4).append(5);
        assertThat(result.toString()).isEqualTo("12345");
    }

    @Test
    @DisplayName("신뢰할 수 없는 BigInteger")
    void bigIntegerExtends() {
        // given
        BigIntegerExtends bi = new BigIntegerExtends("100");

        // when // then
        assertThat(bi).doesNotHaveSameClassAs(BigInteger.class);
        assertThat(safeInstance(bi).getClass()).hasSameClassAs(BigInteger.class);
    }

    private BigInteger safeInstance(BigInteger bi) {
        return bi.getClass() == BigInteger.class ?
                bi : new BigInteger(bi.toByteArray());
    }
}
