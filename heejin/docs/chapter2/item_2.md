# 객체 생성과 파괴

[아이템 2. 생성자에 매개변수가 많다면 빌더를 고려하라](#생성자에-매개변수가-많다면-빌더를-고려하라)   
[1. 점층적 생성자 패턴](#1-점층적-생성자-패턴)  
[2. 자바빈즈 패턴](#2-자바빈즈-패턴)  
[3. 빌더 패턴](#3-빌더-패턴)

<br>

## 생성자에 매개변수가 많다면 빌더를 고려하라

- 정적 팩터리와 생성자에는 똑같은 제약이 하나 있다.  
  → 선택적 매개변수가 많을 때 적절히 대응하기 어렵다는 점이다.


### 해결 방법

#### 1. 점층적 생성자 패턴
- 필수 매개변수만 받는 생성자와 조합하여 선택 매개변수를 받는 생성자를 여러개 만드는 방법이다.
- 예제 코드 - [점층적 생성자 패턴](../../src/main/java/study/heejin/chapter2/item2/NutritionFacts_1.java)

   ```java
   NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
   ```

- 이런 생성자는 사용자가 설정하고 싶지 않은 매개변수까지 포함하기 쉽고, 어쩔 수 없이 그런 매개변수에도 값을 지정해줘야 한다.
- 매개변수가 개수가 많아질수록 클라이언트 코드를 작성하거나 읽기 어렵다.
- 클라이언트가 실수로 매개변수의 순서를 바꿔 건네줘도 컴파일러는 알아채지 못하고, 결국 런타임에 엉뚱한 동작을 하게 될 수도 있다.

<br>

#### 2. 자바빈즈 패턴
- 매개변수가 없는 생성자로 객체를 만든 후, 세터 메서드들을 호출해 원하는 매개변수의 값을 설정하는 방식이다.
- 예제 코드 - [자바빈즈 패턴](../../src/main/java/study/heejin/chapter2/item2/NutritionFacts_2.java)

   ```java
   NutritionFacts cocaCola = new NutritionFacts();
   cocaCola.setServingSize(240);
   cocaCola.setServings(8);
   cocaCola.setCalories(100);
   cocaCola.setSodium(35);
   cocaCola.setCarbohydrate(27);
   ```

- 하지만 자바빈즈 패턴은 심각한 단점을 가지고 있다.
- 자바빈즈 패턴에서는 하나를 만들려면 메서드를 여러 개 호출해야 하고, 객체가 완전히 생성되기 전까지는 일관성이 무너진 상태에 놓이게 된다.
- 일관성이 깨진 객체가 만들어지면, 버그를 심은 코드와 그 버그 때문에 런타임에 문제를 겪는 코드가 물리적으로 멀리  떨어져 있을 것이므로 디버깅도 만만치 않다.

**→ 자바빈즈 패턴을 사용하면 일관성이 깨지고, 불변으로 만들 수 없다.**

<br>

#### 3. 빌더 패턴
- 점층적 생성자 패턴의 안전성과 자바빈즈 패턴의 가독성을 겸비한 것이 빌더 패턴이다.
- 예제 코드 - [빌더 패턴](../../src/main/java/study/heejin/chapter2/item2/NutritionFacts_3.java)

   ```java
   NutritionFacts nutritionFacts = new NutritionFacts.Builder(240, 8)
                                          .calories(100)
                                          .sodium(35)
                                          .carbohydrate(27)
                                          .build();
   ```

- 빌더 패턴의 클래스는 불변이며, 모든 매개변수의 기본값들은 한곳에 모아두었다.
- 빌더의 세터 메서드들은 빌더 자신을 반환하기 때문에 연쇄적으로 호출할 수 있다.   
  (이런 방식을 메서드 호출이 흐르듯 연결된다는 뜻으로 *플루언트 API(fluent API) 혹은 메서드 연쇄(method chaining)* 라고 한다.)

   #### 📍 빌더 패턴과 유효성 검사
    - 여기서 빌더 패턴의 핵심이 보이게 하려고 유효성 검사는 생략했다.
    - 잘못된 매개변수를 최대한 일찍 발견하려면 빌더의 생성자와 메서드에서 입력 매개변수를 검사하고, build 메서드가 호출하는 생성자에서 불변식을 검사하자.
    - 불변식을 보장하려면 빌더로부터 매개변수를 복사한 후 해당 객체 필드들도 검사해야 한다. *(→ item 50)*

      ```text
      • 불변(immutable)은 어떠한 변경도 호용하지 않는다는 뜻으로 주로 변경을 허용하는 가변 객체와 구분하는 용도로 쓰인다. 
        대표적으로 String 객체는 한번 만들어지면 절대 값을 바꿀 수 없는 불변 객체다.
      • 불변식(invariant)은 프로그램이 실행되는 동안, 혹은 정해진 기간 동안 반드시 만족해야 하는 조건을 말한다.
        예컨데 리스트의 크리는 반드시 0 이상이어야 하는데 음수 값이 된다면 불변식이 깨지는 것이다. 
        또한, 기간을 표현하는 Period 클래스에서 start 필드의 값은 반드시 end 필드의 값보다 앞서야 하므로, 두 값이 역전되면 역시 불변식이 깨진 것이다.
      ```

   #### 📍 계층적 클래스에서 사용
    - 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기에 좋다.
    - 예제 코드 - [피자](../../src/main/java/study/heejin/chapter2/item2/Pizza.java)  
      \- `Pizza.Builder` 클래스는 재귀적 타입 한정을 이용하는 제네릭 타입이다. *(→ item 30)*  
      \- 여기에 추상 메서드인 `self`를 사용해 하위 클래스에서는 형변환하지 않고도 메서드 연쇄를 지원할 수 있다.  
      \- self 타입이 없는 자바를 위한 이 우회 방법을 *시뮬레이트한 셀프 타입(simulated self-type)* 관용구라 한다. 
    - 예제 코드 - [뉴욕 피자](../../src/main/java/study/heejin/chapter2/item2/NyPizza.java)
              , [칼조네 피자](../../src/main/java/study/heejin/chapter2/item2/Calzone.java)  
      \- 각 하위 클래스의 빌더가 정의한 `build` 메서드는 해당 구체 하위 클래스를 반환하도록 선언한다.  
      \- 하위 클래스의 메서드가 상위 클래스의 메서드가 정의한 반환 타입이 아닌, 그 하위 타입을 반환하는 기능을 *공변반환 타이핑(covariant return typing)* 이라 한다.
  
      ```java
      NyPizza nyPizza = new NyPizza.Builder(SMALL)
                                   .addTopping(SAUSAGE)
                                   .addTopping(ONION)
                                   .build();
   
      Calzone calzone = new Calzone.Builder()
                              .addTopping(HAM)
                              .sauceInside()
                              .build();
      ```
  - 빌더 패턴은 상당히 유연하다.
  - 빌더 한나로 여러 객체를 순회하면서 만들 수 있고, 빌더에 넘기는 매개변수에 따라 다른 객체를 만들 수도 있다.


- 빌더 패턴에 장점만 있는 것은 아니다.
- 객체를 만들려면, 그에 앞서 빌더부터 만들어야 한다. 빌더 생성 비용이 크지는 않지만 성능에 민감한 상황에서는 문제가 될 수 있다.
- 또한 점층적 생성자 패턴보다는 코드가 장황해서 매개변수가 4개 이상은 되엉야 값어치를 한다.
        
<br>


