package chapter4.item17;

import java.util.ArrayList;
import java.util.List;

/**
 * author : haedoang
 * date : 2022/03/09
 * description :
 */
public class DiceFactory {
    private final List<Dice> dices;

    public DiceFactory() {
        this.dices = new ArrayList<>();
    }

    public Dice create(int eye) {
        return dices.stream().filter(it -> it.getEye() == eye)
                .findFirst()
                .orElseGet(() ->  {
                    final Dice createdDice = new Dice(eye);
                    dices.add(createdDice);
                    return createdDice;
                });
    }
}
