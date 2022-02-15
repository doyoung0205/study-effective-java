package me.doyoung.studyeffectivejava.chapter1.item8;

import org.junit.jupiter.api.Test;

class RoomTest {
    @Test
    void adult() {
        try (Room myRoom = new Room(7)) {
            System.out.println("안녕~");
        }
    }

    @Test
    void teenager() {
        new Room(99);
        System.out.println("Peace out");

        // 다음 줄의 주석을 해제한 후 동작을 다시 확인해보자.
        // 단, 가비지 컬렉러를 강제로 호출하는 이런 방식에 의존해서는 절대 안 된다!
//      System.gc();
    }
}
