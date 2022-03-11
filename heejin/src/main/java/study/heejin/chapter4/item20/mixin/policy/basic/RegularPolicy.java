package study.heejin.chapter4.item20.mixin.policy.basic;

import study.heejin.chapter4.item20.mixin.Call;
import study.heejin.chapter4.item20.mixin.Money;

import java.time.Duration;

/**
 * 기본 요금제 - 표준 요금 정책
 */
public class RegularPolicy extends BasicRatePolicy {
    private Money amount;
    private Duration seconds;

    public RegularPolicy(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        System.out.println("기본 요금제 - 표준 요금 정책");
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}
