## item73 추상화 수준에 맞는 예외를 던져라

### 상위 계층에서는 저수준 예외를 잡아 자신의 추상화 수준에 맞는 예외로 바꾸어야 한다
- 예외 번역(exception translaction)
- ex) AbstractSequentialList 
    ```java
    public E get(int index) {
            try {
                return listIterator(index).next();
            } catch (NoSuchElementException exc) {
                throw new IndexOutOfBoundsException("Index: "+index);
            }
        }
    ```
  
### 예외 연쇄는 저수준 예외가 디버깅에 도움이 된다
- ex) `getCause` , `initCause`


### 정리
 - 아래 계층의 예외를 예방하거나 스스로 처리할 수 없고, 예외를 상위 계층에 노출하기 곤란하면 `예외 번역`을 사용하라
 - `예외 연쇄`를 이용하면 고수준 예외를 던지면서 원인도 알려주어 분석하기 용이하게 된다