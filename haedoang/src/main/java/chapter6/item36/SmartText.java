package chapter6.item36;

import java.util.HashSet;
import java.util.Set;

/**
 * author : haedoang
 * date : 2022/04/18
 * description :
 */
public class SmartText {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    private Set<Style> styles;

    public void applyStyles(Set<Style> styles) {
        this.styles = new HashSet<>(styles);
    }

    public Set<Style> getStyles() {
        return styles;
    }
}
