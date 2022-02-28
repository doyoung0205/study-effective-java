# 모든 객체의 공통 메서드

[아이템 14. Comparable을 구현할지 고려하라](#comparable을-구현할지-고려하라)   
[- compareTo 메서드의 일반 규약](#compareto-메서드의-일반-규약)  
[- compareTo 메서드 주의사항](#compareto-메서드-주의사항)  
[- 핵심 필드의 비교](#핵심-필드의-비교)  

<br>

## Comparable을 구현할지 고려하라
- Comparable 인터페이스의 유일한 메서드인 compareTo는 이번장에서 다룬 Onject의 메서드가 아니다.
- 하지만 Object의 equals 메서드와 두 가지 차이만 빼고 같다.
  - compareTo는 단순 동치성 비교에 더 해 **순서**까지 비교할 수 있다.
  - compareTo는 **제네릭**하다.
- Comparable을 구현했다는 것은 그 클래스의 인스턴스들에는 자연적인 순서가 있음을 뜻한다.
- 그래서 Comparable을 구현한 객체들의 배열은 손쉽게 정렬할 수 있다.

  ````java
  public class WordList {
     public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        Collections.addAll(set, args);
        System.out.println(set);
     }
  }
  ````
  - String이 Comparable을 구현한 덕분에 인수들을 중복을 제거하고 알파벳 순서로 정렬한다.
  
- 자바 플랫폼 라이브러리의 모든 값 클래스와 열거 타입이 Comparable을 구현했다.
  

### compareTo 메서드의 일반 규약
- 이 객체가 주어진 객체보다 작으면 음의 정수를, 같으면 0을, 크면 양의 정수를 반환한다.
- 이 객체와 비교할 수 없는 타입의 객체가 주어지면 ClassCastException을 던진다.


- 반사성
  ```java
  sgn(x.compareTo(y)) == -sgn(y.compareTo(x))
  x.compareTo(y)가 예외를 던지면 y.compareTo(x)도 예외를 던진다.
  ```

- 추이성
  ```java
  x.compareTo(y) > 0 && y.compareTo(z) > 0 이면, x.compareTo(z) > 0 이다.
  ```

- 대칭성
  ```java
  x.compareTo(y) == 0 이면 sgn(x.compareTo(z)) == sgn(y.compareTo(z)) 이다.
  ```

- 다음은 필수 권고사항은 아니지만, 꼭 지키는 것이 좋다.  
  이 권고를 지키지 않는다면, 클래스의 순서가 equals 메서드와 일관되지 않음을 명시하는 것이 좋다.
  ```java
  (x.compareTo(y) == 0) == (x.equals(y))
  ```


### compareTo 메서드 주의사항
- 다른 타입 사이의 비교도 허용하지만, 보통은 비교할 객체들이 구현한 공통 인터페이스를 매개로 이뤄진다.
  - hashCode 규약을 지키지 못하면 해시를 사용하는 클래스와 어울리지 못하듯, comapareTo 규약을 지키지 못하면 비교를 활용하는 클래스와 어울리지 못한다.


- 기존 클래스를 확장한 구체 클래스에서 새로운 값 컴포넌트를 추가했다면 compareTo 규약을 지킬 방법이 없다.
  - Comparable을 구현한 클래스를 확장해 값 컴포넌트를 추가하고 싶다면, 확장하는 대신 독립된 클래스를 만들고, 이 클래스에 원래 클래스의 인스턴스를 가리키는 필드를 둔다.
  - 그런 다음 내부 인스턴스를 반환하는 '뷰' 메서드를 제공하면 된다.


- compareTo의 마지막 규약은 필수는 아니지만 꼭 지키길 권장한다.
  - compareTo의 순서와 equals의 결과가 일관되지 않으면, 객체를 정렬된 컬렉션에 넣으면 해당 컬렉션이 구현한 인터페이스(Collection, Set 혹은 Map)에 정의된 동작과 엇박자를 낼 것이다.
  - 정렬된 컬렉션의 경우 동치성을 비교할 때 `equals` 대신 `compareTo`를 사용하기 때문에 HashSet 과 TreeSet의 결과가 다를 수 있다. - [예시](https://github.com/pageprologue/study-effective-java/blob/main/heejin/src/test/java/study/heejin/chapter3/Item14Test.java#LC14)


- Comparable은 타입을 인수로 받는 제네릭 인터페이스이므로 compareTo 메서드의 인수 타입은 컴파일타임에 정해진다.
  - 이는 입력 인수의 타입을 확인하거나 형변환할 필요가 없다.
  - 인수의 타입이 잘못됐다면 컴파일 자체가 되지 않는다.


- compareTo 메서드는 각 필드가 동치인지를 비교하는 게 아니라 그 순서를 비교한다.
  - Comparable을 구현하지 않은 필드나 표준이 아닌 순서로 비교해야 한다면 `비교자(Comparator)`를 사용한다.
  

- 자바 7부터는 박싱된 기본 타입 클래스들에 새로 추가된 정적 메서드인 compare를 이용하면 된다.  
  - 이 책의 2판 에서는 compareTo 메서드에서 정수 기본 타입 필드를 비교할 때는 관계 연산자인 `<`, `>`를, 실수 기본 타입 필드를 비교할 때는 정벅 메서드인 `Double.compre`와 `Float.compare`를 사용하라고 권했다.
  - compareTo 메서드에서 관걔 연산자 `<`, `>`를 사용하는 방식은 이제 추천하지 않는다.


### 핵심 필드의 비교
- 클래스에 핵심 필드가 여러개라면 어느 것을 먼저 비교하느냐가 중요해진다.
- 가장 핵심적인 필드부터 비교하여, 그 결과가 0이 아니라면, 즉 순서가 결정되면 거기서 끝이다. - [예시](https://github.com/pageprologue/study-effective-java/blob/main/heejin/src/test/java/study/heejin/chapter3/Item14Test.java#LC48)
  ```java
  @Override
    public int compareTo(PhoneNumber pn) {
        int result = Short.compare(areaCode, pn.areaCode);
        if (result == 0) {
            result = Short.compare(prefix, pn.prefix);
            if (result == 0) {
                result = Short.compare(lineNum, pn.lineNum);
            }
        }
        return result;
    }
  ```
- 자바 8에서는 Comparator 인터페이스가 메서드 연쇄 방식으로 비교자를 생성할 수 있게 되었다. - [예시](https://github.com/pageprologue/study-effective-java/blob/main/heejin/src/test/java/study/heejin/chapter3/Item14Test.java#LC61)
  - 이 방식은 간결하지만, 약간의 성능 저하가 뒤따른다.
  ```java
  private static final Comparator<PhoneNumber> COMPARATOR = 
        comparingInt((PhoneNumber pn) -> pn.areaCode)
            .thenComparingInt(pn -> pn.prefix)
            .thenComparingInt(pn -> pn.lineNum);
  
  public int compareTo(PhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }
  ```

<br>

