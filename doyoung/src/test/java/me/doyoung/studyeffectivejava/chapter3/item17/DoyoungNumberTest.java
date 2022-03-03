package me.doyoung.studyeffectivejava.chapter3.item17;

import me.doyoung.studyeffectivejava.utils.SpeedCheckUtils;
import org.junit.jupiter.api.Test;

class DoyoungNumberTest {

    @Test
    void cache() {
        SpeedCheckUtils.check(() -> {
            final DoyoungNumber number = DoyoungNumber.valueOf(1);
            number.add(3); // 3
            number.add(3); // 3
            number.add(3); // 3
            // 3006
        });
    }
}
