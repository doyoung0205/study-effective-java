### 예외를 무시하지 마라

예외를 무시하지말자!!

```
// 예외를 무시하는 행위

try {
 ...
} catch (SomeException e) {
}
```

catch 블록을 비워두면 예외가 존재할 이유가 없어진다.
비유하자면 화재경보를 무시하는 수준을 넘어 아에 꺼버려 다른 누구도 화재가 발생했음을 알지 못하게 하는 것과 같다.

예외를 무시하기로 했다면 catch 블록 안에 그렇게 결정한 이유를 주석으로 남기고 예외 변수의 이름도 ignored 로 바꿔놓도록하자.


