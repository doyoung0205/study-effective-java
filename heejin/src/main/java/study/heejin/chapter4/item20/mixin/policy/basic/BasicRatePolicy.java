package study.heejin.chapter4.item20.mixin.policy.basic;

import study.heejin.chapter4.item20.mixin.Call;
import study.heejin.chapter4.item20.mixin.Money;
import study.heejin.chapter4.item20.mixin.Phone;
import study.heejin.chapter4.item20.mixin.policy.RatePolicy;

/**
 * 기본 요금제 정책
 */
public abstract class BasicRatePolicy implements RatePolicy {

    @Override
    public Money calculateFee(Phone phone) {
        Money result = Money.ZERO;

        for(Call call : phone.getCalls()) {
            result = result.plus(calculateCallFee(call));
        }

        return result;
    }

    protected abstract Money calculateCallFee(Call call);
}
