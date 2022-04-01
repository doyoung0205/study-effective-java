package me.doyoung.studyeffectivejava.chapter3.item19;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ParentTest {

    @Test
    void koreaPerson() {
        final Person dory = new KoreaPerson(LocalDate.of(1995, 2, 5), "DORY");
        System.out.println(dory.getAge());
    }
}
