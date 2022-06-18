# 예외

[아이템 72. 표준 예외를 사용하라](#표준-예외를-사용하라)     

<br>

## 표준 예외를 사용하라
- 자바 라이브러리는 대부분 API에서 쓰기에 충분한 수의 예외를 제공한다.
- 표준 예외를 사용하면 장점이 많다.
  - 익숙한 규약을 따라 사용하면 API를 익히고 사용하기 쉬워진다.
  - 예외 클래스가 적을수록 메모리 사용량도 줄고 클래스를 적재하는 시간도 적게 걸린다.
  - Exception, RuntimeException, Throwable, Error는 직접 재사용하지 말자.

  #### 자주 사용하는 표준 예외
  - `IllegalArgumentException` : 허용하지 않는 값이 인수로 건네졌을 때 (null은 따로 NullPointException으로 처리)
  - `IllegalStateException` : 객체가 메서드를 수행하기에 적절하지 않은 상태일 때  
 **→ 인수의 값이 무엇이었든 어차피 실패했을거라면 IllegalStateException을, 그렇지 않으면 IllegalArgumentExceptiondmf 던지자.** 
  - `NullPointException` : null을 허용하지 않는 메서드에 null을 건넸을 때
  - `IndexOutOfBoundsException` : 인덱스 범위를 넘어섰을 때
  - `ConcurrentModificationException` : 허용하지 않는 동시 수정이 발견됐을 때
  - `UnsupportedOperationException` : 호출한 메서드를 지원하지 않을 때

- 이 외에도 상황에 부합한다면 항상 표준 예외를 사용하자. 이때 API 문서를 참고해 그 예외가 어떤 상황에서 던져지는지 꼭 확인하자.
- 더 많은 정보를 제공하길 원한다면 표준 예외를 확장해도 좋다. 단, 예외는 직렬화할 수 있다는 사실을 기억하자.


<br>
