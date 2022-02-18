package me.doyoung.studyeffectivejava.chapter1.item7.reference;

import java.lang.ref.WeakReference;

public class Weak {
    public static class Referred {
        protected void finalize() {
            System.out.println("안녕히계세요.. 지금까지 ClassWeek 이였습니다.");
        }
    }

    public static void collect() throws InterruptedException {
        System.out.println("GC 시작");
        System.gc();
        System.out.println("Sleeping");
        Thread.sleep(5000);
    }

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Creating weak references");
        // This is now a weak reference.
        // The object will be collected only if no strong references.
        Referred strong = new Referred();
        WeakReference<Referred> weak = new WeakReference<Referred>(strong);

        // Attempt to claim a suggested reference.
        Weak.collect();
        System.out.println("Removing reference");
        // The object may be collected.
        strong = null;
        Weak.collect();
        System.out.println("Done");

    }
}
