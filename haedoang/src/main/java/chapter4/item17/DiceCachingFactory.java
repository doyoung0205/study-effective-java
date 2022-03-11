package chapter4.item17;

import java.util.ArrayList;
import java.util.List;

/**
 * author : haedoang
 * date : 2022/03/09
 * description :
 */
public class DiceCachingFactory {
    private static final List<Dice> dices;

    static {
        dices = new ArrayList<>();
    }

    private DiceCachingFactory() {
    }

    public static boolean isExist(int eye) {
        return dices.stream().anyMatch(dice -> dice.getEye() == eye);
    }

    public static Dice getDice(int eye) {
        return dices.stream()
                .filter(it -> it.getEye() == eye)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public static void addDice(Dice dice) {
        if (dices.contains(dice)) {
            throw new IllegalStateException();
        }
        dices.add(dice);
    }
}
