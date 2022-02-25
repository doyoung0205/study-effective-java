## Comparable 을 구현할지 고려하라

- Comparable 은 인터페이스이면 compareTo 메서드를 다룬다.
- compareTo 와 equals 와 비슷하지만 동치성 뿐만 아니라 **순서** 까지 비교할 수 있으며 제네릭하다.

Comparable 을 구현한 객체(a)들은 Arrays.sort(a), Collections.sort(a) 로 손쉽게 정렬 가능하다. (`ComparableTest.sort` 참고)

### compareTo 메서드의 일반 규약

- `Comparable`을 구현한 클래스는 모든 x,y 에 대해 syn(x.compareTo(y)) == -syn(y.compareTo(x)) 여야 한다.
    - 따라서 전자에서 예외가 발생하면 후자에서 예외가 발생해야 한다.
- `Comparable`을 구현한 클래스는 추이성을 보장해야 한다. 즉, x.compareTo(y) > 0 && x.compareTo(z) > 0 이면 y.compareTo(z) 여야 한다.
- `Comparable`을 구현한 클래스는 모든 z에 대해 x.compareTo(y) == 0 이면 syn(x.compareTo(z)) == syn(y.compareTo(z)) 여야 한다.
- (필수X 선택 O) (x.compareTo(y) == 0) == (x.equals(y)) 여야 한다.

--- 

TreeSet 은 compareTo 메서드로 비교하기 떄문에 BigDecimal("1.0"), BigDecimal("1.00") 가 들어가면 

동일하다고 판단하여 총 원소의 갯수는 1개가 된다.




