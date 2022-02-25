package me.doyoung.studyeffectivejava.chapter2.item14;

import org.junit.jupiter.api.Test;

import java.util.NavigableSet;
import java.util.TreeSet;

class PhoneNumberTest {
    @Test
    void phoneNumberWithCompare() {
        final Runnable runnable = () -> {
            NavigableSet<PhoneNumberWithCompare> s = new TreeSet<>();
            for (int i = 0; i < 1_000_000; i++)
                s.add(PhoneNumberWithCompare.randomPhoneNumber());
        };
        System.out.print("phoneNumberWithCompare :: ");
        checkSpeed(runnable);

        // COMPARATOR:1244 -> 느리지만 읽기쉬움
        // COMPARE TO:1065 -> 이게 더 빠름
    }

    @Test
    void phoneNumberWithComparator() {
        final Runnable runnable = () -> {
            NavigableSet<PhoneNumberWithComparator> s = new TreeSet<>();
            for (int i = 0; i < 1_000_000; i++)
                s.add(PhoneNumberWithComparator.randomPhoneNumber());
        };
        System.out.print("PhoneNumberWithComparator :: ");
        checkSpeed(runnable);
    }

    private void checkSpeed(Runnable runnable) {
        long st = System.currentTimeMillis();
        runnable.run();
        long et = System.currentTimeMillis();
        System.out.println((et - st) + " ms");
    }
}
