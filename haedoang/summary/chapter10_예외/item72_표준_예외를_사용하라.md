## item72 표준 예외를 사용하라


### 재사용되는 예외 클래스
- IllegalArgumentException:  허용하지 않는 값이 인수로 내어졌을 때 
- IllegalStateException: 객체가 메서드를 수행하기에 적절하지 않을 때
- NullPointerException 
- IndexOutOfBoundsException
- ConcurrentModificationException: 허용하지 않은 동시 수정이 발견됐을 때
- UnsupportedOperationException: 호출한 메서드를 지원하지 않을 때 

### Exception, RuntimeException, Throwable, Error 는 직접 재사용하지 말라

### IllegalArgumentException, IllegalStateException 비교하기
- 인수 값이 무엇이었든 어차피 실패했을거라면 `IllegalStateException`, 그렇지 않으면 `IllegalArgumentException` 예외를 던지자 

