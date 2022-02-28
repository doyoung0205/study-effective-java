package study.heejin.chapter3;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter3.item12.StackOverflowError;

import java.math.BigInteger;
import java.util.*;

class Item12Test {

    @Test
    @DisplayName("BigInteger의 toString")
    void bigInteger() {
        // given
        BigInteger bigInteger = new BigInteger("321456");
        int radix = 2;

        // when // then
        System.out.println(
                "Binary String of BigInteger " + bigInteger + " is equal to " + bigInteger.toString(radix)
        );
    }

    @Test
    @DisplayName("AbstractMap의 하위 클래스 toString")
    void hashMap() {
        // given
        AbstractCollection<Integer> abstractCollection = new ArrayList<>();
        abstractCollection.add(1);
        abstractCollection.add(2);
        abstractCollection.add(3);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // when // then
        System.out.println(
                "abstractCollection toString : " + abstractCollection.toString()
                + ", list toString : " + list.toString()
        );
    }

    @Test
    @Disabled
    @DisplayName("toString 순환 참조")
    void stackOverflowError() {
        // given
        StackOverflowError.A aClass = new StackOverflowError.A();

        // when // then
        System.out.println(
                aClass.toString()
        );

    }
}
