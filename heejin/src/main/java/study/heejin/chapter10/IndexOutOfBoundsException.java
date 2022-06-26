package study.heejin.chapter10;

public class IndexOutOfBoundsException extends RuntimeException {
    private final int lowerBound;
    private final int upperBound;
    private final int index;

    public IndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
        super(String.format("최솟값: %d, 최댓값: %d, 인덱스: %d", lowerBound, upperBound, index));
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }
}
