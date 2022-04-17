package study.heejin.chapter6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter6.item36.Text;

import java.util.EnumSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class Item36Test {

    @Test
    @DisplayName("EnumSet removeAll 테스트")
    void removeAll() {
        EnumSet<Text.Style> styles = EnumSet.allOf(Text.Style.class);
        styles.removeAll(Set.of(Text.Style.UNDERLINE, Text.Style.STRIKETHROUGH));

        assertThat(styles).hasSize(2);
        styles.forEach(System.out::println);
    }

    @Test
    @DisplayName("EnumSet retainAll 테스트")
    void retainAll() {
        EnumSet<Text.Style> styles = EnumSet.allOf(Text.Style.class);
        styles.retainAll(Set.of(Text.Style.BOLD));

        assertThat(styles).hasSize(1);
        styles.forEach(System.out::println);
    }
}
