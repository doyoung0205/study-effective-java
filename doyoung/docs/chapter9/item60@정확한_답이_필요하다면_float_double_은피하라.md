### 정확한 답이 필요하다면 float 와 double 은 피하라

float 와 double 타입은 금융 관련 계산과는 맞지 않는다.

1.00 - 9 * 0.10 = ?

0.1 이 나와야 하지만 0.09999999999998 이 나온다.

반올림하면 해결되리라 생각할지 모르겠지만, 반올림을 해도 틀린 답이 나올 수 있다.

따라서 BigDecimal, int 혹은 long 을 사용해야 한다.

특히 BigDecimal 는 여덟 가지 반올림 보드를 이용하여 반올림을 완벽히 제어 할 수 있다.

아홉자리는 int 열여덟 자리는 long 을 사용하라 열여덟 자리를 넘어가면 BigDecimal 을 사용해야 한다.



