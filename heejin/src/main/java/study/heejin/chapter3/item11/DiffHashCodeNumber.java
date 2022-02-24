package study.heejin.chapter3.item11;

import java.util.Objects;

public class DiffHashCodeNumber {
    private final int number;

    public DiffHashCodeNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiffHashCodeNumber that = (DiffHashCodeNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
