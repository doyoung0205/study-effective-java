package study.heejin.chapter2.item5.factorymethod;

public class YellowTile extends Tile {

    public static class Builder extends Tile.Builder<Builder> {

        @Override
        public YellowTile build() {
            return new YellowTile(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    YellowTile(Builder builder) {
        super(builder);
    }
}
