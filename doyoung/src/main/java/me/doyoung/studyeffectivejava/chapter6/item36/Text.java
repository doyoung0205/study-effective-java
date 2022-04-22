package me.doyoung.studyeffectivejava.chapter6.item36;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

// 코드 36-2 EnumSet - 비트 필드를 대체하는 현대적 기법 (224쪽)
public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // 어떤 Set을 넘겨도 되나, EnumSet이 가장 좋다.
    public void applyStyles(Set<Style> styles) {
        System.out.printf("Applying styles %s to text%n",
                Objects.requireNonNull(styles));
    }

    // 사용 예
    public static void main(String[] args) {
        Text text = new Text();
        final EnumSet<Style> styles = EnumSet.of(Style.BOLD, Style.ITALIC);
        text.applyStyles(styles);
    }
}
