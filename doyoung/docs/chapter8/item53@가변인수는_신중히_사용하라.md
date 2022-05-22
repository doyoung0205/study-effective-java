### 가변인수는 신중히 사용하라

가변인수를 사용할 때 인수가 최소 1개 이상 있어야 한다면 다음 처럼 작성하자.

```
static int min(int firstArg, int ... remainingArgs) {
...
}

```

가변인수는 배열을 만드는데 성능 최적화를 위해서 EnumSet 처럼 가장 많이 사용하는 케이스는 매개변수로 따로 만들자.

public foo () {}
public foo (int a1) {}
public foo (int a1, int a2) {}
public foo (int a1, int a2, int a3) {}
public foo (int a1, int a2, int a3, int ... rest) {}

