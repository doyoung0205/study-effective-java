# 열거 타입과 애너테이션

[아이템 41. 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라](#정의하려는-것이-타입이라면-마커-인터페이스를-사용하라)  
[- 마커 인터페이스와 마커 애너테이션 비교](#마커-인터페이스와-마커-애너테이션-비교)  
[- 마커 인터페이스와 마커 애너테이션의 사용](#마커-인터페이스와-마커-애너테이션의-사용)  
[- 정리](#정리)

<br>

## 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라
- 아무 메서드도 담고 있지 않고, 단지 자신을 구현하는 클래스가 특정 속성을 가짐을 표시해주는 인터페이스를 **마커 인터페이스**라고 한다.
- eg. Serialisable
  ```java
  public interface Serializable {
  }
  ```
  ```java
  // 매개변수로 Serializeable 타입을 받도록 했다.
  public static void newWriteObject(Serializable object, String path) {
    File file = new File(path);
    try (ObjectOutputStream oops = new ObjectOutputStream(new FileOutputStream(file))) {
        oops.writeObject(object);
    } catch (IOException e) {
        System.out.println("런타임에 에러 발생");
    }
  }
  ```
- eg. ObjectOutputStream
  ```java
  // @throws  NotSerializableException Some object to be serialized does not implement the java.io.Serializable interface.  
  public final void writeObject(Object obj) throws IOException {
    if (enableOverride) {
      writeObjectOverride(obj);
      return;
    }
    try {
      writeObject0(obj, false);
    } catch (IOException ex) {
      if (depth == 0) {
        writeFatalException(ex);
      }
      throw ex;
    }
  }
  ```
  - writeObject 메서드는 매개변수로 Object를 받고 있다. 
  - 하지만, writeObject0 메서드에서 Serializable을 구현했는지 instanceof 로 타입검사를 하는데, 이는 마커 인터페이스가 타입을 적절하게 사용하지 못한 것이다.

### 마커 인터페이스와 마커 애너테이션 비교
1. 마커 인터페이스는 이를 구현한 클래스의 인스턴스들을 구분하는 타입으로 쓸 수 있지만, 마커 애너테이션은 그렇지 않다.
2. 마커 인터페이스는 적용 대상을 더 정밀하게 지정할 수 있다.
   - 특정 인터페이스를 구현한 클래스에만 적용하고 싶은 마커가 있다면, 마킹하고 싶은 클래스에서만 그 인터페이스를 구현하면 된다.
3. 반면, 마커 애너테이션은 거대한 애너테이션 시스템의 지원을 받을 수 있다는 장점이 있다.


### 마커 인터페이스와 마커 애너테이션의 사용
- 클래스와 인터페이스 외의 프로그램 요소(모듈, 패키지, 필드, 지역변수 등)에 마킹해야 할 때는 애너테이션을 쓸 수 밖에 없다.
- 마커를 클래스나 인터페이스에 적용해야 한다면, "마킹된 객체를 매개변수로 받는 메서드를 작성할 일이 있을까?"라고 생각해 보자.
    - 답이 "그렇다" 이면 마커 인터페이스를 써야 한다.
    - 이렇게 하면 마커 인터페이스를 해당 메서드의 매개변수 타입으로 사용하여 컴파일타임에 올를 잡아낼 수 있다.


### 정리
- 마커 인터페이스와 마커 애너테이션은 각자의 쓰임이 있다.
    - 마커 인터페이스와 마커 에너테이션 모두 클래스가 어떤 속성을 가진다는 표시를 할 수 있다.
    - 마커 인터페이스는 타입으로 사용하여 컴파일타임에 오류를 검출할 수 있다.
    - 마커 에너테이션은 런타임에 오류를 검출할 수 있다.
- 새로 추가하는 메서드 없이 단지 타입 정의가 목적이라면 마커 인터페이스를 선택하자.
- 적용 대상이 ElementType.TYPE인 마커 애너테이션을 작성하고 있다면, 정말 애너테이션으로 구현하는게 옳은지, 혹은 마커 인터페이스가 낫지는 않을지 생각해보자.