### 표준 예외를 사용하라

- IllegalArgumentException: 허용하지 않는 값이 인수로 건네졌을 때 (null 은 따로 NullPointerException 으로 처리)
- IllegalStateException: 객체가 메서드로 수행하기에 적절하지 않은 상태일 때
- NullPointException: null을 허용하지 않는 메서드에 null을 건넸을 때
- IndexOutOfBoundsException: 인덱스가 범위를 넘어섰을 때
- ConcurrentModificationException: 허용하지 않는 동시 수정이 발견됐을 떄
- UnsupportedOperationException: 호출한 메서드를 지원하지 않을떄

- IllegalArgumentException VS IllegalStateException 의 차이
    - 인수 값이 무엇이었든 어차피 실패했을 거라면 State 아니면 Argument
- UnsupportedOperationException
  ```
  public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    protected AbstractList() {
    }

    ...
    
    //	해당 메소드가 실행되는 것이다.
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }
    
    ...
  }
  ```
