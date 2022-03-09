package study.heejin.chapter4.item20.mixin.policy.addtion;

import study.heejin.chapter4.item20.mixin.Money;
import study.heejin.chapter4.item20.mixin.policy.RatePolicy;

/**
 * 부가 요금제 - 비율 할인 정책
 */
public class RateDiscountablePolicy extends AdditionalRatePolicy {
    private Money discountAmount;

    public RateDiscountablePolicy(Money discountAmount, RatePolicy next) {
        super(next);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        System.out.println("부가 요금제 - 비율 할인 정책");
        return fee.minus(discountAmount);
    }
}