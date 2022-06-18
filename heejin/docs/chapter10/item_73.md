# 예외

[아이템 73. 추상화 수준에 맞는 예외를 던지라](#추상화-수준에-맞는-예외를-던지라)
[- 정리](#정리)

<br>

## 추상화 수준에 맞는 예외를 던지라
- 메서드가 저수준 예외를 처리하지 않고 바깥으로 전파해버릴 때 종종 관련 없는 예외를 던지게 된다.
- 이는 내부 구현 방식을 드러내어 윗 레벨 API를 오염시킨다.
- 이런 문제를 피하려면 **상위 계층에서는 저수준 예외를 잡아 자신의 추상화 수준에 맞는 예외로 바꿔 던져야 한다.**
  - 이를 예외 번역이라고 한다.
    ```java
    try {
      ...  // 저수준 추상화를 이용한다.
    } catch(LowerLevelException e) {
      throw new HighterLevelException(...);
    }
    ```

  - `AbstractSequentialList`에서 수행하는 예외 번역 예시
    ```
    /**
     * Returns the element at the specified position in this list.
     *
     * This implementation first gets a list iterator pointing to the
     * indexed element (with {@code listIterator(index)}).  Then, it gets
     * the element using {@code ListIterator.next} and returns it.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        try {
            return listIterator(index).next();
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }
    ```
    
- **예외를 번역할 때, 저수준 예외가 디버깅에 도움이 된다면 예외 연쇄를 사용하는게 좋다.**
  - 예외 연쇄란 문제의 근본 원인인 저수준 예외를 고수준 예외에 실어 보내는 방식이다.
    ```java
    try {
        ...  // 저수준 추상화를 이용한다.
    } catch (LowerLevelException cause) {
        // 저수준 예외를 고수준 예외에 실어 보낸다.
        throw new HigherLevelException(cause);
    }
    ```
    
  - 대부분의 표준 예외는 예외 연쇄용 생성자를 갖추고 있다.
  - 그렇지 않은 예외라도 initCause 메서드를 이용해 '원인'을 직접 넣어줄 수 있다.
  
- 무턱대고 예외를 전파하는 것보다야 예외 번역이 우수한 방법이지만, 그렇다고 남용해서는 곤란하다.
  - 가능하다면 저수준 메서드가 반드시 성공하도록 하여 아래 계층에서는 예외가 발생하지 않도록 하는 것이 최선이다.

<br>

### 정리
- 아래 계층의 예외를 예방하거나 스스로 처리할 수 없고, 그 예외를 상위 계층에 그대로 노출하기 곤란하다면 예외 번역을 사용하라.
- 이때 예외 연쇄를 이용하면 상위 계층에는 맥락에 어울리는 고수준 예외를 던지면서 근본 원인도 함께 알려주어 오류를 분석하기에 좋다.

<br>
