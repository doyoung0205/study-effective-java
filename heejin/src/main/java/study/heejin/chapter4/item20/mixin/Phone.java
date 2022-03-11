package study.heejin.chapter4.item20.mixin;

import study.heejin.chapter4.item20.mixin.policy.RatePolicy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Phone {
    private RatePolicy ratePolicy;
    private List<Call> calls = new ArrayList<>();

    public Phone(RatePolicy ratePolicy) {
        this.ratePolicy = ratePolicy;
    }

    public List<Call> getCalls() {
        return Collections.unmodifiableList(calls);
    }

    public void setCall(Call call) {
        this.calls.add(call);
    }

    public Money calculateFee() {
        return ratePolicy.calculateFee(this);
    }
}
