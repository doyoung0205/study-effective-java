package me.doyoung.studyeffectivejava.chapter1.item7.reference;

import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import static org.junit.jupiter.api.Assertions.*;


public class ReferenceTest {


    @Test
    void weakReference() {
        // given
        Language origin = new Language("english");
        Language copy = origin;

        WeakReference weekReference = new WeakReference<>(origin); // java.lang.ref.WeakReference@154f730f

        assertNotNull(weekReference);
        assertNotNull(weekReference.get());
        assertEquals(((Language) weekReference.get()).getName(), "english");

        // 할당을 해지하기 위해 해당객체에 null 값 대입
        origin = null;
        copy = null;

        // when
        // GC 강제 수행
        System.gc();

        // then
        assertNull(weekReference.get());
    }

    @Test
    void softReference() {
        // given
        Language origin = new Language("english");
        SoftReference softReference = new SoftReference(origin); // java.lang.ref.WeakReference@154f730f

        assertNotNull(softReference);
        assertNotNull(softReference.get());
        assertEquals(((Language) softReference.get()).getName(), "english");

        // 할당을 해지하기 위해 해당객체에 null 값 대입
        origin = null;

        // when
        // GC 강제 수행
        System.gc();

        // then
        assertNotNull(softReference.get());

        long callCount = 0;
        try {
            while (true) {
                callCount++;
                if (callCount > 2_000) {
                    return;
                }
            }
        } catch (OutOfMemoryError ofm) {
        }
        assertNull(softReference.get());
    }

    @Test
    void strongReference() {
        Language origin = new Language("english");
        Language strongReference = origin; // me.doyoung.studyeffectivejava.chapter1.item7.reference.Language@645372c0

        System.out.println(strongReference == null ? "null" : strongReference); // english 출력

        // 할당을 해지하기 위해 해당객체에 null 값 대입
        origin = null;

        // GC 강제 수행
        System.gc();

        assertNotNull(strongReference);
    }


}

class Language {
    private String name;

    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                '}';
    }
}

