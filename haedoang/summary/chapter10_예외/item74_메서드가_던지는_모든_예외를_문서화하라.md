## item74 메서드가 던지는 모든 예외를 문서화하라 

###  검사 예외는 따로 따로 선언하고 예외가 발생하는 상황을 자바독의 @throws 태그로 문서화하라
- 비검사 예외도 문서해두면 좋다


### 메서드가 던질 수 있는 예외를 각각 @throws 태그로 문서화하고, 비검사예외는 throws 목록에 넣지 않는다

### 한 클래스에 정의된 많은ㅁ ㅔ서드가 같은 이유로 같은 예외를 던진다면 클래스 설명에 추가하라
```java 
    /**
     * 
     * @param name
     * @param players
     * @throws NullPointerException 이름이 null값인 경우
     * @throws IllegalArgumentException 이름 또는 플레이어가 공백인 경우와 플레이어 수가 최소 인원보다 적은 경우 
     * @throws IllegalStateException 골키퍼가 1명이 아닌경우 
     */
    private void validate(String name, List<Player> players) {
        if (Objects.requireNonNull(name).isEmpty() ||
                Objects.requireNonNull(players).isEmpty() ||
                players.size() < PLAYERS_COUNT
        ) {
            throw new IllegalArgumentException();
        }

        if (players.stream().filter(it -> it.getPosition().equals(Position.GK)).count() != 1) {
            throw new IllegalStateException();
        }
    }
```

### 정리
- 메서드가 던질 가능성이 있는 모든 예외를 문서화하라
- 문서화는 자바독의 `@throws` 태그를 사용하라
- 검사 예외만 throws 문에 선언하라 
