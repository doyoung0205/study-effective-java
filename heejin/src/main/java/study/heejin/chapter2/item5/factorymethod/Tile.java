package study.heejin.chapter2.item5.factorymethod;

public abstract class Tile {
    final TilePattern tilePattern;

    protected abstract static class Builder<T extends Builder<T>> {
        private TilePattern tilePattern;

        public T addTile(TilePattern tilePattern) {
            this.tilePattern = tilePattern;
            return self();
        }

        public abstract Tile build();

        protected abstract T self();
    }

    Tile(Builder<?> builder) {
        tilePattern = builder.tilePattern;
    }

    public String getTilePattern() {
        return tilePattern.getSymbol();
    }
}
