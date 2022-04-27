## item42 익명 클래스보다는 람다를 사용하라 

### 익명클래스 & 람다 & 메서드 참조 표현식 
- 익명 클래스는 낡은 기법이다.
    ```java
    Collections.sort(list, new Comparator<String>() {
        public int compare(String s1, String s2) {
            return Integer.compare(s1.length(), s2.length()) 
        }  
    });
    ```
  - 익명 클래스 방식은 코드가 길기 때문에 가독성이 좋지 않고, 함수형 프로그래밍에 적합하지 못하다
- 함수형 프로그래밍에 적합한 람다 표현식
    ```java
    Collections.sort(list, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    ```
  - 람다는 메서드 타입을 추론한다(컴파일러가 문맥상 타입을 추론한다. 컴파일러가 추론하지 못하는 경우 타입을 명시한다)
- 람다 자리에 비교자 생성 메서드를 사용하여 코드를 더 간결하게 할 수 있다.
    ```java
    Collections.sort(list, comparingInt(String::length));
    ```
  
### 람다의 단점 
 - 이름이 없고, 문서화도 못한다. 
 - 코드 자체로 설명되지 않거나 코드 줄 수가 많아지는 경우 가독성이 더 좋지 못하다.
 - `람다는 한 줄일 때 가장 좋고 세줄이 넘어가는 경우 사용하지 말아야 한다`
 - 람다는 자신을 참조할 수 없다(차이점 알기)
   - 람다에서의 `this`는 바깥 인스턴스를 가리킨다
   - 익명 클래스의 `this`는 익명 클래스 인스턴스 자신을 가리킨다 
 
### 람다는 함수형 인터페이스에서만 쓰인다.
- `추상 클래스의 인스턴스를 만들 때` 람다를 쓸 수 없고, `익명 클래스`를 사용해야 한다.

### 람다 사용 주의 사항
- 람다는 익명 클래스 처럼 직렬화 형태가 구현별(가상머신)로 다를 수 있기 때문에 `직렬화`를 하는 것을 지양한다
    ```java
    Runnable r = (Runnable & Serializable)() -> System.out.println("Serializable!"); //anti pattern
    ```
  

### 정리
- 익명 클래스는 타입의 함수형 인터페이스가 아닌 타입의 인스턴스를 만들 때만 사용하고 람다를 사용하도록 하자