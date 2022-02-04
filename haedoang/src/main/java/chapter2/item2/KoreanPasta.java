package chapter2.item2;

import java.util.Objects;

/**
 * packageName : chapter2.item2
 * fileName : KoreanPasta
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
public class KoreanPasta extends Pasta {
    public enum Size { 보통, 곱배기 }
    private final Size size;

    public KoreanPasta(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    public static class Builder extends Pasta.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        KoreanPasta build() {
            return new KoreanPasta(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public boolean equalSize(Size size) {
        return this.size == size;
    }
}
