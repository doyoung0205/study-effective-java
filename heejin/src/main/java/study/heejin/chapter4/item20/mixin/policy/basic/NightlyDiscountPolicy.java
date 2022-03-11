package study.heejin.chapter4.item20.mixin.policy.basic;

import study.heejin.chapter4.item20.mixin.Call;
import study.heejin.chapter4.item20.mixin.Money;

import java.time.Duration;

/**
 * 기본 요금제 - 심야 할인 정책
 */
public class NightlyDiscountPolicy extends BasicRatePolicy {
    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightlyAmount;
    private Money regularAmount;
    private Duration seconds;

    public NightlyDiscountPolicy(Money nightlyAmount, Money regularAmount, Duration seconds) {
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        System.out.println("기본 요금제 - 심야 할인 정책");

        if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
            return nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
        }

        return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}
