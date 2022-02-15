package me.doyoung.studyeffectivejava.chapter1.item7.reference;

import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class Soft {
    public static class Referred {
        protected void finalize() {
            System.out.println("안녕히계세요.. 지금까지 ClassSoft 이였습니다.");
        }
    }

    void collect() throws InterruptedException {
        System.out.println("GC 시작");
        System.gc();
        System.out.println("Sleeping");
        Thread.sleep(5000);
    }

    @Test
    void soft() throws InterruptedException {
        // 강한 참조가 없고 JVM에 메모리가 필요한 경우 수집됨.
        Referred strong = new Referred();
        SoftReference<Referred> soft = new SoftReference<Referred>(strong);

        // GC
        collect();

        System.out.println("Removing reference");
        // 수집되지 않을 가능성이 매우 높음
        strong = null;
        collect();
        System.out.println("Consuming heap");
        try {
            // 힙에 많은 개체 생성
            List<Soft> heap = new ArrayList<>(100000);
            while (true) {
                heap.add(new Soft());
            }
        } catch (OutOfMemoryError e) {
            // OutOfMemoryError 발생 전 GC 발생
            System.out.println("Out of memory error raised");
        }
        System.out.println("Done");
    }
}
