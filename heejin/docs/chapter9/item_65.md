# 일반적인 프로그래밍 원칙

[아이템 65. 리플렉션보다는 인터페이스를 사용하라](#리플렉션보다는-인터페이스를-사용하라)  
[- 정리](#정리)  

<br>

## 리플렉션보다는 인터페이스를 사용하라
- 리플렉션 기능(`java.lang.reflect`)을 이용하면 프로그램에서 임의의 클래스에 접근할 수 있다.
- 나아가 Construct, Method, Field 인스턴스를 이용해 각각에 연결된 실제 생성자, 메서드, 필드를 조작할 수도 있다.
  - 예를 들어, `Method.invoke`는 어떤 클래스의 어떤 객체가 가진 어떤 메서드라도 호출할 수 있게 해준다.
- **리플렉션을 이용하면 컴파일 당시에 존재하지 않던 클래스도 이용할 수 있다.**

<br>

#### 리플렉션의 단점
1. **컴파일타임 타입 검사가 주는 이점을 하나도 누릴 수 없다. 예외 검사도 마찬가지다.**
2. **리플렉션을 이용하면 코드가 지저분하고 장황해진다.**
3. **성능이 떨어진다.**

<br>

#### 리플렉션의 사용
- 리플렉션은 아주 제한된 형태로만 사용해야 단점을 피하고 이점만 취할 수 있다.
- **리플렉션은 인스턴스 생성에만 쓰고, 이렇게 만든 인스턴스는 인터페이스나 상위 클래스로 참조해 사용하자.**

    ```java
    public static void main(String[] args) {
        // 클래스 이름을 Class 객체로 변환
        Class<? extends Set<String>> cl = null;
        try {
            // 비검사 형변환
            cl = (Class<? extends Set<String>>) Class.forName(args[0]);

        } catch (ClassNotFoundException e) {
            fatalError("클래스를 찾을 수 없습니다.");
        }

        // 생성자를 얻는다.
        Constructor<? extends Set<String>> cons = null;
        try {
            cons = cl.getDeclaredConstructor();

        } catch (NoSuchMethodException e) {
            fatalError("매개변수 없는 생성자를 찾을 수 없습니다.");
        }

        // 집합의 인스턴스를 만든다.
        Set<String> s = null;
        try {
            s = cons.newInstance();

        } catch (IllegalAccessException e) {
            fatalError("생성자에 접근할 수 없습니다.");
        } catch (InstantiationException e) {
            fatalError("클래스를 인스턴스화할 수 없습니다.");
        } catch (InvocationTargetException e) {
            fatalError("생성자가 예외를 던졌습니다: " + e.getCause());
        } catch (ClassCastException e) {
            fatalError("Set을 구현하지 않은 클래스입니다.");
        }

        // 생성한 집합을 사용한다.
        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }

    private static void fatalError(String msg) {
        System.err.println(msg);
        System.exit(1);
    }
    ```
    - 이 프로그램은 제네릭 집합 테스터로 변신할 수도 있다.
    - 또한, 제네릭 집합 성능 분석 도구로 활용할 수도 있다.
    - 이 기법은 완벽한 서비스 제공자 프레임워크를 구현할 수 있을 만큼 강력하다. _(→ item 1. JDBC)_

<br>

### 정리
- 리플렉션은 복잡한 특수 시스템을 개발할 때 필요한 강력한 기능이지만, 단점도 많다.
- 컴파일타임에는 알 수 없는 클래스를 사용하는 프로그램을 적성한다면 리플렉션을 사용해야 할 것이다.
- 되도록 객체 생성에만 사용하고, 생성한 객체를 이용할 때는 적합한 인터페이스나 컴파일타임에 알 수 있는 상위 클래스로 형변환해 사용해야 한다.

<br>