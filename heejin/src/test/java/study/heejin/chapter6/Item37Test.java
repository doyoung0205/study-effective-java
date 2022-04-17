package study.heejin.chapter6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.heejin.chapter6.item37.Plant;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class Item37Test {

    @Test
    @DisplayName("스트림을 사용하면 EnumMap을 사용했을 때와 살짝 다르게 동작한다.")
    void steamEnumMap() {
        Plant[] garden = {
                new Plant("바질", Plant.LifeCycle.ANNUAL),
                new Plant("딜", Plant.LifeCycle.ANNUAL),
                new Plant("라벤더", Plant.LifeCycle.PERENNIAL),
                new Plant("로즈마리", Plant.LifeCycle.PERENNIAL)
        };

        Plant plant = new Plant();
        Set<Plant>[] result1 = plant.groupByOrdinalArray(garden);
        EnumMap<Plant.LifeCycle, Set<Plant>> result2 = plant.groupByEnumMap(garden);
        Map<Plant.LifeCycle, List<Plant>> result3 = plant.groupByStream(garden);
        EnumMap<Plant.LifeCycle, Set<Plant>> result4 = plant.groupByStreamEnumMap(garden);

        assertThat(result1).hasSize(3);
        assertThat(result2).hasSize(3);
        assertThat(result3).as("Steam을 사용한 Map").hasSize(2);
        assertThat(result4).as("Stream을 사용한 EnumMap").hasSize(2);
    }
}
