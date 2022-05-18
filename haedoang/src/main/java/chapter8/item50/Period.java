package chapter8.item50;

import java.util.Date;

/**
 * packageName : chapter8.item50
 * fileName : Period
 * author : haedoang
 * date : 2022-05-18
 * description :
 */
public class Period {
    private final Date start;
    private final Date end;

    private Period(Date start, Date end) {
        assert start.before(end);
        this.start = start;
        this.end = end;
    }

    public static Period valueOf(Date start, Date end) {
        return new Period(start, end);
    }

    public static Period defensiveCopyValueOf(Date start, Date end) {
        return new Period(new Date(start.getTime()), new Date(end.getTime()));
    }

    @Deprecated
    public Date getStart() {
        return start;
    }

    @Deprecated
    public Date getEnd() {
        return end;
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }
}
