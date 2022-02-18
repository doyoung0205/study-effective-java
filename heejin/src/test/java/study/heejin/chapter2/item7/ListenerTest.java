package study.heejin.chapter2.item7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item7.listener.Listener;
import study.heejin.chapter2.item7.listener.MyListener;

class ListenerTest {

    @Test
    @DisplayName("리스너 테스트")
    void listener() {
        MyListener myListener = new MyListener();

        Listener newListener = new Listener() {
            @Override
            public void onEvent() {
                System.out.println("new Listener");
            }
        };

        myListener.onEvent();
        newListener.onEvent();
    }
}
