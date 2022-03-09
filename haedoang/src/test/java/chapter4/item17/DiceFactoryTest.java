package chapter4.item17;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * author : haedoang
 * date : 2022/03/09
 * description :
 */
class DiceFactoryTest {

    @Test
    @DisplayName("DiceFactory를 사용한 객체 캐싱")
    public void useDiceCaching() {
        // given
        final DiceFactory diceFactory = new DiceFactory();

        // when
        final Dice diceObj1 = diceFactory.create(1);
        final Dice diceObj2 = diceFactory.create(1);

        // then
        assertThat(diceObj1).isEqualTo(diceObj2);
        assertThat(diceObj1).isSameAs(diceObj2);
    }

    @Test
    @DisplayName("DiceCachingWithStaticMethod")
    public void useDiceCaching2() {
        // given
        final Dice diceObj1 = Dice.valueOf(1);
        final Dice diceObj2 = Dice.valueOf(1);

        // then
        assertThat(diceObj1).isEqualTo(diceObj2);
        assertThat(diceObj1).isSameAs(diceObj2);
    }

}