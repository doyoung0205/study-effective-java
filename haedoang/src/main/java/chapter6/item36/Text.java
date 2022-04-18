package chapter6.item36;

/**
 * author : haedoang
 * date : 2022/04/18
 * description :
 */
public class Text {
    public static final int STYLE_BOLD = 1 << 0;
    public static final int STYLE_ITALIC = 1 << 1;
    public static final int STYLE_UNDERLINE = 1 << 2;
    public static final int STYLE_STRIKETHROUGH = 1 << 3;

    private int style;

    public void addStyles(int styles) {
        this.style = styles;
    }

    public int getStyle() {
        return style;
    }
}
