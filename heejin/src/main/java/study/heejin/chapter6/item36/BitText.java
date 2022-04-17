package study.heejin.chapter6.item36;

import java.util.Objects;

public class BitText {
    public static final int STYLE_BOLD = 1 << 0;            // 1
    public static final int STYLE_ITALIC = 1 << 1;          // 2
    public static final int STYLE_UNDERLINE = 1 << 2;       // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3;   // 8

    public void applyStyles(int styles) {
        System.out.printf("Applying styles %s to text %n", Objects.requireNonNull(styles));
    }

    public static void main(String[] args) {
        BitText text = new BitText();
        text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
    }
}
