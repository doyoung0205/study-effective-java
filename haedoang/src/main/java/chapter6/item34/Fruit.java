package chapter6.item34;

import java.util.Arrays;

/**
 * author : haedoang
 * date : 2022/04/15
 * description :
 */
public enum Fruit {
    APPLE(0),
    PEACH(1),
    PINEAPPLE(2),
    WATERMELON(3),
    ORANGE(4),
    GRAPE(5);

    private int rank;

    Fruit(int rank) {
        this.rank = rank;
    }

    public static Fruit myFavoriteFruit() {
        return Arrays.stream(Fruit.values())
                .filter(it -> it.rank == 0)
                .findFirst()
                .orElseThrow(AssertionError::new);
    }
}
