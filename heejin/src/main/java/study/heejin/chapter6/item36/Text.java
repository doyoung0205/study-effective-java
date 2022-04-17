package study.heejin.chapter6.item36;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import static study.heejin.chapter6.item36.Text.Style.BOLD;
import static study.heejin.chapter6.item36.Text.Style.ITALIC;

public class Text {
    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }

    public void applyStyles(Set<Style> styles) {
        System.out.printf("Applying styles %s to text %n", Objects.requireNonNull(styles));
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(EnumSet.of(BOLD, ITALIC));
    }
}
