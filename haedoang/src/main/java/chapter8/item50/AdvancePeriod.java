package chapter8.item50;

import java.time.LocalDateTime;

/**
 * packageName : chapter8.item50
 * fileName : AdvancePeriod
 * author : haedoang
 * date : 2022-05-18
 * description :
 */
public class AdvancePeriod {
    private final LocalDateTime start;
    private final LocalDateTime end;

    private AdvancePeriod(LocalDateTime start, LocalDateTime end) {
        assert start.isBefore(end);
        this.start = start;
        this.end = end;
    }

    public static AdvancePeriod valueOf(LocalDateTime start, LocalDateTime end) {
        return new AdvancePeriod(start, end);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
