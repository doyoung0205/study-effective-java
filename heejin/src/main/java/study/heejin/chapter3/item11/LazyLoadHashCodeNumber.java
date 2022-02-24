package study.heejin.chapter3.item11;

public class LazyLoadHashCodeNumber {
    private final int number;
    private final int number2;

    private int hashCode;

    public LazyLoadHashCodeNumber(int number, int number2) {
        this.number = number;
        this.number2 = number2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LazyLoadHashCodeNumber that = (LazyLoadHashCodeNumber) o;
        return number == that.number && number2 == that.number2;
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Integer.hashCode(number);
            result = 31 * result + Integer.hashCode(number2);
            hashCode = result;
        }
        return result;
    }
}
