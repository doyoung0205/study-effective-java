package me.doyoung.studyeffectivejava.chapter3.item24.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClazzTest {

    @Test
    void test() {
        final Clazz clazz = new Clazz();
        Clazz.PublicInner publicInner = clazz.new PublicInner();
        publicInner = null;
        assertThat(clazz).isNotNull();
    }
}
