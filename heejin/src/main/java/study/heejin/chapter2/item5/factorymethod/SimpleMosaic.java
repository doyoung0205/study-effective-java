package study.heejin.chapter2.item5.factorymethod;

import java.util.function.Supplier;

public class SimpleMosaic {

    private final Supplier<? extends Tile> simple;

    public SimpleMosaic(Supplier<? extends Tile> simple) {
        this.simple = simple;
    }

    public String getTilePattern() {
        return simple.get().getTilePattern();
    }
}
