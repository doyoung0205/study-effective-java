package chapter9.item65;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/06/05
 * description :
 */
public class Item65Test {
    @Test
    @DisplayName("리플렉션을 사용한 Set instance 생성")
    public void createSetWithReflection() {
        // when
        final Set<String> actual = CollectionUtil.createSetInstance("java.util.TreeSet", "A", "D", "C", "E", "B");
        final Set<String> actual2 = CollectionUtil.createSetInstance("java.util.LinkedHashSet", "A", "D", "C", "E", "B");

        // then
        assertThat(actual).containsExactly("A", "B", "C", "D", "E");
        assertThat(actual2).containsExactly("A", "D", "C", "E", "B");
    }
}
