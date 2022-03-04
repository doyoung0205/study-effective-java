package me.doyoung.studyeffectivejava.chapter3.item18;

import lombok.extern.slf4j.Slf4j;
import me.doyoung.studyeffectivejava.chapter3.item18.event.NumberPrint;
import me.doyoung.studyeffectivejava.chapter3.item18.event.NumberPrintEventCounterWithComposition;
import me.doyoung.studyeffectivejava.chapter3.item18.event.NumberPrintEventCounterWithInheritance;
import me.doyoung.studyeffectivejava.chapter3.item18.event.NumberPrintListener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SelfTest {

    @Test
    void composition() {
        // given
        final NumberPrintListener listener = new NumberPrintListener(); // 이벤트 리스너 생성
        final NumberPrintEventCounterWithComposition wrapper =
                new NumberPrintEventCounterWithComposition(new NumberPrint(1), listener);// 래퍼 클래스 생성

        // when
        listener.publish(); // 이벤트 발행

        // then
        assertEquals(listener.getEventSize(), 1);
        assertEquals(wrapper.getPrintCount(), 0);
    }

    @Test
    void inherit() {
        // given
        final NumberPrintListener listener = new NumberPrintListener(); // 이벤트 리스너 생성
        final NumberPrintEventCounterWithInheritance wrapper =
                new NumberPrintEventCounterWithInheritance(new NumberPrint(1), listener);// 래퍼 클래스 생성

        // when
        listener.publish(); // 이벤트 발행

        // then
        assertEquals(listener.getEventSize(), 1);
        assertEquals(wrapper.getPrintCount(), 1);
    }
}
