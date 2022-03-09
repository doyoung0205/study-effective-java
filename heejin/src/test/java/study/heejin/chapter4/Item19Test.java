package study.heejin.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter4.item19.Sub;

class Item19Test {

    @Test
    @DisplayName("하위 클래스에서 재정의한 메서드가 하위 클래스의 생성자보다 먼저 호출")
    void subOverrideMethod() {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
