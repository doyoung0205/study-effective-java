# 클래스와 인터페이스

[아이템 25. 톱 레벨 클래스는 한 파일에 하나만 담으라](#톱-레벨-클래스는-한-파일에-하나만-담으라)   

<br>

## 톱 레벨 클래스는 한 파일에 하나만 담으라
- 소스 파일 하나에 톱 레벨 클래스 여러개를 둘 수 있다.
- 컴파일에 문제는 없지만, 심각한 위험을 감수해야 한다.
- 문제는 한 클래스를 여러 가지로 정의할 수 있으며, 어느 것을 사용할지는 어느 소스 파일을 먼저 컴파일하는지에 따라 달라지기 때문이다.  
  - `javac Main.java Utensil.java`  
    ```java
    public class Main {
        public Main() {
        }
    
        public static void main(String[] var0) {
            System.out.println("pancake");
        }
    }
    ```
  - `javac Dessert.java Main.java`
    ```java
    public class Main {
        public Main() {
        }
    
        public static void main(String[] var0) {
            System.out.println("potpie");
        }
    }
    ```
  
- 여러 톱 레벨 클래스를 한 파일에 담고 싶다면 정적 멤버 클래스를 사용하는 방법을 고민해볼 수 있다. → [예제](../../src/main/java/study/heejin/chapter4/item25/StaticMemberClass.java)

<br>

