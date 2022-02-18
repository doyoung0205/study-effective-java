package me.doyoung.studyeffectivejava.chapter1.item7.reference;

import org.junit.jupiter.api.Test;

class Strong {
    public static class Referred {
        protected void finalize() {
            System.out.println("안녕히계세요.. 지금까지 ClassStrong 이였습니다.");
        }
    }

    void collect() throws InterruptedException {
        System.out.println("GC 시작");
        System.gc();
        System.out.println("Sleeping");
        Thread.sleep(5000);
    }

    @Test
    void strong() throws InterruptedException {
        System.out.println("강한 참조 객체 생성");
        // 강한 결합, 객체에 대한 모든 참조가 사라진 경우에만 GC 됨 !
        Referred strong = new Referred();
        // 참조 있는 경우에서 GC 시도
        collect();
        System.out.println("참조 제거");
        // 참조가 없는 경우 GC 시도
        strong = null;
        collect();
        System.out.println("종료");
    }

}
