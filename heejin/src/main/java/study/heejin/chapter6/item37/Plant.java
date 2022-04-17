package study.heejin.chapter6.item37;

import java.util.*;
import java.util.stream.Collectors;

public class Plant {
    public enum LifeCycle {
        ANNUAL, PERENNIAL, BIENNIAL
    }

    private String name;
    private LifeCycle lifeCycle;

    public Plant() {
    }

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = {
//                new Plant("바질", LifeCycle.ANNUAL),
                new Plant("캐러웨이", LifeCycle.BIENNIAL),
//                new Plant("딜", LifeCycle.ANNUAL),
                new Plant("라벤더", LifeCycle.PERENNIAL),
                new Plant("파슬리", LifeCycle.BIENNIAL),
                new Plant("로즈마리", LifeCycle.PERENNIAL)
        };

        Plant plant = new Plant();
        // 1) Set 배열 사용
//        Set<Plant>[] result = plant.groupByOrdinalArray(garden);
//        for (int i = 0; i < result.length; i++) {
//            System.out.printf("%s: %s%n", LifeCycle.values()[i], result[i]);
//        }

        // 2) EnumMap 사용
//        EnumMap<LifeCycle, Set<Plant>> result = plant.groupByEnumMap(garden);

        // 3) Steam Map 사용
//        Map<LifeCycle, List<Plant>> result = plant.groupByStream(garden);

        // 4) Stream EnumMap 사용
        EnumMap<LifeCycle, Set<Plant>> result = plant.groupByStreamEnumMap(garden);

        System.out.println(result);
    }

    public EnumMap<LifeCycle, Set<Plant>> groupByStreamEnumMap(Plant[] garden) {
        EnumMap<LifeCycle, Set<Plant>> collect = Arrays.stream(garden)
                .collect(Collectors.groupingBy(p -> p.lifeCycle,
                        () -> new EnumMap<>(LifeCycle.class), Collectors.toSet()));
        return collect;
    }

    public Map<LifeCycle, List<Plant>> groupByStream(Plant[] garden) {
        Map<LifeCycle, List<Plant>> collect = Arrays.stream(garden)
                .collect(Collectors.groupingBy(p -> p.lifeCycle));
        return collect;
    }

    public EnumMap<LifeCycle, Set<Plant>> groupByEnumMap(Plant[] garden) {
        EnumMap<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);
        for (LifeCycle lifeCycle : LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle, new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }

        return plantsByLifeCycle;
    }

    public Set<Plant>[] groupByOrdinalArray(Plant[] garden) {
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }

        for (Plant p : garden) {
            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
        }

        return plantsByLifeCycle;
    }
}
