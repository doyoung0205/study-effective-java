package study.heejin.chapter3.item13;

import study.heejin.chapter3.item10.ColorPointComposition;

import java.util.Objects;

public class CloneableExample implements Cloneable {

    private final int value;

    public CloneableExample(int value) {
        this.value = value;
    }

    @Override
    public CloneableExample clone() {
        try {
            return (CloneableExample) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CloneableExample)) {
            return false;
        }
        CloneableExample that = (CloneableExample) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
