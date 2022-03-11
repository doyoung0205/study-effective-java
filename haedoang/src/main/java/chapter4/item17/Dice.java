package chapter4.item17;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/03/09
 * description :
 */
public class Dice {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 6;

    private final int eye;

    public Dice(int eye) {
        if (eye < MIN_VALUE || eye > MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        this.eye = eye;
    }

    public static Dice valueOf(int eye) {
        if (DiceCachingFactory.isExist(eye)) {
            return DiceCachingFactory.getDice(eye);
        }
        final Dice dice = new Dice(eye);
        DiceCachingFactory.addDice(dice);
        return dice;
    }


    public int getEye() {
        return eye;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dice dice = (Dice) o;
        return eye == dice.eye;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eye);
    }
}
