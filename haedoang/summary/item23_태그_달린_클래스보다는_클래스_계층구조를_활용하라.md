### item23 태그 달린 클래스보다는 클래스 계층구조를 활용하라
- 태그 달린 클래스의 단점
  - 쓸데 없는 코드가 많아진다 => 객체 응집도 약화 
  - 가독성이 좋지 않고, 메모리도 많이 사용한다
  - 불필요한 필드들까지 생성자에서 초기화해야 한다

#### 클래스 계층 구조
- 계층구조의 `root` 가 될 추상 클래스를 정의하고, 태그 값에 따라 동작이 달라지는 <br/>
  메서드들을 루트 클래스의 추상 메서드로 선언한다
- 클래스 계층 구조의 장점
  - 필드들을 모두 `final`로 정의하여 생성자가 모든 필드를 초기화한다
  - 독립적으로 계층구조를 확장하고 함께 사용할 수 있다. 
  
#### 클래스 계층 구조 사용 시 주의할 점 
- 리스코프 치환 법칙이 위배하지 않는 지 확인한다(책에서의 예제에서는 필드를 `final`로 불변 객체로 만들었기 때문에 해당하지 않는다)


- 출처
  - [https://stackoverflow.com/questions/56860/what-is-an-example-of-the-liskov-substitution-principle](https://stackoverflow.com/questions/56860/what-is-an-example-of-the-liskov-substitution-principle) 
  - [http://web.archive.org/web/20160521015258/https://lostechies.com/derickbailey/2009/02/11/solid-development-principles-in-motivational-pictures/](http://web.archive.org/web/20160521015258/https://lostechies.com/derickbailey/2009/02/11/solid-development-principles-in-motivational-pictures/)
