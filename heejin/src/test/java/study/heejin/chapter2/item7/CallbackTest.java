package study.heejin.chapter2.item7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item7.callback.Callback;
import study.heejin.chapter2.item7.callback.Caller;

class CallbackTest {

    @Test
    @DisplayName("콜백 테스트")
    void callback() {
        Caller caller = new Caller();

        Caller newCaller = new Caller(new Callback() {
            @Override
            public void callbackMethod() {
                System.out.println("new Callback");
            }
        });

        caller.callbackMethod();
        newCaller.callbackMethod();
    }
}
