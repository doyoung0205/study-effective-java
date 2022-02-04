package chapter2.item2;

import java.util.Objects;

/**
 * packageName : chapter2.item2
 * fileName : BAWIPASTABAR
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
public class NewYorkPasta extends Pasta {
    public enum Bread { 바게트, 마늘빵 }
    private final Bread bread;

    public NewYorkPasta(Builder builder) {
        super(builder);
        this.bread = builder.bread;
    }

    public static class Builder extends Pasta.Builder<Builder> {
        private final Bread bread;

        public Builder(Bread bread) {
            this.bread = Objects.requireNonNull(bread);
        }

        @Override
        NewYorkPasta build() {
            return new NewYorkPasta(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public boolean equalBread(Bread bread) {
        return bread == this.bread;
    }
}
