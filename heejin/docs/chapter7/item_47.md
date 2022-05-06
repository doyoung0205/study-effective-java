# 람다와 스트림

[아이템 47. 반환 타입으로는 스트림보다 컬렉션이 낫다](#반환-타입으로는-스트림보다-컬렉션이-낫다)  
[- Stream 과 Iterable](#stream-과-iterable)   
[- Collection 인터페이스](#collection-인터페이스)   
[- 정리](#정리)  

<br>

## 반환 타입으로는 스트림보다 컬렉션이 낫다
- 원소 시퀀스를 반환할 때 Collection, Set, List 같은 `컬렉션 인터페이스`, 혹은 `Iterable`이나 `배열`, 마지막으로 `Stream`이 있다.
- 이 중 적합한 타입을 선택해야 하고, 기본은 컬렉션 인터페이스이다.
- 스트림은 반복을 지원하지 않기 때문에 스트림과 반복을 알맞게 조합해야 좋은 코드가 된다.


### Stream 과 Iterable
- Stream 인터페이스는 Iterable 인터페이스가 정의한 추상 메서드를 전부 포함할 뿐만 아니라, Iterable 인터페이스가 정의한 방식대로 동작한다.
- 그럼에도 for-each로 스트림을 반복할 수 없는 까닭은 Stream이 Iterable을 확장(extends)하지 않기 때문이다.
- 어댑터 패턴을 사용하면 스트림을 for-each 문으로 반복할 수 있다.
  - Stream<E>를 Iterable<E>로 중개해주는 어댑터
    ```java
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }
    ```
  - Iterable<E>를 Stream<E>로 중개해주는 어댑터
    ```java
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
    ```
    

### Collection 인터페이스
- Collection 인터페이스는 Iterable의 하위 타입이고 stream 메서드도 제공하니, 반복과 스트림을 동시에 제공한다.
- 따라서 원소 시퀀스를 반환하는 공개 API의 반환 타입에는 Collection이나 그 하위 타입을 쓰는게 일반적으로 최선이다.
- 반환하는 시퀀스의 크기가 메모리에 올려도 안전할 만큼 작다면 ArrayList나 HashSet 같은 표준 컬렉션 구현체를 반환하는 것이 최선일 수 있다.
- 하지만 단지 컬렉션을 반환한다는 이유로 무거운 시퀀스를 메모리에 올리는 것은 좋지 않다.
  - [멱집합 예시](../../src/main/java/study/heejin/chapter7/item47/PowerSet.java) - 멱집합을 구성하는 각 원소의 인덱스를 비트 벡터로 사용하는 방식이다.
  - [부분리스트 예시](../../src/main/java/study/heejin/chapter7/item47/SubLists.java) - 어떤 리스트의 부분리스트는 단순히 그 리스트의 프리픽스와 서픽스에 빈 리스트 하나만 추가하면 된다. 
  

### 정리
- 원소 시퀀스를 반화하는 메서드를 작성할 때는, 스트림으로 처리하기 원하는 사용자와 반복으로 처리하기 원하는 사용자를 모두 고려해서 설계하라.
- 컬렉션을 반환할 수 있다면 컬렉션을 기본으로 생각하라.


<br>
