package study.heejin.chapter2.item5.factorymethod;

public class WhiteTile extends Tile {

    public static class Builder extends Tile.Builder<Builder> {

        @Override
        public WhiteTile build() {
            return new WhiteTile(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    WhiteTile(Builder builder) {
        super(builder);
    }
}
