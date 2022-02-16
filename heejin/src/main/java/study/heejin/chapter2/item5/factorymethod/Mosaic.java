package study.heejin.chapter2.item5.factorymethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Mosaic {
    private final int size;
    private final List<Supplier<? extends Tile>> mosaic = new ArrayList<>();

    public Mosaic(int size) {
        this.size = size;
    }

    public void add(Supplier<? extends Tile> tileFactory) {
        mosaic.add(tileFactory);
    }

    public int getSize() {
        return size;
    }

    public List<Supplier<? extends Tile>> getMosaic() {
        return mosaic;
    }
}
