## Item3 private 생성자나 열거 타입으로 싱글턴임을 보증하라
 - 싱글턴은 무상태 객체나 시스템 설계상 유일해야하는 시스템 컴포넌트에 사용된다.
 
### 싱글톤 패턴의 종류
1. 정적 필드 방식의 싱글턴
2. 정적 팩터리 방식의 싱글턴
    - API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다.
    - 제네릭 싱글턴 팩터리로 만들 수 있다
    - 정적 팩터리의 메서드 참조를 공급자로 사용할 수 있다.
3. 열거 타입의 싱글턴

### 싱글톤 패턴의 단점 
 - 사용하는 클라이언트를 테스트하기 어렵다.
    - mocking 테스트가 어렵다(static에 대한 mock객체 테스트가 기본적으로 제공되지 않음)
 - 싱글톤을 보장하기 어렵다(1,2번의 경우)
   - reflection을 사용할 경우 생성자 제한을 통한 싱글톤 방식을 깨뜨릴 수 있다.
   - Serialiable 객체의 경우 싱글톤을 보장하기 어렵다.

### 역직렬화 시 readResolve 메서드가 호출되는 이유
 - 역직렬화 시 자동으로 인스턴스가 생성되기 떄문에 싱글톤을 보장받을 수 없음을 학습하였다.
 - readResolve() 메서드는 역직렬화 시 자동으로 인스턴스 대신 오브젝트를 반환할 수 있다.
 - readResolve()가 존재하는 경우 반환한 인스턴스가  readObject메서드가 만든 객체를 대체할 수 있다.
```java
public class ObjectStreamClass implements Serializable {
    //...
    
    Object invokeReadResolve(Object obj)
        throws IOException, UnsupportedOperationException
    {
        requireInitialized();
        if (readResolveMethod != null) {
            try {
                return readResolveMethod.invoke(obj, (Object[]) null);
            } catch (InvocationTargetException ex) {
                Throwable th = ex.getTargetException();
                if (th instanceof ObjectStreamException) {
                    throw (ObjectStreamException) th;
                } else {
                    throwMiscException(th);
                    throw new InternalError(th);  // never reached
                }
            } catch (IllegalAccessException ex) {
                // should not occur, as access checks have been suppressed
                throw new InternalError(ex);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
```

```java
public class SerializationUtils {
    ///...
    public static <T> T deserialize(final InputStream inputStream) {
        Validate.notNull(inputStream, "inputStream");
        try (ObjectInputStream in = new ObjectInputStream(inputStream)) {
            @SuppressWarnings("unchecked")
            final T obj = (T) in.readObject();
            return obj;
        } catch (final ClassNotFoundException | IOException ex) {
            throw new SerializationException(ex);
        }
    }
}
```

#### 용어 정리
- 제네릭 싱글턴 팩터리 : 제네릭으로 사용 가능한(Object) 인스턴스를 만들어 두고 제네릭을 통해 반환타입을 결정하는 방식
- 공급자(Supplier) : 인자를 받지 않고 타입을 없고 리턴값이 있는 getXXX() 메소드를 가지고 있다.

