# 메서드

[아이템 53. 가변인수는 신중히 사용하라](#가변인수는-신중히-사용하라)  

<br>

## 가변인수는 신중히 사용하라
- 가변인수(varargs) 메서드는 명시한 타입의 인수를 0개 이상 받을 수 있다.
- 가변인수 메서드를 호출하면, 가장 먼저 **인수의 개수와 길이가 같은 배열을 만들고 인수들을 이 배열에 저장하여 가변인수 메서드에 건네준다.**
- 인수의 개수는 런타임에 (자동 생성된) 배열의 길이로 알 수 있다.


- 인수의 개수가 일정하지 않은 메서드를 정의해야 한다면 가변인수가 반드시 필요하다.
- 하지만, 가변인수를 사용할 때는 런타임 오류가 발생할 여지가 생긴다.
  ```java
  public static int min(int... args) {
      if (args.length == 0) {
          throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
      }
      int min = args[0];
      for (int i = 0; i < args.length; i++) {
          if (args[i] < min) {
              min = args[i];
          }
      }
      return min;
  }
  ```
  - 위의 코드에서 인수를 0개만 넣어 호출하면, 컴파일타임이 아닌 런타임에 실패한다.
  - args 유효성 검사를 명시적으로 해야 하고, min의 초기값을 `Integer.MAX_VALUE`로 설정하지 않고는 `for-each` 문도 사용할 수 없다.
- 매개변수를 2개 받으면 위의 문제를 해결할 수 있다.
  ```java
  public static int min(int firstArg, int... remainingArgs) {
      int min = firstArg;
      for (int arg : remainingArgs) {
          if (arg < min) {
              min = arg;
          }
      }
      return min;
  }
  ```
  
- `printf`는 가변인수와 한 묶음으로 자바에 도입되었고, 이때 리플렉션 기능 _(→ item 65)_ 도 재정비되었다.


- 그런데 **성능에 민감한 상황이라면 가변인수가 걸림돌이 될 수 있다.**
- 가변인수 메서드는 호출될 때마다 배열을 새로 하나 할당하고 초기화한다.
- 성능이 민감한 상황에서 가변인수의 유연성이 필요할 때 사용하는 패턴이 있다.
  - 예를 들어, 메서드 호출의 95%가 인수를 3개 이하로 사용한다고 하면, 인수가 0개인 것부터 4개인 것까지, 총 5개를 다중정의한다.
  - 마지막 다중정의 메서드가 인수 4개 이상인 5%의 호출을 담당하는 것이다. 
    ```java
    public void foo() { }
    public void foo(int a1) { }
    public void foo(int a1, int a2) { }
    public void foo(int a1, int a2, int a3) { }
    public void foo(int a1, int a2, int a3, int... rest) { }
    ```
  
  - EnumSet의 정적 팩터리도 이 기법을 사용해 열거 타입 집합 생성 비용을 최소화한다.
  - EnumSet은 비트 필드 _(→ item 36)_ 를 대체하면서 성능까지 유지해야 하므로 아주 적절하게 활용한 예라 할 수 있다.
    ```java
    public static <E extends Enum<E>> EnumSet<E> of(E e) { }
    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2) { }
    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3) { }
    public static <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4, E e5) { }
    public static <E extends Enum<E>> EnumSet<E> of(E first, E... rest) { }
    ```
  
<br>
