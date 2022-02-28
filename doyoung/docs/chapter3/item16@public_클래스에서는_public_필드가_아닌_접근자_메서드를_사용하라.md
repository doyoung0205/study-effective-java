### public 클래스에서는 public 필드가 아닌 접근 메서드를 사용하라

---

- 패키지 바깥에서 접근할 수 있는 클래스라면 접근자를 제공함으로써
  <br>
  내부 표현 방식을 언제든 바꿀 수 있는 유연성을 얻을 수 있다.

  


--- 
### pubilc 클래스의 필드를 직접 노출한 안좋은 사례

`Dimension.java`

```java
public class Dimension extends Dimension2D implements java.io.Serializable {
    
    public int width;
    public int height;

    // ...
}
```
