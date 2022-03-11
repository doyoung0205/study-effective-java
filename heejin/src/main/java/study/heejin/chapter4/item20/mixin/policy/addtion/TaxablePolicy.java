package study.heejin.chapter4.item20.mixin.policy.addtion;

import study.heejin.chapter4.item20.mixin.Money;
import study.heejin.chapter4.item20.mixin.policy.RatePolicy;

/**
 * 부가 요금제 - 세금 정책
 */
public class TaxablePolicy extends AdditionalRatePolicy {
    private double taxRatio;

    public TaxablePolicy(double taxRatio, RatePolicy next) {
        super(next);
        this.taxRatio = taxRatio;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        System.out.println("부가 요금제 - 세금 정책");
        return fee.plus(fee.times(taxRatio));
    }
}
