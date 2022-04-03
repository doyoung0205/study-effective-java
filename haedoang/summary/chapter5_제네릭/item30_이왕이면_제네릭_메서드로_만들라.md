## item30 이왕이면 제네릭 메서드로 만들라

- 개념은 동일하다. 메서드도 제네릭으로 만들어서 타입 세이프하게 구현하여야 한다
- 메서드에서도 로 타입은 타입 세이프하지 못하기 때문에 지양하도록 한다

### 제네릭 메서드 작성법
```java
public static <E> Set<E> union(Set<E> set1, Set<E> set2) {
    ...
}
```
- 타입 매개변수 목록
  - 사용할 제네릭 타입 매개변수를 정의한다고 생각하면 된다. static 뒤에 `<E>` 이것이 타입 매개변수 목록이다 
  - 메서드의 `제한자`와 `반환 타입` 사이에 온다

### 제네릭 싱글턴 팩터리 예시
```java
public class Collections {
    
    public static <T> Comparator<T> reverseOrder() {
        return (Comparator<T>) ReverseComparator.REVERSE_ORDER;
    }
    
    
    private static class ReverseComparator
            implements Comparator<Comparable<Object>>, Serializable {
    
        private static final long serialVersionUID = 7207038068494060240L;
    
        static final ReverseComparator REVERSE_ORDER
                = new ReverseComparator();
    
        public int compare(Comparable<Object> c1, Comparable<Object> c2) {
            return c2.compareTo(c1); //reverse
        }
    
        private Object readResolve() { return Collections.reverseOrder(); }
    
        @Override
        public Comparator<Comparable<Object>> reversed() {
            return Comparator.naturalOrder();
        }
    }
} 
```
- `ReverseComparator` 클래스가 구현한 compare메서드를 보면 비교값이 반대로 되어 있음을 확인할 수 있음
- 제네릭 타입을 사용하였기 때문에 형 변환없이 사용할 수 있다


### 재귀적 타입 한정 
```java
public static <E extends Comparable<E>> E max(Collection<E> c) {
    if (c.isEmpty()) {
        throw new IllegalArgumentException("컬렉션이 비어 있습니다.");
    }

    E result = null;

    for (E e : c) {
        if (result == null || e.compareTo(result) > 0) {
            result = Objects.requireNonNull(e);
        }
    }

    return result;
}
```
- 재귀적 타입 한정은 자연적 순서를 정하는 `Comparable` 인터페이스와 함께 쓰인다
- 위 메서드에서는 `Comparable` 믹스인 클래스를 구현한 객체의 자연적 순서를 사용하여 최대값을 구하는 메서드이다


### 정리
- 메서드도 제네릭을 사용하면 타입 안정성, 다형성의 이점을 누릴 수 있다