package me.doyoung.studyeffectivejava.chapter8;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static me.doyoung.studyeffectivejava.chapter8.item52.CollectionClassifier.classify;
import static org.assertj.core.api.Assertions.assertThat;

class CollectionClassifierTest {

    @Test
    void classifyTest() {

        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> collection : collections) {
            assertThat(classify(collection)).isEqualTo("그 외");
        }

    }

    @Test
    void name() {
        final List<String> stringList = Collections.emptyList();

        stringList.add("");
    }
}
