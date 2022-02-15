package me.doyoung.studyeffectivejava.chapter1.item7.reference;

import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Phantom {
    public static class Referred {
        // finalize 메서드가 있는 경우 phantomReference 는 ReferenceQueue 에 추가되지 않습니다.
    }

    @Test
    void phantom() throws InterruptedException {
        // 정리해야 할 리소스를 찾는 데 사용할 수 있는 샘플
        Map<Reference, String> cleanUpMap = new HashMap<>();

        // 강력한 참조가 없는 경우에만 개체가 수집됨.
        Referred strong = new Referred();
        ReferenceQueue dead = new ReferenceQueue();
        PhantomReference<Referred> phantom = new PhantomReference(strong, dead);
        cleanUpMap.put(phantom, "리소스정리");

        strong = null;

        // GC
        collect();

        // 확인
        Reference reference = dead.poll();

        assertNotNull(reference);
        assertEquals(cleanUpMap.remove(reference), "리소스정리");

        reference.clear();

    }


    public void collect() throws InterruptedException {
        System.out.println("GC 시작");
        System.gc();
        System.out.println("Sleeping");
        Thread.sleep(5000);
    }

}
