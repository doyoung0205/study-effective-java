package study.heejin.chapter3.item14;

import java.util.Objects;

public class ComparableString implements Comparable<ComparableString> {
    private final String s;

    public ComparableString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public int compareTo(ComparableString cs) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cs.s);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ComparableString) {
            return s.equalsIgnoreCase(((ComparableString) o).s);
        }
        if (o instanceof String) {
            return s.equalsIgnoreCase((String) o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(s);
    }
}
