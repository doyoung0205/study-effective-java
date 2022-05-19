package study.heejin.chapter8.item50;

import java.util.Date;

public class MutablePeriod {
    private final Date start;
    private final Date end;

    public MutablePeriod(Date start, Date end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
        }
        this.start = start;
        this.end = end;
    }

    public Date start() {
        return start;
    }

    public Date end() {
        return end;
    }

    public String toString() {
        return start + " - " + end;
    }
}
