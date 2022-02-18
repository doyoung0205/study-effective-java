package me.doyoung.studyeffectivejava.chapter1.item7;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeakHasMapTest {
    public static class Referred {
        protected void finalize() {
            System.out.println("Good bye cruel world");
        }
    }

    @Test
    void test() throws InterruptedException {
        System.out.println("Creating weak references");
        // This is now a weak reference.
        // The object will be collected only if no strong references.
        Referred strong = new Referred();
        Map<Referred, String> metadata = new WeakHashMap<Referred, String>();
        metadata.put(strong, "WeakHashMap's make my world go around");
        // Attempt to claim a suggested reference.
        collect();
        assertEquals(metadata.size(), 1);
        System.out.println("Still has metadata entry? " + (metadata.size() == 1));
        System.out.println("Removing reference");
        // The object may be collected.
        strong = null;
        collect();
        assertEquals(metadata.size(), 0);
        System.out.println("Still has metadata entry? " + (metadata.size() == 1));
        System.out.println("Done");
    }

    public void collect() throws InterruptedException {
        System.out.println("Suggesting collection");
        System.gc();
        System.out.println("Sleeping");
        Thread.sleep(5000);
    }
}
