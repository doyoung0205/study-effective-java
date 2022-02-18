package study.heejin.chapter2.item5.factorymethod;

public enum TilePattern {
    BACKGROUND("B"),
    GEOMETRY("G"),
    FLOWER("F"),
    NORDIC("N"),
    TRADITIONAL("T");

    private final String symbol;

    TilePattern(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
