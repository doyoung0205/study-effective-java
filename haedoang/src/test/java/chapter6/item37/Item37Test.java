package chapter6.item37;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static chapter6.item37.Plant.LifeCycle;
import static chapter6.item37.Plant.LifeCycle.*;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/04/18
 * description :
 */
public class Item37Test {
    @Test
    @DisplayName("ordinal 인덱싱 사용은 안티패턴이다")
    public void ordinalIndexing() {
        // given
        final Set<Plant>[] plantByLifeCycle = new Set[values().length];
        final Plant[] garden = new Plant[]{
                new Plant("세잎클로버", ANNUAL),
                new Plant("개나리", PERENNIAL),
                new Plant("할미꽃", BIENNIAL)
        };
        // when
        // initial
        for (int i = 0; i < plantByLifeCycle.length; i++) {
            plantByLifeCycle[i] = new HashSet<>();
        }
        // setLifeCycle
        for (Plant p : garden) {
            plantByLifeCycle[p.lifeCycle.ordinal()].add(p); //anti
        }

        // then
        for (int i = 0; i < plantByLifeCycle.length; i++) {
            System.out.printf("%s: %s%n", LifeCycle.values()[i], plantByLifeCycle[i]);
        }
    }

    @Test
    @DisplayName("enumSet을 사용해 데이터와 열거 타입을 연결한 테스트")
    public void enumTypeMapping() {
        // given
        final EnumMap<LifeCycle, Set<Plant>> plantsByLifeCycle =
                new EnumMap<>(LifeCycle.class);

        final Plant[] garden = new Plant[]{
                new Plant("세잎클로버", ANNUAL),
                new Plant("개나리", PERENNIAL),
                new Plant("할미꽃", BIENNIAL)
        };

        // when
        for (LifeCycle lc : LifeCycle.values()) {
            plantsByLifeCycle.put(lc, new HashSet<>());
        }

        for (Plant p : garden) {
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        }

        // then
        System.out.println(plantsByLifeCycle);
    }

    @Disabled
    @Test
    @DisplayName("stream을 사용한 grouping은 존재하지 않은 Enum에 대해서는 만들지 않음에 주의할 것")
    public void useStream() {
        // given
        final Plant[] garden = new Plant[]{
                new Plant("세잎클로버", ANNUAL),
                new Plant("개나리", PERENNIAL),
                new Plant("할미꽃", PERENNIAL)
        };

        // when
        final Map<LifeCycle, List<Plant>> collect = Arrays.stream(garden)
                .collect(Collectors.groupingBy(p -> p.lifeCycle));

        final EnumMap<LifeCycle, Set<Plant>> collect2 = Arrays.stream(garden)
                .collect(Collectors.groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet()));

        // then
        assertThat(collect).hasSize(2);
        assertThat(collect2).hasSize(3)
                .as("expected => 3")
                .as("팀원 질문하기");
    }

    @Test
    @DisplayName("ordinal 안티패턴 transition 테스트")
    public void ordinalTransition() {
        // given
        final Phase.Transition transition = Phase.Transition.from(Phase.SOLID, Phase.LIQUID);

        // then
        assertThat(transition).isEqualTo(Phase.Transition.MELT)
                .as("ordinal을 사용하여 컴파일러는 배열의 인덱스와의 관계를 알지 못함");
    }

    @Test
    @DisplayName("transitionEnumMap 테스트")
    public void smartTransition() {
        // given
        final SmartPhase.SmartTransition actual = SmartPhase.SmartTransition.from(SmartPhase.SOLID, SmartPhase.GAS);

        final SmartPhase.SmartTransition actual2 = SmartPhase.SmartTransition.from(SmartPhase.PLASMA, SmartPhase.GAS);

        // then
        assertThat(actual).isEqualTo(SmartPhase.SmartTransition.SUBLIME);
        assertThat(actual2).isEqualTo(SmartPhase.SmartTransition.DEIONIZE);
    }
}
