package study.heejin.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter4.item20.IntArrays;
import study.heejin.chapter4.item20.mixin.Call;
import study.heejin.chapter4.item20.mixin.Money;
import study.heejin.chapter4.item20.mixin.Phone;
import study.heejin.chapter4.item20.mixin.policy.addtion.RateDiscountablePolicy;
import study.heejin.chapter4.item20.mixin.policy.addtion.TaxablePolicy;
import study.heejin.chapter4.item20.mixin.policy.basic.NightlyDiscountPolicy;
import study.heejin.chapter4.item20.mixin.policy.basic.RegularPolicy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

class Item20Test {

    @Test
    @DisplayName("믹스인")
    void mixin() {
        Money basicFee = Money.wons(100);
        Money nightFee = Money.wons(50);
        LocalDateTime from = LocalDateTime.of(2022, 3, 9, 22, 30, 00);
        LocalDateTime to = LocalDateTime.of(2022, 3, 9, 23, 30, 00);
        System.out.println("통화 시간: " + Duration.between(from, to).getSeconds() / 60 + "분");

        RegularPolicy regularPolicy = new RegularPolicy(basicFee, Duration.ofSeconds(60));
        NightlyDiscountPolicy nightlyDiscountPolicy = new NightlyDiscountPolicy(nightFee, basicFee, Duration.ofSeconds(60));

        TaxablePolicy taxablePolicy = new TaxablePolicy(1.2, regularPolicy);
        RateDiscountablePolicy rateDiscountablePolicy = new RateDiscountablePolicy(Money.wons(10_000), regularPolicy);
        TaxablePolicy taxableAndrateDiscountablePolicy = new TaxablePolicy(2, rateDiscountablePolicy);

        System.out.println("---------- 표준 요금제 ---------");
        Phone phone1 = new Phone(regularPolicy);
        phone1.setCall(new Call(from, to));
        System.out.println("요금: " + phone1.calculateFee());

        System.out.println("---------- 심야 할인 요금제 ---------");
        Phone phone2 = new Phone(nightlyDiscountPolicy);
        phone2.setCall(new Call(from, to));
        System.out.println("요금: " + phone2.calculateFee());

        System.out.println("---------- 부가 요금 정책(세금) ---------");
        Phone phone3 = new Phone(taxablePolicy);
        phone3.setCall(new Call(from, to));
        System.out.println("요금: " + phone3.calculateFee());

        System.out.println("--------- 부가 요금 정책(비율 할인) ----------");
        Phone phone4 = new Phone(rateDiscountablePolicy);
        phone4.setCall(new Call(from, to));
        System.out.println("요금: " + phone4.calculateFee());

        System.out.println("--------- 부가 요금 정책(세금, 비율 할인) ----------");
        Phone phone5 = new Phone(taxableAndrateDiscountablePolicy);
        phone5.setCall(new Call(from, to));
        System.out.println("요금: " + phone5.calculateFee());
    }

    @Test
    @DisplayName("골격 구현 - 템플릿 메서드")
    void intArrayAsList() {
        // given
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        // when
        List<Integer> list = IntArrays.intArrayAsList(a);
        Collections.shuffle(list);

        // then
        System.out.println(list);
    }
}
