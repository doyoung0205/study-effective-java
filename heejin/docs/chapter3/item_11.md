# 모든 객체의 공통 메서드

[아이템 11. equals를 재정의하려거든 hashCode도 재정의하라](#equals를-재정의하려거든-hashcode도-재정의하라)   
[- Object 명세에 적힌 규약](#object-명세에-적힌-규약)  
[- 논리적으로 같은 객체인데 해시 코드가 다른 경우](#논리적으로-같은-객체인데-해시-코드가-다른-경우)  
[- Hash 관련 Colletions](#hash-관련-colletions)  
[- hashCode 구현](#hashcode-구현)  
[- 좋은 hashCode를 작성하는 요령](#좋은-hashcode를-작성하는-요령)  
[- hashCode 주의사항](#hashcode-주의사항)  

<br>

## equals를 재정의하려거든 hashCode도 재정의하라

### Object 명세에 적힌 규약
1) equals 비교에 사용되는 정보가 변경되지 않았다면, hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다.
2) equals(Object)가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 똑같은 값을 반환해야 한다.
3) equals(Object)가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없다.


### 논리적으로 같은 객체인데 해시 코드가 다른 경우 
- Objects의 두 번째 명세를 위반한 경우 발생하는 문제이다.
- HashMap은 해시코드가 다른 엔트리끼리는 동치성 비교를 시도조차 하지 않도록 최적화 되어 있다. - [예시](../../src/test/java/study/heejin/chapter3/Item11Test.java)

  ```java
  Map<PhoneNumber, String> m = new HashMap<>();
  m.put(new PhoneNumber(707, 867, 5309), "Jenny");

  m.get(new PhoneNumber(707, 867, 5309)); // "Jenny" 가 나와야 할 것 같지만 null을 반환한다.
  ```


### Hash 관련 Colletions
- HashSet, HashMap 과 같은 Hash 관련 Collection 에서 Key는 hashCode 를 기준으로 정해진다.
- Key 값으로 고유한 식별값인 해시값을 만드는데, 이 해시값은 Bucket 에 저장된다.
- 해시테이블의 크키는 한정적이기 때문에 서로 다는 객체라도 같은 해시값을 가질 수 있다.
- 이 때 해시 충돌(Hash Collisions) 이 발생하게 된다.
- 해시 충돌이 발생할 경우 해당 버킷에 LinkedList(TreeMap) 형태로 객체를 추가한다. (Seperate Chaining)
- **같은 해시값의 버킷 안에 다른 객체가 있는 경우 eqauls() 메서드가 사용된다.**


- HashTable 에 put() 으로 객체를 추가하는 경우
  - 값이 같은 객체가 있다면(equals() == true) 기존 객체를 덮어쓴다.
  - 값이 같은 객체가 없다면(equals () == false) 해당 entry를 LinkedList에 추가한다.


- HashTable 에 get() 메서드로 객체를 조회하는 경우
  - equals () == true 면, 그 객체를 리턴한다.
  - equals () == false 면, null을 리턴한다.


- equals() 는 재정의 하고 hashCode()를 재정의 하지 않을 때 **→ 같은 객체인데 hashCode가 다르기 때문에 해당 객체가 저장된 버킷을 찾을 수 없다.**
- hashCode()는 재정의하고 equals()를 재정의 하지 않을 때 **→ hashCode가 같은데 값이 같은 객체가 없으므로 원하는 객체를 찾을 수 없다.**


### hashCode 구현

1) 모든 객체에서 똑같은 해시코드를 반환하는 방식 - [예시](../../src/main/java/study/heejin/chapter3/item11/SameHashCodeNumber.java)
    ```java
    @Override
    public int hashCode() {
        return 42;
    }
    ```
   - 이 방식은 모든 객체가 해시테이블의 버킷 하나에 담겨 마치 연결 리스트처럼 동작한다. 그 결과, **평균 수행 시간이 `O(1)`으로 느려진다.**

  
2) 동치인 모든 객체에서 똑같은 해시코드를 반환하는 방식 - [예시](../../src/main/java/study/heejin/chapter3/item11/DiffHashCodeNumber.java)
    ```java
    @Override
      public int hashCode() {
          return Objects.hash(areaCode, prefix, lineNum);
      }
    ```
   - 이 방식은 입력 인수를 담기 위한 배열이 만들어지고, 입력 중 기본 타입이 있다면 박싱과 언박싱을 거치기 때문에 **성능이 민감하지 않은 경우에서 사용해야 한다.** (→ 객체가 해시의 키로 많이 사용되는 경우에는 사용시 주의하자.)
   - Objects#hash는 Arrays#hashCode를 통해 hashCode를 계산한다.
    ```java
    public static int hashCode(Object a[]) {
        if (a == null)
            return 0;

        int result = 1;

        for (Object element : a)
            result = 31 * result + (element == null ? 0 : element.hashCode());

        return result;
    }
    ```
    - 성능에 민감한 경우라면 hashCode를 Laye Loading 하는 방식을 사용해 볼 수 있다. - [예시](../../src/main/java/study/heejin/chapter3/item11/LazyLoadHashCodeNumber.java)
  

### 좋은 hashCode를 작성하는 요령
1. int 변수 result를 선언한 후 값 `c`로 초기화한다. 이때 `c`는 해당 객체의 첫번째 핵심 필드를 2.a 방식으로 계산한 해시코드이다. **equals에서 사용되지 않는 필드는 반드시 hashCode에서도 제외해야한다.**
2. 나머지 핵심 필드 `f`에 대해 각각 다음 작업을 수행한다.  
   a. 필드의 해시코드 c를 계산한다.
   - **기본 필드**라면 `Type.hashCode(f)`를 수행한다. 이때 Type은 기본 타입에 매핑되는 래퍼 타입이다.
   - **참조 필드**이면서 클래스의 equals가 이 필드의 equals를 재귀적으로 호출해 비교한다면 이 필드의 hashCode가 재귀적으로 호출한다. 만약 필드의 값이 `null`이면 0을 반환한다.
   - **배열**이라면 핵심 원소 각각을 별도의 필드로 나눈다. 별도로 나눈 필드를 위 규칙을 적용하여 해시코드를 계산한 후 다음 소개되는 2.b 방식으로 갱신한다. 모든 원소가 핵심이라면 `Arrays.hashCode`를 이용한다.  

    b. 2.a로 계산한 해시코드 c로 result를 갱신한다.
    ```java
    result = 31 * result + c
    ```
3. result를 반환한다.


- hashCode가 작성된 예시
  ```java
  @Override 
  public int hashCode() {
      int result = Short.hashCode(areaCode);
      result = 31 * result + Short.hashCode(prefix);
      result = 31 * result + Short.hashCode(lineNum);
      return result;
  }
  ```
  - 31은 홀수이면서 소수이기도 하고 `31 * i = (i << 5) - i` 와 같이 시프트연산과 뺄셈으로 최적화할 수 있기 때문에 사용한다. 
  - 하지만 사용하는 이유는 명확하지는 않고 전통적으로 쓰고 있다.


- 해시 충돌이 더욱 적은 방법을 꼭 써야 한다면, 구아바의 com.google.common.hash.Hashing을 참고하자 - [Guava를 써야하는 5가지 이유](https://blog.outsider.ne.kr/710)



### hashCode 주의사항
- 파생 필드는 해시코드 계산에서 제외해도 된다.
- eqauls 비교에 사용되지 않은 필드는 반드시 제외해야 한다.
- hashCode가 반환하는 값의 생성 규칙을 API 사용자에게 자세히 공표하지 말자.
  - 클라이언트에서 이 값에 의존하지 않고 추후에 계산 방식을 바꿀 수 있게 한다.


<br>

---
#### Reference

- [item 11. equals를 재정의하려거든 hashCode도 재정의하라](https://github.com/Meet-Coder-Study/book-effective-java/blob/main/3%EC%9E%A5/11_equals%EB%A5%BC_%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%A0%A4%EA%B1%B0%EB%93%A0_hashCode%EB%8F%84_%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%9D%BC_%EB%B0%95%EA%B2%BD%EC%B2%A0.md)
- [아이템 11 equals를 재정의하려거든 hashCode도 재정의하라](https://github.com/Meet-Coder-Study/book-effective-java/blob/main/3%EC%9E%A5/11_equals%EB%A5%BC_%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%A0%A4%EA%B1%B0%EB%93%A0_hashCode%EB%8F%84_%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%9D%BC_%EC%95%88%EC%86%A1%EC%9D%B4.md)
- [Effective Java 3rd ITEM 11](https://dlsrb6342.github.io/2019/04/30/Effective-Java-3rd-ITEM-11/)
- [Guava를 써야하는 5가지 이유](https://blog.outsider.ne.kr/710)


