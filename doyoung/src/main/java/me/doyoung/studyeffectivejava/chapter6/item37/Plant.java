package me.doyoung.studyeffectivejava.chapter6.item37;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

// EnumMap을 사용해 열거 타입에 데이터를 연관시키기 (226-228쪽)

// 식물을 아주 단순하게 표현한 클래스 (226쪽)
class Plant {
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = {
            new Plant("바질",    LifeCycle.ANNUAL),
//            new Plant("캐러웨이", LifeCycle.BIENNIAL),
//            new Plant("딜",      LifeCycle.ANNUAL),
//            new Plant("라벤더",   LifeCycle.PERENNIAL),
//            new Plant("파슬리",   LifeCycle.BIENNIAL),
            new Plant("로즈마리", LifeCycle.PERENNIAL)
        };

        // 코드 37-1 ordinal()을 배열 인덱스로 사용 - 따라 하지 말 것! (226쪽)
        Set<Plant>[] plantsByLifeCycleArr =
                (Set<Plant>[]) new Set[LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycleArr.length; i++)
            plantsByLifeCycleArr[i] = new HashSet<>();
        for (Plant p : garden)
            plantsByLifeCycleArr[p.lifeCycle.ordinal()].add(p);
        // 결과 출력
        for (int i = 0; i < plantsByLifeCycleArr.length; i++) {
            System.out.printf("%s: %s%n",
                    LifeCycle.values()[i], plantsByLifeCycleArr[i]);
        }

        // 코드 37-2 EnumMap을 사용해 데이터와 열거 타입을 매핑한다. (227쪽)
        Map<LifeCycle, Set<Plant>> plantsByLifeCycle =
                new EnumMap<>(LifeCycle.class);
        for (LifeCycle lc : LifeCycle.values())
            plantsByLifeCycle.put(lc, new HashSet<>());
        for (Plant p : garden)
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        System.out.println(plantsByLifeCycle);

        // 코드 37-3 스트림을 사용한 코드 1 - EnumMap 을 사용하지 않는다! (228쪽)
        System.out.println("37-3");
        final Map<LifeCycle, List<Plant>> map = Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle));
        System.out.println(map);

        // 코드 37-4 스트림을 사용한 코드 2 - EnumMap 을 이용해 데이터와 열거 타입을 매핑했다. (228쪽)
        System.out.println("37-4");
        final EnumMap<LifeCycle, Set<Plant>> enumMap = Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle,
                        () -> new EnumMap<>(LifeCycle.class), toSet()));
        System.out.println(enumMap);
    }
}
