# 제네릭

[아이템 30. 이왕이면 제네릭 메서드로 만들라](#이왕이면-제네릭-메서드로-만들라)
[- 제네릭 싱글턴 패턴](#제네-싱글턴-패턴)
[- 항등함수](#항등함수)
[- 재귀적 타입 한정](#재귀적-타입-한정)
[- 정리](#정리)

<br>

## 이왕이면 제네릭 메서드로 만들라
- 클래스와 마찬가지로 메서드도 제네릭으로 만들 수 있다.
- 매개변수화 타입을 받는 정적 유틸리티 메서드는 보통 제네릭이다.
  - `Collections`의 알고리즘 메서드(`binarySearch`, `sort` 등)는 모두 제네릭이다.
    ```java
    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        if (list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
            return Collections.indexedBinarySearch(list, key);
        else
            return Collections.iteratorBinarySearch(list, key);
    }
    ```

- 타입 매개변수의 명명 규칙은 제네릭 메서드나 제네릭 타입 모두 똑같다.  
  (타입 매개변수 목록은 메서드의 제한자와 반환 타입 사이에 온다.)


### 제네릭 싱글턴 패턴
- 불변 객체를 여러 타입으로 활용할 수 있게 만들어야 할 때 사용힌다.
- 요청한 타입 매개변수에 맞게 객체의 타입을 바꿔주는 정적 팩터리를 만들어야 한다.
- eg. `Collections.reverseOrder`, `Collections.emptySet`


### 항등함수
- 항등함수란 입력 값을 수정 없이 그대로 반환하는 특별한 함수이다.
- 항등함수 객체는 상태가 없기 때문에 요청할 때마다 새로 생성하는 것은 낭비다.
- 자바의 제네릭이 실체화된다면 항등함수를 타입별로 만들어야 했겠지만, 소거 방식을 사용한 덕에 제네릭 싱글턴 하나면 충분하다.


### 재귀적 타입 한정
- 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정할 수 있다.
- 재귀적 타입 한정은 주로 타입의 자연적 순서를 정하는 `Comparable` 인터페이스와 함께 쓰인다.
  ```java
  public static <E extends Comparable<E>> E max(Collection<E> c) { ... }
  ```
  - 타입 한정인 `<E extends Comparable<E>` 는 "모든 타입 E는 자신과 비교할 수 있다"는 의미이다.


### 정리
- 클라이언트에서 입력 매개변수와 반환값을 형변환해야 하는 메서드보다 제네릭 메서드가 더 안전하고 사용하기 쉽다.
- 타입과 마찬가지로, 메서드도 형변환 없이 사용할 수 있는 편이 좋다.
- 형변환 해줘야 하는 기존 메서드는 제네릭하게 만들자.


<br>

