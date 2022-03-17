### item16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
- 데이터 필드에 직접 접근할 수 있는 클래스는 캡슐화의 이점을 가지지 못한다
  - `getter, setter` 등의 데이터 필드 접근자를 제공해야 한다
- package-private 클래스 혹은 private 중첩 클래스라면 데이터 필드를 노출한다해도 문제가 없다
  - 이유인 즉슨, outer class 에서만 접근할 수 있기 때문
- public 클래스는 가변 필드를 직접 노출해서는 안된다

#### 중첩 클래스 이해하기 

##### Static nested classes
- 정적 멤버와 마찬가지로 이러한 멤버는 클래스의 인스턴스가 아니라 해당 클래스를 둘러싸는 클래스에 속한다
- 모든 유형의 접근제어자를 가질 수 있다
- `둘러싸는 클래스의 정적 멤버`에만 액세스할 수 있다
- 정적, 비정적 멤버를 모두 정의할 수 있다

##### Non-static nested classes
- 내부 클래스라고도 한다 
- 모든 유형의 접근제어자를 가질수 있다
- 인스턴스 변수 및 메서드와 마찬가지로 `내부 클래스는 둘러싸는 클래스의 인스턴스와 연결`된다.
- 정적/비정적 관계없이 둘러싸는 클래스의 모든 구성원에 액세스 할 수 있다 
- `비정적 멤버`만 정의할 수 있다

##### Local classes
- 내부 클래스의 특수한 유형으로 `메서드` 또는 `scope` 내부에 정의된다
- 접근제어자를 가질 수 없다
- 둘러싸인 정적/비정적 멤버에 접근할 수 있다
- 인스턴스 멤버만 정의할 수 있다

##### Anonymous classes
- 익명 클래스를 사용하여 재사용 가능한 구현을 만들 필요 없이 인터페이스 또는 추상클래스로 구현
- 접근제어자를 가질 수 없다
- 둘러싸인 정적/비정적 멤버에 접근할 수 있다
- 인스턴스 멤버만 정의할 수 있다
- 생성자를 정의하거나 다른 클래스 또는 인터페이스를 확장/구현할 수 없는 유일한 중첩 클래스


#### Inner Class 사용하는 경우는 ? 
- Inner Class는 위의 예시에서 보았듯이 외부 클래스에 대한 참조가 유지된다.
- 외부에 멤버에 대한 참조를 하지 않는 경우 IDE에서는 `static class`로 변경할 것을 알린다
- 이유인 즉슨, 외부 클래스에 대한 참조를 가지기 위해 작성한 Inner Class를 직접적으로 생성하는 경우 외부 참조가 유지되기 때문이다
   ```text
    // 내부클래스를 중첩클래스 외부에서 직접 생성할 수 없음     
    final Clazz.InnerClass innerClass = new Clazz.InnerClass();// compile error 
    // 외부클래스를 통해 객체생성할 수 있음.
    Clazz.InnerClass innerClass = clazz.new InnerClass(); 
- 내부 클래스 5번째 특징 `비정적 멤버`만 정의할수도 있다도 위의 예를 뒷받침해준다
- 내부 클래스는 외부 클래스의 인스턴스로 사용할 목적으로 작성되었는데 static 변수는 인스턴스 없이 작동하기 때문에 규칙에 위배된다
- 외부의 멤버의 인스턴스로 사용할 목적이 아닌 경우라면 static nested 클래스로 선언하거나 클래스를 분리하는 것이 옳다 
  - 불필요한 메모리 참조가 발생하기 때문에 




- 출처 
  - [https://www.baeldung.com/java-nested-classes](https://www.baeldung.com/java-nested-classes)
  - [https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html)
  - [https://stackoverflow.com/questions/70324/java-inner-class-and-static-nested-class/70358#70358](https://stackoverflow.com/questions/70324/java-inner-class-and-static-nested-class/70358#70358)
