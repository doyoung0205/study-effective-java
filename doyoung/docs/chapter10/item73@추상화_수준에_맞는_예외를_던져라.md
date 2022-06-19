### 추상화 수준에 맞는 예외를 던져라

상위 계층에서는 저수준 예외를 잡아 자신의 추상화 수준에 맞는 예외로 바꿔 던져야 한다.

-> 예외 번역

```
try {
    ... // 저수준 추상화를 이용한다.
} catch (LowerLevelException e) {
    // 추상화 수준에 맞게 번역한다.
    throw new HigherLevelException(...);
}
```

예외를 번역할 때, 저수준 예외가 디버깅에 도움이 된다면 예외 연쇄를 사용하는 것이 좋다.

예외 연쇄란 문제의 근본원인인 저수준 예외를 고수준 예외에 실어 보내는 것이다.

```
try {
    ... // 저수준 추상화를 이용한다.
} catch (LowerLevelException e) {
    // 추상화 수준에 맞게 번역한다.
    throw new HigherLevelException(e);
}
```

또는 iniCause 를 통해서 직접 못박을 수 있다.

추가로 logging 을 적절하게 이용해서 로그를 분석해 추가 조치를 취할 수 있게 해준다.



