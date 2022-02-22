package chapter3.item12;

import chapter3.chapter12.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/19
 * description :
 */
public class ToStringTest {

    @Test
    @Disabled
    @DisplayName("toString()에서 객체 정보를 전부 표시하지 않은 경우 오류 메시지 확인하기")
    public void notDefineToStringAllFields() {
        // given
        final Person person = new Person("adele", 34, "singer");
        final Person notSamePerson = new Person("adele", 34, "actor");

        // then
        assertThat(person).isEqualTo(notSamePerson);
    }

    @Test
    @DisplayName("BigInteger toString 표현방식 확인하기")
    public void toStringTest() {
        // given
        final BigInteger bigInteger = new BigInteger("11");

        System.out.println(String.format("10진수 표기 :%s", bigInteger));
        System.out.println(String.format("10진수 표기 :%s", bigInteger.toString(2)));
        System.out.println(String.format("8진수 표기 :%s", bigInteger.toString(8))); //8진수표기
        System.out.println(String.format("16진수 표기 :%s", bigInteger.toString(16))); //16진수표기

    }
}

