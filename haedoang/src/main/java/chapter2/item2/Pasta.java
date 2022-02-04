package chapter2.item2;

import java.util.EnumSet;
import java.util.Set;

/**
 * packageName : chapter2.item2
 * fileName : Pasta
 * author : haedoang
 * date : 2022/02/05
 * description :
 */
public abstract class Pasta {
    public enum Noodle {
        라비올리, 라자냐, 마카로니, 스파게티, 리가토니, 펜네, 푸실리, 쿠스쿠스, 뇨키
    }

    private Set<Noodle> noodles;

    public Pasta(Builder builder) {
        this.noodles = builder.noodles;
    }

    abstract static class Builder<T> {
        private final EnumSet<Noodle> noodles = EnumSet.noneOf(Noodle.class);

        public T addNoodle(Noodle noodle) {
            this.noodles.add(noodle);
            return self();
        }
        abstract Pasta build();

        protected abstract T self();
    }

    public int noodleSize() {
        return this.noodles.size();
    }
}
