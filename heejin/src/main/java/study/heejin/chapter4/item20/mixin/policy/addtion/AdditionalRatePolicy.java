package study.heejin.chapter4.item20.mixin.policy.addtion;

import study.heejin.chapter4.item20.mixin.Money;
import study.heejin.chapter4.item20.mixin.Phone;
import study.heejin.chapter4.item20.mixin.policy.RatePolicy;

/**
 * 믹스인(mixin)
 * 부가 요금제 정책
 * - 부가 정책은 항상 기본 정책의 처리가 완료된 후에 실행돼야 한다.
 */
public abstract class AdditionalRatePolicy implements RatePolicy {
    private RatePolicy next;

    public AdditionalRatePolicy(RatePolicy next) {
        this.next = next;
    }

    @Override
    public Money calculateFee(Phone phone) {
        Money fee = next.calculateFee(phone);
        return afterCalculated(fee);
    }

    abstract protected Money afterCalculated(Money fee);
}