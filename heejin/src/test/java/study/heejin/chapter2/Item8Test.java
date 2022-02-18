package study.heejin.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter2.item8.Adult;
import study.heejin.chapter2.item8.FakeAccountOperations;
import study.heejin.chapter2.item8.Teenager;

class Item8Test {

    @Test
    @DisplayName("방 청소 테스트")
    void cleanRoom() {
        // given
        Adult adult = new Adult();
        Teenager teenager = new Teenager();

        // when
        adult.cleanRoom(7);
        teenager.cleanRoom(99);
        // System.gc(); // Teenager 에서 Cleaner를 동작하게 하려면 추가
    }

    @Test
    @DisplayName("finalizer를 통해 허가되지 않은 메서드 호출하기")
    void finalizerAttack() {
        try {
            FakeAccountOperations fakeAccountOperations = new FakeAccountOperations();

        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

        System.gc();
    }
}
