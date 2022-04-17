# 열거 타입과 애너테이션

[아이템 37. ordinal 인덱싱 대신 EnumMap을 사용하라](#ordinal-인덱싱-대신-enummap을-사용하라)  
[- EnumMap](#enummap)  
[- 정리](#정리)  


<br>

## ordinal 인덱싱 대신 EnumMap을 사용하라
- 배열이나 리스트에서 원소를 꺼낼 때 ordinal 메서드로 인덱스를 사용하는 것은 좋지 않다.
- 배열은 제네릭과 호환되지 않기 때문에 비검사 형변환을 수행해야 하고, 인덱스의 의미를 제대로 파악할 수 없다.
    ```java
    Set<PlantOld>[] plantsByLifeCycle = (Set<PlantOld>[]) new Set[LifeCycle.values().length];
    for (int i = 0; i < plantsByLifeCycle.length; i++) {
        plantsByLifeCycle[i] = new HashSet<>();
    }
    
    for (PlantOld p : garden) {
        plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
    }
    ```


### EnumMap
- 열거 타입을 키로 사용하도록 설계한 아주 빠른 Map 구현체이다.
- EnumMap은 타입 안전성과 배열의 성능을 모두 얻었다.
  ```java
  EnumMap<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);
  for (LifeCycle lifeCycle : LifeCycle.values()) {
      plantsByLifeCycle.put(lifeCycle, new HashSet<>());
  }
  
  for (Plant plant : garden) {
      plantsByLifeCycle.get(plant.lifeCycle).add(plant);
  }
  ```
- EnumMap은 배열과 달리 형번환을 사용하지 않고, 내부에서 배열을 사용해 구현했기 때문에 ordinal과 성능이 비견된다.

#### 스트림을 사용한 EnumMap
- 스트림을 사용해 맵을 관리하면 코드를 더 줄일 수 있다.
  ```java
  Map<LifeCycle, List<Plant>> collect = Arrays.stream(garden)
                  .collect(Collectors.groupingBy(p -> p.lifeCycle));
  ```

- 위의 코드는 EnumMap을 사용하지 않으므로 Collectors.grouppingBy의 mapFactory 매개변수에 원하는 맵 구현체를 명시해 호출할 수 있다.
  ```java
  EnumMap<LifeCycle, Set<Plant>> collect = Arrays.stream(garden)
                  .collect(Collectors.groupingBy(p -> p.lifeCycle,
                          () -> new EnumMap<>(LifeCycle.class), Collectors.toSet()));
  ```
  
- 스트림을 사용하면 EnumMap을 사용했을 때와 살짝 다르게 동작한다.
  - EnumMap은 언제나 식물의 생애주기당 하나씩의 중첩 맵을 만들지만, 스트림을 사용하면 해당 생애주기에 속하는 식물이 있을 때만 만든다.


### 정리
- 배열의 인덱스를 얻기 위해 ordinal을 쓰는 것은 일반적으로 좋지 않으니, 대신 EnumMap을 사용하라.