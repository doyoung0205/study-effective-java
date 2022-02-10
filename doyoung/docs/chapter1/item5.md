## 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식이 적합하지 않다.

**인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식**


--- 

기존에 자원을 인스턴스 내부에서 생성하게 되면 유연하지 않고 테스트하기가 어렵다.

왜냐하면 기존에 자원이 있는 상태에서 자원이 필요할 때, 또는 다른 자원으로 교체할 때
인스턴스 내부에 자원 하나로 모든 상황을 대처하기는 어렵다.

물론 setter 와 같은 메서드를 추가해서 변경할 수 있지만,
이 방법은 오류는 내기도 쉽고 멀티스레드 환경에서는 사용하기가 어렵다.

따라서, 인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식을 선호한다.

이런 방식은 타입 매개변수(추상화)에 제한된 다양한 객체들을 자원으로 활용할 수 있게 해준다. (의존객체 주입)


### 핵심정리

클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 
싱글턴과 정적 유틸리티 클래스는 사용하지 않는 것이 좋다.

이 자원들을 클래스가 직접 만들게 해서도 안 된다. 
대신 필요한 자원을(혹은 그 자원을 만들어주는 팩터리를) 생성자에 (혹은 정적 팩터리나 빌더에)  
넘겨주자. 의존 객체 주입이라 하는 이 기법은 클래스의 유연성 재사용성, 테스트 용이성을 기막히게 개선해준다.


