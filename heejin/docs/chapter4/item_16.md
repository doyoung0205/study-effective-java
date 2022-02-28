# 클래스와 인터페이스

[아이템 16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라](#public-클래스에서는-public-필드가-아닌-접근자-메서드를-사용하라)   
[- ](#)  

<br>

## public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
- 인스턴스 필드들을 모아놓은 일 외에는 아무 목적도 없는 퇴보한 클래스를 작성하려 할 때가 있다.
  ```java
  class Point {
      public double x;
      public double y;
  }
  ```
  - 이런 클래스는 데이터 필드에 직접 접근 할 수 있으니 캡슐화의 이점을 제공하지 못한다.
  - API를 수정하지 않고는 내부 표현을 바꿀 수 없고, 불변식을 보장할 수 없으며, 외부에서 필드에 접근할 때 부수 작업을 수행할 수도 없다.

- 위의 클래스를 좀 더 나은방식으로 수정하기 위해 필드들을 모두 private으로 바꾸고 public 접근자(getter)를 추가한다.
  ````java
  public class Point {
      private double x;
      private double y;
  
      public Point(double x, double y){
          this.x = x;
          this.y = y;
      }
  
      public void setX(double x) {this.x = x;}
      public void setY(double y) {this.y = y;}
      
      public double getX() {return x;}
      public double getY() {return y;}
  }
  ````

- package-private, private 중첩 클래스의 경우 데이터 필드를 노출한다고 해도 문제가 없다. 
  - 그 클래스가 표현하려는 추상 개념만 올바르게 표현해주면 된다.
  - 이 방식은 접근자 방식보다 훨씬 깔끔하며, 패키지 바깥 코드는 전혀 손대지 않고도 데이터 표현 방식을 바꿀 수 있다.
  - private 중첩 클래스의 경우라면 수정 범위가 더 좁아져서 이 클래스를 포함하는 외부 클래스까지로 제한된다.



<br>

---
#### Reference

- []()


