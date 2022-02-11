package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item1.CoffeeBean;

import java.math.BigInteger;
import java.util.EnumSet;
import java.util.Random;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class Item1Test {

    @Test
    @DisplayName("정적 팩터리 메서드의 이름으로 반환될 인스턴스의 의미를 파악하기 쉬움")
    void staticFactoryMethod_name() {
        BigInteger bigInteger = new BigInteger(5, 2, new Random());
        System.out.println("bigInteger = " + bigInteger);

        BigInteger primeBigInteger = BigInteger.probablePrime(5, new Random());
        System.out.println("primeBigInteger = " + primeBigInteger);
    }

    @Test
    @DisplayName("인스턴스를 미리 만들어 두고 통제할 수 있음")
    void staticFactoryMethod_controlled_class() {
        Boolean t = Boolean.valueOf(true);
        Boolean f = Boolean.valueOf("false");
    }

    @Test
    @DisplayName("반환 타입의 하위 타입 객체를 반환할 수 있음")
    void staticFactoryMethod_return_type() {
        EnumSet<CoffeeBean> noneCoffeeBeans = EnumSet.noneOf(CoffeeBean.class);
        assertThat(noneCoffeeBeans).hasSize(0);

        EnumSet<CoffeeBean> allCoffeeBeans = EnumSet.allOf(CoffeeBean.class);
        assertThat(allCoffeeBeans).hasSize(4);
    }
}


