package study.heejin.chapter4.item20.mixin.policy;

import study.heejin.chapter4.item20.mixin.Money;
import study.heejin.chapter4.item20.mixin.Phone;

public interface RatePolicy {

    Money calculateFee(Phone phone);

}
