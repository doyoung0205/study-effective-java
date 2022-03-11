# 클래스와 인터페이스

[아이템 22. 인터페이스는 타입을 정의하는 용도로만 사용라라](#인터페이스는-타입을-정의하는-용도로만-사용라라)   

<br>

## 인터페이스는 타입을 정의하는 용도로만 사용라라

- 인터페이서는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다.

### 상수 인터페이스
- 상수는 외부 인터페이스가 아니라 내부 구현이기 때문에 상수 인터페이스는 안티패턴이고, 인터페이스를 잘못 사용한 예이다.
  ```java
  public interface PhysicalConstants {
      // 아보가드로 수 (1/몰)
      static final double AVOGADROS_NUMBER = 6.022_140_857e23;
  
      // 볼츠만 상수 (J/K)
      static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
  
      // 전자 질량 (kg)
      static final double ELECTRON_MASS = 9.109_383_56e-31;
  }
  ```
  - 이것은 내부 구현을 클래스의 API로 노출하는 행위다.
  

- 특정 클래스나 인터페이스와 강하게 연관된 상수라면 그 클래스나 인터페이스 자체에 추가해야 한다.  
  - 대표적으로, `Integer와` `Double에` 선언된 `MIN_VALUE`와 `MAX_VALUE` 상수가 있다.
- 열거 타입으로 나타내기 적합한 상수라면 열거타입으로 만들어 공개하면 된다.
- 또한, 인스터스화할 수 없는 유틸리티 클래스에 담아 공개할 수도 있다.
  ```java
  public class PhysicalConstantsUtil {
      private PhysicalConstantsUtil() { }  // 인스턴스화 방지
  
      // 아보가드로 수 (1/몰)
      public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
  
      // 볼츠만 상수 (J/K)
      public static final double BOLTZMANN_CONST = 1.380_648_52e-23;
  
      // 전자 질량 (kg)
      public static final double ELECTRON_MASS = 9.109_383_56e-31;
  }
  ```


<br>

