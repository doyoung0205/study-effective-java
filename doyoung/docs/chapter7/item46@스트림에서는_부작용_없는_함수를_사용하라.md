### 스트림에서는 부작용 없는 함수를 사용하라

스트림은 함수형 프로그래밍에 기초한 패러다임이기 떄문이다.

따라서 스트림의 표현력, 속도, 병렬성을 얻으려면 이 패러다임을 받아 들여야 한다.

### 스트림의 패러다임

- 계산을 일련의 변환(transformation) 으로 재구성 하는 부분이다.

이때 변환단계는 가능한 순수 함수여야 한다.

- 순수함수: 오직 입력만이 결과에 영향을 주는 함수 (ex. utils 형의 메소드)

---

```
Map<String, Long> freq = new HashMap<>();
try (Stream<String> words = new Scanner(file).tokens()) {
    words.forEach(word -> {
        freq.merge(word.toLowerCase(), 1L, Long::sum); // 외부 상태 값을 수정하고 있다.
    });
}

```

이 처럼 외부 상태 값을 변경하면서 문제가 생긴다.

```
Map<String, Long> freq;
try (Stream<String> words = new Scanner(file).tokens()) {
    freq = words
            .collect(
                    groupingBy(
                            String::toLowerCase, counting()
                    )
            );
}

```

스트림 안에서 foreach 에서 가장 덜 스트림 답다. 따라서 스트림 계산 결과를 보고할 때만 사용하고 계산하는데는 쓰지 말자.

스트림 toMap 종단연산중 마지막 값을 저장할 때는 `last-write-wins` 마지막 값을 취하는 수집기를 만들 때 유용하다.




