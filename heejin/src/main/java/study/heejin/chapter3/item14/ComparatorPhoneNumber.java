package study.heejin.chapter3.item14;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Comparator.comparingInt;

public class ComparatorPhoneNumber implements Comparable<ComparatorPhoneNumber> {
    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    private static final Comparator<ComparatorPhoneNumber> COMPARATOR = comparingInt((ComparatorPhoneNumber pn) -> pn.areaCode)
            .thenComparingInt(pn -> pn.prefix)
            .thenComparingInt(pn -> pn.lineNum);

    public ComparatorPhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNum = (short) lineNum;
    }

    public int compareTo(ComparatorPhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }

    public static ComparatorPhoneNumber randomPhoneNumber() {
        Random rnd = ThreadLocalRandom.current();
        return new ComparatorPhoneNumber((short) rnd.nextInt(1000),
                (short) rnd.nextInt(1000),
                (short) rnd.nextInt(10000));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComparatorPhoneNumber)) {
            return false;
        }
        ComparatorPhoneNumber that = (ComparatorPhoneNumber) o;
        return areaCode == that.areaCode && prefix == that.prefix && lineNum == that.lineNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, prefix, lineNum);
    }
}
