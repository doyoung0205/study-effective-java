1### 아이템1 생성자 대신 정적 팩터리 메서드를 고려하라
- 클래스의 인스턴스를 얻는 방법은 `public 생성자`, `정적 팩터리 메서드` 사용이다.

#### 정적 팩터리 메서드의 장점은 무엇인가 ?
1. 이름을 가질 수 있다.
    - 생성자 매개변수, 생성자의 수가 많아질수록 각각의 생성자의 역할을 구분하기가 쉽지 않다.
2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
    - 객체를 생성하는 것은 비용이 든다. 불필요한 객체 생성을 막는 것은 이러한 비용을 줄일 수 있게 해준다.
    - 예시 ) `Boolean` 객체의 `valueOf` 메서드는 객체를 생성하지 않는다.
3. 반환 타입의 하위 타입객체를 반환할 수 있다.
    - 생성자는 해당 객체만을 반환할 수 있다. 정적 팩토리를 사용하는 경우 반환 타입을 자유롭게 선택할 수 있다.
4. 입력 매게변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
    - 매개변수에 따라 다른 객체를 반환할 수 있고, 클라이언트는 이 부분에 대해서 알 필요가 없다.
    - 즉, 내부적으로 반환 타입을 결정할 비즈니스 로직을 구현할 수 있다는 점이 장점이다.
5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
    - JDBC 의 경우를 예로 들면 연결할 데이터베이스의 드라이버가 존재하지 않아도 코드 구현하는 데 문제가 생기지 않는다.
    - JDBC 내부적으로 코드를 보면 사용 시점에 드라이버를 로드하고 존재하지 않을 경우 예외를 반환하도록 되어 있다.
    - 서비스 제공자 프레임워크 컴포넌트 3요소(+ 서비스 제공자 인터페이스)
        - 서비스 인터페이스 : 구현체 동작을 정의 (Connection)
        - 제공자 등록 API : 제공자가 구현체를 등록할 때 사용(DriverManager.registerDriver)
        - 서비스 접근 API : 클라이언트가 서비스의 인스턴스를 얻을 때 사용(DriverManager.getConnection)
        - *서비스 제공자 인터페이스 : 서비스 인터페이스의 인스턴스를 생성하는 팩터리 객체를 설명한다.(Driver)
   ```java
   for (DriverInfo aDriver : registeredDrivers) {
       // If the caller does not have permission to load the driver then
       // skip it.
       if (isDriverAllowed(aDriver.driver, callerCL)) {
           try {
               println("    trying " + aDriver.driver.getClass().getName());
               Connection con = aDriver.driver.connect(url, info);
               if (con != null) {
                   // Success!
                   println("getConnection returning " + aDriver.driver.getClass().getName());
                   return (con);
               }
           } catch (SQLException ex) {
               if (reason == null) {
                   reason = ex;
               }
           }

       } else {
           println("    skipping: " + aDriver.getClass().getName());
       }
   }
- 서비스 제공자 프레임워크 패턴의 변형된 패턴
    - 브리지 패턴 : 구현으로부터 분리하여 각각 독립적으로 변화할 수 있는 패턴
    - 의존 객체 주입 프레임워크


### 정리 
 - 생성자는 개수와 매개변수가 많아질수록 관리하기가 어렵다. 
 - `정적 팩터리 메서드`는 이름을 가질 수 있고, 반환 타입을 지정할수 있으며 필요에 따라 객체를 만들지 않을 수도 있다.
 - 관리의 용이성과 불필요 객체 사용을 줄이기 위해서라도 정적 팩토리 메서드 사용을 하도록 하자.
