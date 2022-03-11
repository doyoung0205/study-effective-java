package study.heejin.chapter4.item19;

import java.util.Arrays;

public final class Flower extends Plant {

    private String language;

    public Flower(String name, String seed, String language) {
        super(name, seed);
        this.language = language;
    }

    @Override
    protected String season(String family) {
        return Arrays.stream(Family.values())
                .filter(f -> f.getName().equals(family))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .getSeason();
    }
}
